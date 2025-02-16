package com.tookscan.tookscan.core.utility;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.tookscan.tookscan.core.exception.error.ErrorCode;
import com.tookscan.tookscan.core.exception.type.CommonException;
import java.util.Map;
import java.util.Objects;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RestClientUtil {

    private final RestClient restClient = RestClient.create();

    public Map<String, Object> sendGetMethod(String url, HttpHeaders headers) {
        return Objects.requireNonNull(
                restClient.get()
                        .uri(url)
                        .headers(httpHeaders -> httpHeaders.addAll(headers))
                        .retrieve()
                        .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                            throw new CommonException(ErrorCode.INVALID_ARGUMENT);
                        })
                        .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                            throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
                        })
                        // toEntity(...) 말고 toEntity(Map.class)도 가능
                        .toEntity(Map.class)
                        .getBody()
        );
    }

    public Map<String, Object> sendPostMethod(String url, HttpHeaders headers, String body) {
        try {
            return Objects.requireNonNull(
                    restClient.post()
                            .uri(url)
                            .headers(httpHeaders -> httpHeaders.addAll(headers))
                            .contentType(APPLICATION_JSON)
                            .body(body)
                            .retrieve()
                            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                                throw new CommonException(ErrorCode.INVALID_ARGUMENT);
                            })
                            .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                                throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
                            })
                            .toEntity(Map.class)
                            .getBody()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error sending POST request", e);
        }
    }

    // TODO: 위아래 통합
    public JSONObject sendPost(String url, HttpHeaders headers, String body) {
        try {
            return new JSONObject(Objects.requireNonNull(restClient.post()
                    .uri(url)
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .contentType(APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                        throw new CommonException(ErrorCode.INVALID_ARGUMENT);
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                        throw new CommonException(ErrorCode.INTERNAL_SERVER_ERROR);
                    })
                    .toEntity(JSONObject.class).getBody()));
        } catch (Exception e) {
            throw new RuntimeException("Error sending POST request", e);
        }
    }
}
