package com.party.giftzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@ComponentScan
@SpringBootApplication
@EnableJpaRepositories("com.party.giftzzle.repository")
public class GiftzzleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftzzleApplication.class, args);
	}
}
