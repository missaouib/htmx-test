package io.nuevedejun.htmxtest.config;

import io.nuevedejun.htmxtest.transaction.TransactionModel;
import io.nuevedejun.htmxtest.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalModel {
	private final TransactionService transactionService;

	/**
	 * Provide accessors for all relevant user information to be used in web templates.
	 */
	public class UserData {
		public List<TransactionModel> getTransactions() {
			return transactionService.getLatestTransactions(0, 20);
		}
	}

	private static final String ATTR_NAME = "userData";

	/**
	 * Binds user data to the model in all controllers to provide access in all applications routes.
	 */
	@ModelAttribute(ATTR_NAME)
	public UserData userData() {
		log.debug("Binding user data to model attribute: {}", ATTR_NAME);
		return new UserData();
	}
}
