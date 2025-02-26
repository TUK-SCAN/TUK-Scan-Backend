package com.tookscan.tookscan.core.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tookscan.tookscan.core.constant.Constants;
import com.tookscan.tookscan.security.application.dto.response.DefaultJsonWebTokenDto;
import com.tookscan.tookscan.security.application.dto.response.OauthJsonWebTokenDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HttpServletUtil {

    @Value("${web-engine.client-url}")
    private String clientUrl;

    @Value("${web-engine.cookie-domain}")
    private String cookieDomain;

    @Value("${json-web-token.refresh-token-expire-period}")
    private Long refreshTokenExpirePeriod;

    private final ObjectMapper objectMapper;

    public void onSuccessRedirectResponseWithJWTCookie(
            String redirectPath,
            HttpServletResponse response,
            OauthJsonWebTokenDto tokenDto
    ) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.CREATED.value());

        // 최초 로그인이 아닐 시
        if (tokenDto.getTemporaryToken() == null) {
            CookieUtil.addCookie(
                    response,
                    cookieDomain,
                    Constants.ACCESS_TOKEN,
                    tokenDto.getAccessToken()
            );

            CookieUtil.addSecureCookie(
                    response,
                    cookieDomain,
                    Constants.REFRESH_TOKEN,
                    tokenDto.getRefreshToken(),
                    (int) (refreshTokenExpirePeriod / 1000L)
            );
        }
        // 최초 로그인 시 (임시 토큰 발급)
        else {
            CookieUtil.addCookie(
                    response,
                    cookieDomain,
                    Constants.TEMPORARY_TOKEN,
                    tokenDto.getTemporaryToken()
            );
        }

        response.sendRedirect(String.format("%s/%s", clientUrl, redirectPath));
    }

    public void onSuccessBodyResponseWithJWTCookie(
            HttpServletResponse response,
            DefaultJsonWebTokenDto tokenDto
    ) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.CREATED.value());

        CookieUtil.addCookie(
                response,
                cookieDomain,
                Constants.ACCESS_TOKEN,
                tokenDto.getAccessToken()
        );
        CookieUtil.addSecureCookie(
                response,
                cookieDomain,
                Constants.REFRESH_TOKEN,
                tokenDto.getRefreshToken(),
                (int) (refreshTokenExpirePeriod / 1000L)
        );

        Map<String, Object> result = new HashMap<>();

        result.put("success", true);
        result.put("data", null);
        result.put("error", null);

        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    public void onSuccessBodyResponseWithJWTBody(
            HttpServletResponse response,
            DefaultJsonWebTokenDto tokenDto
    ) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.CREATED.value());

        Map<String, Object> result = new HashMap<>();

        result.put("success", true);
        result.put("data", Map.of(
                        "access_token", tokenDto.getAccessToken(),
                        "refresh_token", tokenDto.getRefreshToken()
                )
        );
        result.put("error", null);

        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    public void onSuccessBodyResponseWithOauthJWTBody(
            HttpServletResponse response,
            OauthJsonWebTokenDto tokenDto
    ) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.CREATED.value());

        Map<String, Object> data = new HashMap<>();
        data.put("temporary_token", tokenDto.getTemporaryToken());
        data.put("access_token", tokenDto.getAccessToken());
        data.put("refresh_token", tokenDto.getRefreshToken());

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", data);
        result.put("error", null);

        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    public void onSuccessBodyResponse(
            HttpServletResponse response,
            HttpStatus httpStatus
    ) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(httpStatus.value());

        Map<String, Object> result = new HashMap<>();

        if (httpStatus != HttpStatus.NO_CONTENT) {
            result.put("success", true);
            result.put("data", null);
            result.put("error", null);

            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }
}
