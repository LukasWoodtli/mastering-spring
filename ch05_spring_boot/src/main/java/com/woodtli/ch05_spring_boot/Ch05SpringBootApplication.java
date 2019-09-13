package com.woodtli.ch05_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Ch05SpringBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Ch05SpringBootApplication.class, args);
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		for (String beanName: beanNames) {
			System.out.println(beanName);
		}
	}

}
