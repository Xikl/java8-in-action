package com.ximo.java8.chap8;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 朱文赵
 * @date 2018/3/27 19:40
 * @description 观察这模式
 */
public class ObserverMain {

    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObservers("The queen said her favourite book is Java 8 in Action!");

        Feed lambdaFeed = new Feed();
        lambdaFeed.registerObserver(news -> {
            if (StringUtils.hasText("Money")) {
                System.out.println("纽约大新闻：" + news);
            }
        });

        lambdaFeed.registerObserver(news -> {
            if(StringUtils.hasText("queen")){
                System.out.println("Yet another news in London... " + news);
            }
        });

        lambdaFeed.notifyObservers("Money Money Give me money");

    }

    interface Observer {
        void inform(String news);
    }

    interface Subject {
        void registerObserver(Observer o);

        void notifyObservers(String news);

    }

    static private class NYTimes implements Observer {

        @Override
        public void inform(String news) {
            if (StringUtils.hasText("Money")) {
                System.out.println("纽约大新闻：" + news);
            }
        }
    }

    static private class Guardian implements Observer{
        @Override
        public void inform(String news) {
            if(StringUtils.hasText("queen")){
                System.out.println("Yet another news in London... " + news);
            }
        }
    }

    static private class LeMonde implements Observer{
        @Override
        public void inform(String news) {
            if(StringUtils.hasText("wine")){
                System.out.println("Today cheese, wine and news! " + news);
            }
        }
    }

    static private class Feed implements Subject {

        private static final List<Observer> OBSERVERS = new ArrayList<>();

        /**
         * 注册观察者
         * @param o 观察者
         */
        @Override
        public void registerObserver(Observer o) {
            OBSERVERS.add(o);
        }

        /**
         * 通知所有人
         * @param news 新闻
         */
        @Override
        public void notifyObservers(String news) {
            OBSERVERS.forEach( o -> o.inform(news));
        }
    }

}
