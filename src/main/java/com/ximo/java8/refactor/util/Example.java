package com.ximo.java8.refactor.util;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 朱文赵
 * @date 2018/4/24 15:45
 * @description
 */
@Data
@Builder
public class Example {

    private String name;

    private Integer code;

    private boolean share;

    private Integer age;

}
