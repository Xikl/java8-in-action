package com.ximo.java8.chap14;

import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * 联系
 *
 * @author xikl
 * @date 2018/12/12
 */
public class Currying {

    public static void main(String[] args) {
        UnaryOperator<Double> convertCtoF = curriedConverter(9.0/5, 32D);
        convertCtoF.apply(24D);
        UnaryOperator<Double> convertUSDtoGBP = curriedConverter(0.6, 0D);
        UnaryOperator<Double> convertKmtoMi = curriedConverter(0.6214, 0D);

        DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0/9, 0);
        System.out.println(convertFtoC.applyAsDouble(98.6));
    }



    private static double converter(double x, double y, double z) {
        return x * y + z;
    }

    private static UnaryOperator<Double> curriedConverter(Double y, Double z) {
        return x -> x * y + z;
    }

    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }



}
