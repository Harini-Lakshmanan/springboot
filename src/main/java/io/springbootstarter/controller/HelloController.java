package io.springbootstarter.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello",produces = MediaType.APPLICATION_JSON_VALUE)
    public String sayHi(){
        return "Hi";
    }
}
