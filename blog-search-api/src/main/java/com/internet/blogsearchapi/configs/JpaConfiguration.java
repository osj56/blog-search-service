package com.internet.blogsearchapi.configs;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.internet.blogsearchcommon")
@EntityScan(basePackages = "com.internet.blogsearchcommon")
@Configuration
public class JpaConfiguration {

}
