package com.ximo.java8.controller;

import com.ximo.java8.controller.domain.ComplexTest;
import com.ximo.java8.controller.domain.Test;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static java.util.stream.Collectors.joining;

/**
 * @author 朱文赵
 * @date 2018/5/11 15:33
 * @description
 */
@RestController
public class TestController {

    @PostMapping("/test")
    public String test(@Valid @RequestBody Test test, BindingResult errors) {
        if (errors.hasErrors()) {
            String errorsMsg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(joining(";"));
            throw new RuntimeException(errorsMsg);
        }
        System.out.println(test);
        return "你好";
    }

    @PostMapping("/test2")
    public String test(@Valid @RequestBody ComplexTest complexTest, BindingResult errors) {
        if (errors.hasErrors()) {
            String errorsMsg = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(joining(";"));
            throw new RuntimeException(errorsMsg);
        }
        System.out.println(complexTest);
        return "你好";
    }

}
