package org.example.gateway.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/gateway/hello")
    public String hello() {
        return "gateway hello";
    }
}
