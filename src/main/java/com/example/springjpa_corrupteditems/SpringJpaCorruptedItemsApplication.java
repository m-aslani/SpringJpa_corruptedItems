package com.example.springjpa_corrupteditems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "com/example/springjpa_corrupteditems")
public class SpringJpaCorruptedItemsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringJpaCorruptedItemsApplication.class, args);

		JpaBasedRun jpaBasedRun = context.getBean(JpaBasedRun.class);
		jpaBasedRun.sampleRun();
	}

}
