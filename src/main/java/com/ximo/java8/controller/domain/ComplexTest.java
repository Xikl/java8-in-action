package com.ximo.java8.controller.domain;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/5/11 16:02
 * @description
 */
@Data
public class ComplexTest {

    @NotBlank(message = "label不能为空")
    private String label;

    @Valid
    private List<Test> tests;

}
