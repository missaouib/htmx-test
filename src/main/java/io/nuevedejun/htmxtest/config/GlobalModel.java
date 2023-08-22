package io.nuevedejun.htmxtest.config;

import io.nuevedejun.htmxtest.transaction.TransactionModel;
import io.nuevedejun.htmxtest.transaction.TransactionService;
import io.nuevedejun.htmxtest.transaction.TransactionService.PageItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalModel {
	private static final int PAGE_SIZE = 20;
	private static final int PAGINATION_WINDOW = 5;
	private final TransactionService transactionService;

	/**
	 * Provide accessors for all relevant user information to be used in web templates.
	 */
	public class UserData {
		public List<TransactionModel> getTransactions() {
			return transactionService.getLatestTransactions(0, PAGE_SIZE);
		}

		public List<PageItem> getPagination() {
			return transactionService.getPagination(1, PAGE_SIZE, PAGINATION_WINDOW);
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
