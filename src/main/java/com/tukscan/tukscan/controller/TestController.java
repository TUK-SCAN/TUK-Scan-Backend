package com.tukscan.tukscan.controller;

import com.tukscan.tukscan.util.response.CustomApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    @RequestMapping("")
    public CustomApiResponse<?> test() {
        return CustomApiResponse.createSuccess(200, "테스트 성공", null);
    }
}
