package com.vicentevalcarcel.refreshAheadCache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RefreshAheadCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefreshAheadCacheApplication.class, args);
	}

}
