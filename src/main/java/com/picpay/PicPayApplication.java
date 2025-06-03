package com.picpay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableScheduling
@EnableJpaRepositories({
    "com.picpay.shared.domain.repositories",
    "com.picpay.account_management.domain.repositories",
    "com.picpay.transaction_processing.domain.repositories"
})
public class PicPayApplication {

	public static void main(String[] args) {
        SpringApplication.run(PicPayApplication.class, args);
	}

}
