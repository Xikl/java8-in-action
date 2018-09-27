package com.ximo.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class Java8InActionApplicationTests {


	@Test
	public void contextLoads() throws ExecutionException, InterruptedException {
//        double aa = 11;
//        Object[] objects = new Object[3];
//        objects[0] = 1;
//        objects[1] = aa;
//        objects[2] = "sss";
//
//        for (Object object : objects) {
//            System.out.println(object instanceof Double);
//        }
//
//        int[] task = getThreadExecutionNumberIndex(1, 99999);
//        System.out.println(Arrays.toString(task));
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        int length = task.length - 1;
//        CountDownLatch countDownLatch = new CountDownLatch(length);
//        AtomicInteger result = new AtomicInteger(0);
//        for (int i = 0; i < length; i++) {
//            int i1 = task[i];
//            int i2 = task[i + 1];
////            Callable<Boolean> callable = new TaskTest(i1, i2, countDownLatch);
//            TaskTest2 taskTest2 = new TaskTest2(i1, i2, countDownLatch, result);
//            executorService.execute(taskTest2);
//
//        }
//        try {
//            countDownLatch.await();
//            log.info("全部结束, result: {}", result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private int[] getThreadExecutionNumberIndex(int firstRowNum, int lastRowNum) {
        // 总的条数
        int totalRowCount = lastRowNum - firstRowNum + 1;
        // 分为多少个任务 如 100003 / 10000 = 10 则为10个任务
        int taskNum = (int)Math.ceil((double)totalRowCount / 10000);

        if (taskNum == 0) {
            return new int[]{firstRowNum, lastRowNum};
        }
        //任务数组 存下标
        int[] taskArray = new int[taskNum + 1];

        taskArray[0] = firstRowNum;
        int currentCount = 0;
        for (int i = 1; i < taskNum; i++) {
            taskArray[i] = taskArray[i - 1] + 10000;
            currentCount += 10000;
        }
        taskArray[taskNum] = totalRowCount - currentCount + taskArray[taskNum - 1];
        return taskArray;
    }


    private class TaskTest implements Callable<Boolean> {

        private int i1;

        private int i2;

        private CountDownLatch countDownLatch;

        public TaskTest(int i1, int i2, CountDownLatch countDownLatch) {
            this.i1 = i1;
            this.i2 = i2;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Boolean call() {
            try {
                Thread.sleep(2000);
                log.info("i1 --- " + i1);
                log.info("i2 --- " + i2);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private class TaskTest2 implements Runnable {

        private int i1;

        private int i2;

        private CountDownLatch countDownLatch;

        private AtomicInteger atomicInteger;

        public TaskTest2(int i1, int i2, CountDownLatch countDownLatch, AtomicInteger atomicInteger) {
            this.i1 = i1;
            this.i2 = i2;
            this.countDownLatch = countDownLatch;
            this.atomicInteger = atomicInteger;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                log.info("i1 --- " + i1);
                log.info("i2 --- " + i2);
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                atomicInteger.incrementAndGet();
            }
        }
    }


}
