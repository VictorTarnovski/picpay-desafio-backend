package com.picpay.account_management;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan("com.picpay.*")
@EntityScan("com.picpay.*")
@EnableKafka
@EnableScheduling
@EnableJpaRepositories("com.picpay.*")
public class Application {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
	}

}
