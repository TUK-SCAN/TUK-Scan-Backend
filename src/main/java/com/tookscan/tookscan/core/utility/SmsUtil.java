package com.tookscan.tookscan.core.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
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

    public void sendAuthenticationCode(String phoneNumber, String authenticationCode) throws NoSuchAlgorithmException, InvalidKeyException {

        String body = String.format(
                "{" +
                        "\"type\":\"SMS\"," +
                        "\"contentType\":\"COMM\"," +
                        "\"countryCode\":\"82\"," +
                        "\"from\":\"%s\"," +
                        "\"subject\":\"\"," +
                        "\"content\":\"%s\"," +
                        "\"messages\":[{\"to\":\"%s\",\"subject\":\"\",\"content\":\"\"}]" +
                        "}",
                senderPhone, SMS_MESSAGE+authenticationCode , phoneNumber
        );

        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.add("x-ncp-apigw-timestamp", timestamp);
        headers.add("x-ncp-iam-access-key", accessKey);
        headers.add("x-ncp-apigw-signature-v2", makeSignature(timestamp));

        String url = smsSendUrl.replace("{serviceId}", serviceId);

        restClientUtil.sendPostMethod(url, headers, body);
    }

    public String makeSignature(String timestamp) throws NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + serviceId + "/messages";

        String message = method +
                space +
                url +
                newLine +
                timestamp +
                newLine +
                accessKey;

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
