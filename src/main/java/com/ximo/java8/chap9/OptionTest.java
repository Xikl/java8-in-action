package com.ximo.java8.chap9;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * @author 朱文赵
 * @date 2018/4/20 8:57
 * @description
 */
public class OptionTest {

    public void nullSafeFind(Optional<String> str, Optional<Integer> no) {
        str.flatMap(e -> no.map(a -> e + a));
        //不推荐使用基础类型的optional对象 因为没有map 或 flatMa等方法
        OptionalInt optionalInt = OptionalInt.of(22);
    }


}
