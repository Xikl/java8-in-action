package com.ximo.java8.chap14;

/**
 * @author xikl
 * @date 2018/12/12
 */
public class PersistentTrainJourney {


    static class TrainJourney {
        /** 价格 */
        private Integer price;

        public TrainJourney onward;

        public TrainJourney(Integer price, TrainJourney onward) {
            this.price = price;
            this.onward = onward;
        }

        static TrainJourney link(TrainJourney a, TrainJourney b) {
            if (a == null) {
                return b;
            }

            TrainJourney next = a;
            while (next.onward != null) {
                next = next.onward;
            }

            next.onward = b;
            return a;
        }

        /**
         * 达到对象的复制
         *
         * @param a a对象
         * @param b b对象
         * @return 一个新的对象
         */
        static TrainJourney append(TrainJourney a, TrainJourney b) {
            return a == null ? b : new TrainJourney(a.price, append(a.onward, b));
        }



    }



}
