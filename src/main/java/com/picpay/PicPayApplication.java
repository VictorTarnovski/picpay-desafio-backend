package com.picpay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories({
    "com.picpay.account_management.domain.repositories",
    "com.picpay.transaction_processing.domain.repositories"
})
public class PicPayApplication {

	public static void main(String[] args) {
        SpringApplication.run(PicPayApplication.class, args);
	}

}
