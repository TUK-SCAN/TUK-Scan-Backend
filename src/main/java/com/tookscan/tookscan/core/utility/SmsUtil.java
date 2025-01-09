package com.tookscan.tookscan.core.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class SmsUtil {

    @Value("${ncp.sms.url}")
    private String smsSendUrl;

    @Value("${ncp.sms.service-id}")
    private String serviceId;

    @Value("${ncp.access-key}")
    private String accessKey;

    @Value("${ncp.secret-key}")
    private String secretKey;

    @Value("${ncp.sms.sender}")
    private String senderPhone;

    private final RestClientUtil restClientUtil;

    private static final String SMS_MESSAGE = "[툭스캔]\\n인증번호: ";

    public void sendAuthenticationCode(String phoneNumber, String authenticationCode) {

        String body = String.format(
                "{" +
                        "\"type\":\"SMS\"," +
                        "\"contentType\":\"COMM\"," +
                        "\"countryCode\":\"82\"," +
                        "\"from\":\"%s\"," +
                        "\"subject\":\"\"," +
                        "\"content\":\"%s\"," +
                        "\"messages\":[{\"to\":\"%s\",\"subject\":\"\",\"content\":\"%s\"}]," +
                        "\"files\":[]," +
                        "\"reserveTime\":\"\"," +
                        "\"reserveTimeZone\":\"\"" +
                        "}",
                senderPhone, SMS_MESSAGE , phoneNumber, authenticationCode
        );

        String timeStamp = String.valueOf(System.currentTimeMillis());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.add("x-ncp-apigw-timestamp", timeStamp);
        headers.add("x-ncp-iam-access-key", accessKey);
        headers.add("x-ncp-apigw-signature-v2", generateSignature(timeStamp, body));

        String url = smsSendUrl.replace("{serviceId}", serviceId);

        restClientUtil.sendPostMethod(url, headers, body);
    }

    private String generateSignature(String timeStamp, String body) {
        try {
            String space = " ";
            String newLine = "\n";
            String method = "POST";
            String url = "/sms/v2/services/" + serviceId + "/messages";

            String message = method + space + url + newLine + timeStamp + newLine + accessKey + newLine + body;

            Mac hasher = Mac.getInstance("HmacSHA256");
            hasher.init(new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = hasher.doFinal(message.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error generating signature", e);
        }
    }
}
