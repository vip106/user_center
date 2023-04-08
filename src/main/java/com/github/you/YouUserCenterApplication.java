package com.github.you;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.github.you.mapper")
public class YouUserCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouUserCenterApplication.class, args);
    }

}
