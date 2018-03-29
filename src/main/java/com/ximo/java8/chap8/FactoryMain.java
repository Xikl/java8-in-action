package com.ximo.java8.chap8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author 朱文赵
 * @date 2018/3/29 19:29
 * @description 工厂模式
 */
public class FactoryMain {

    public static void main(String[] args) {
        Product p = ProductFactory.create("loan");

        Supplier<Product> productSupplier =  Loan::new;
        Product product = productSupplier.get();

        Product loan = ProductFactory.createByLambda("loan");
    }

    static private class ProductFactory {
        public static Product create(String name) {
            switch (name) {
                case "loan":
                    return new Loan();
                case "stock":
                    return new Stock();
                case "bond":
                    return new Bond();
                default:
                    throw new RuntimeException("No such product " + name);
            }
        }

        public static Product createByLambda(String name) {
            Supplier<Product> p = map.get(name);
            if (p == null) {
                throw new RuntimeException("No such product " + name);
            }
            return p.get();
        }
    }

    static private interface Product {
    }

    static private class Loan implements Product {

    }

    static private class Stock implements Product {

    }

    static private class Bond implements Product {

    }

    final static private Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}
