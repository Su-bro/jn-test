package com.osh.jntest.controller;

import com.osh.jntest.global.domain.ResponseFormat;
import com.osh.jntest.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class testController {

    private final TestService testService;


    @GetMapping()
    public ResponseFormat init() {
        ResponseFormat responseFormat = new ResponseFormat("INIT");
        responseFormat.setCode(1234);
        return responseFormat;
    }

    @GetMapping("/name")
    public ResponseFormat cntName(@RequestParam("name") String name) {
        return new ResponseFormat(testService.countUserByName(name));
    }

}
