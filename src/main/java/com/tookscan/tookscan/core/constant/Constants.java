package com.tookscan.tookscan.core.constant;

import java.util.List;

public class Constants {

    // JWT
    public static String ACCOUNT_ID_ATTRIBUTE_NAME = "ACCOUNT_ID";
    public static String ACCOUNT_ID_CLAIM_NAME = "aid";
    public static String ACCOUNT_ROLE_CLAIM_NAME = "rol";

    // HEADER
    public static String BEARER_PREFIX = "Bearer ";
    public static String AUTHORIZATION_HEADER = "Authorization";

    // COOKIE
    public static String ACCESS_TOKEN = "access_token";
    public static String REFRESH_TOKEN = "refresh_token";
    
    /**
     * 인증이 필요 없는 URL
     */
    public static List<String> NO_NEED_AUTH_URLS = List.of(
            // Authentication/Authorization
            "/v1/users/auth/validations/id",
            "/v1/auth/authentication-code",
            "/v1/auth/reissue/token",
            "/v1/auth/reissue/authentication-code",
            "/v1/users/auth/sign-up",
            "/v1/admins/auth/sign-up",
            "/v1/users/oauth/sign-up",
            "/v1/users/auth/serial-id",
            "/v1/users/auth/authentication-code",
            "/v1/users/auth/reissue/password",
            "/v1/auth/login",

            // Guest
            "/v1/guests/**",

            // Swagger
            "/api-docs.html",
            "/api-docs/**",
            "/swagger-ui/**",
            "/v3/**"
    );

    /**
     * Swagger 에서 사용하는 URL
     */
    public static List<String> SWAGGER_URLS = List.of(
            "/api-docs.html",
            "/api-docs",
            "/swagger-ui",
            "/v3"
    );

    /**
     * 사용자 URL
     */
    public static List<String> USER_URLS = List.of(
            "/v1/**"
    );
}
