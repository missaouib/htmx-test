package io.nuevedejun.htmxtest.config;

import io.nuevedejun.htmxtest.UserDataProvider;
import io.nuevedejun.htmxtest.transaction.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public UserDataProvider userDataProvider(TransactionService transactionService) {
		return new UserDataProvider(transactionService);
	}
}
