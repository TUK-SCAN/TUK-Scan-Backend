package com.tookscan.tookscan.core.utility;

import com.amazonaws.services.s3.AmazonS3Client;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class S3Util {
    private final AmazonS3Client amazonS3Client;

    private final String IMAGE_CONTENT_PREFIX = "image/";

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.s3.url}")
    private String bucketUrl;

    @Getter
    @Value("${cloud.aws.s3.user-default-img-url}")
    private String userDefaultImgUrl;

    @Getter
    @Value("${cloud.aws.s3.owner-default-img-url}")
    private String ownerDefaultImgUrl;

}
