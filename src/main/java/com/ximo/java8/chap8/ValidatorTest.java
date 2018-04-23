package com.ximo.java8.chap8;

/**
 * @author 朱文赵
 * @date 2018/4/19 16:08
 * @description
 */
public class ValidatorTest {


    public static interface ValidationStrategy {
        boolean execute(String s);
    }

    public static class Validator {
        private ValidationStrategy validationStrategy;

        public Validator(ValidationStrategy validationStrategy) {
            this.validationStrategy = validationStrategy;
        }

        public boolean validator(String s) {
            return validationStrategy.execute(s);
        }
    }


    public static void main(String[] args) {
        Validator validator = new Validator(s -> s.matches("[a-z]+"));
        validator.validator("ssss");
    }



}
