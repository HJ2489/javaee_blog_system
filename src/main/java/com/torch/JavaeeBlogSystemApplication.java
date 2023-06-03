package com.torch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot启动类
 */

@EnableScheduling   // 让SpringBoot开启定时任务注解功能支持
@SpringBootApplication
public class JavaeeBlogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeeBlogSystemApplication.class, args);
    }

}
