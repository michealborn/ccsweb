package com.magfin.ccs.webccs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude={
		//springboot2.0集成activiti和security时activiti的包里也有security的class，需忽略
		org.activiti.spring.boot.SecurityAutoConfiguration.class,
})
@MapperScan("com.magfin.ccs.webccs")
public class WebccsApplication {


	public static void main(String[] args) {
		SpringApplication.run(WebccsApplication.class, args);
	}




}
