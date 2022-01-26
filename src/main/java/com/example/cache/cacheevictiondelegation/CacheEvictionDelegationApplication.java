package com.example.cache.cacheevictiondelegation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheEvictionDelegationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheEvictionDelegationApplication.class, args);
	}

}
