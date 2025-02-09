package com.tookscan.tookscan.core.utility;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.tookscan.tookscan.order.domain.Document;
import java.net.URL;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class S3Util {
    private final AmazonS3Client amazonS3Client;

    private final String IMAGE_CONTENT_PREFIX = "image/";

    @Value("${cloud.aws.s3.pdf.prefix}")
    private String PDF_CONTENT_PREFIX;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.s3.url}")
    private String bucketUrl;

    @Value("${cloud.aws.s3.pdf.expiration-seconds}")
    private Long pdfExpirationSeconds;

    /**
     * S3 key를 전달받아 해당 객체의 최종 URL을 반환 (AmazonS3Client가 제공하는 기본 메서드를 사용)
     *
     * @param key S3 객체 키 (예: 'folder/subfolder/filename.png')
     * @return 해당 객체에 접근 가능한 URL
     */
    public String getS3ObjectUrl(String key) {
        return amazonS3Client.getUrl(bucketName, key).toString();
    }

    /**
     * 이미지 경로를 prefix(IMAGE_CONTENT_PREFIX)로 묶어 최종 접근 가능한 URL을 반환
     *
     * @param imageName 실제 이미지 파일 이름 (예: 'myphoto.png')
     * @return 이미지 객체에 접근 가능한 URL
     */
    public String getImageUrl(String imageName) {
        // 예: "image/myphoto.png"
        String finalKey = IMAGE_CONTENT_PREFIX + imageName;
        return amazonS3Client.getUrl(bucketName, finalKey).toString();
    }

    /**
     * PDF 경로를 prefix(PDF_CONTENT_PREFIX)로 묶어 최종 접근 가능한 URL을 반환
     *
     * @param document PDF 파일을 가지고 있는 Document 객체
     * @return PDF 객체에 접근 가능한 URL
     */
    public String getPdfUrl(Document document) {
        String finalKey =
                PDF_CONTENT_PREFIX + document.getOrder().getId() + '/' + document.getName() + '_' + document.getId()
                        + ".pdf";
        return amazonS3Client.getUrl(bucketName, finalKey).toString();
    }

    /**
     * 특정 Document에 대한 PDF 파일을 일정 시간만 유효한 프리사인드 URL로 반환
     *
     * @param document PDF 파일을 가지고 있는 Document 객체
     * @return 만료 시간 이후 사용 불가능한 임시 다운로드 URL
     */
    public String getPdfPresignedUrl(Document document) {
        // 최종 S3 객체 키 구성
        String finalKey = PDF_CONTENT_PREFIX + document.getOrder().getId() +
                '/' + document.getName() + '_' + document.getId() + ".pdf";

        // 만료 시간 계산
        Date expiration = new Date();
        long now = expiration.getTime();
        expiration.setTime(now + pdfExpirationSeconds * 1000);

        // Presigned URL 요청 생성
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, finalKey)
                .withMethod(HttpMethod.GET)
                .withExpiration(expiration);

        // Presigned URL 생성
        URL presignedUrl = amazonS3Client.generatePresignedUrl(request);
        return presignedUrl.toString();
    }
}
