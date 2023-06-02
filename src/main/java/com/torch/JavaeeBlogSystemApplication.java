package com.torch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类
 */

@SpringBootApplication(scanBasePackages={ "com.torch.service.impl", "com.torch.dao"})
public class JavaeeBlogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaeeBlogSystemApplication.class, args);
    }

}
