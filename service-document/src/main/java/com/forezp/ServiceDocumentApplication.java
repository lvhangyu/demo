package com.forezp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

/**
 * @author lenovo
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.forezp.mapper")
public class ServiceDocumentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDocumentApplication.class, args);
	}

	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}



}
