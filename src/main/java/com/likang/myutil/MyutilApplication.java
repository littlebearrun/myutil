package com.likang.myutil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.likang.myutil.mapper")
public class MyutilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyutilApplication.class, args);
	}
}
