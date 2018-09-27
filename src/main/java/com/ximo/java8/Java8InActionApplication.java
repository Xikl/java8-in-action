package com.ximo.java8;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zwz
 */
@EnableScheduling
@Slf4j
@SpringBootApplication
public class Java8InActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java8InActionApplication.class, args);
	}

//	@Scheduled(initialDelay = 1000, fixedDelay = 1000)
	@Scheduled(cron = "0/1 * * * * ?")
	public void contextLoads() throws InterruptedException {
		Thread.currentThread().setName("ssssssss");
		Thread.sleep(3000);
		log.info("任务完成");
	}
}
