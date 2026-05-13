package com.msa4spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

// @RestController : REST API 컨트롤러
@RestController
// @RequestMapping : 클래스 레벨의 맵핑
@RequestMapping("/api")
public class RequestMappingController {
    // 메소드에 맵핑 ()는 아규먼트 작성가능
    @GetMapping("/test")
    public String test(){
        return "GET테스트";
    }
    
    @PostMapping("/test")
    public String testPost(){
        return "POST TEST";
    }
}

