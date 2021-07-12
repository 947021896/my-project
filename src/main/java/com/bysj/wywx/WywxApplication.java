package com.bysj.wywx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.bysj.wywx.mapper")
public class WywxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WywxApplication.class, args);
	}

}
