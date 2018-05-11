package com.ximo.java8.controller.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 朱文赵
 * @date 2018/5/11 15:33
 * @description
 */
@Data
public class Test {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String pwd;

}
