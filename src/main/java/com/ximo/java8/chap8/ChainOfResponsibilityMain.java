package com.ximo.java8.chap8;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author 朱文赵
 * @date 2018/3/29 8:57
 * @description 责任链模式
 */
public class ChainOfResponsibilityMain {

    public static void main(String[] args) {
        BaseProcessingObject<String> p1 = new HandleTextProcessing();
        BaseProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccesser(p2);
        String input = "Aren't labdas really sexy?!!";
        String result = p1.handle(input);
        System.out.println(result);

        //java8模式
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> secondProcessing = (text) -> text.replaceAll("labdas", "lambda");
        Function<String, String> pipLine = headerProcessing.andThen(secondProcessing);
        String result2 = pipLine.apply("Aren't labdas really sexy?!!");
        System.out.println(result2);
    }

    static private abstract class BaseProcessingObject<T> {
        protected BaseProcessingObject<T> successer;

        public void setSuccesser(BaseProcessingObject<T> successer) {
            this.successer = successer;
        }

        public T handle(T input) {
            T result = handleWork(input);
            if (successer != null) {
                return successer.handle(result);
            }
            return result;
        }

        abstract protected T handleWork(T input);
    }

    static private class HandleTextProcessing extends BaseProcessingObject<String>{

        @Override
        protected String handleWork(String input) {
            return "From Raoul, Mario and Alan: " + input;
        }
    }

    static private class SpellCheckerProcessing extends BaseProcessingObject<String>{

        @Override
        protected String handleWork(String input) {
            return input.replaceAll("labdas", "lambda");
        }
    }


}
