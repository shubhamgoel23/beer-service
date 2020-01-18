package com.spring.beerservice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;


//we can directly mentuon these annotation in main class
//following best practice to have configuration in separate package rather than separated everywhere in code
@EnableCaching
@Configuration
public class CacheConfig {

}
