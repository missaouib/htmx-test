package io.nuevedejun.htmxtest;

import io.nuevedejun.htmxtest.transaction.TransactionModel;
import io.nuevedejun.htmxtest.transaction.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserDataProvider {
	private static final int PAGE_SIZE = 20;
	private static final int PAGINATION_WINDOW = 5;

	private final TransactionService transactionService;

	public UserData get() {
		return new UserData();
	}

	/**
	 * Provide accessors for relevant user information to be used in web templates.
	 */
	public class UserData {
		public List<TransactionModel> getTransactions() {
			return transactionService.getLatestTransactions(0, PAGE_SIZE);
		}

		public List<TransactionService.PageItem> getPagination() {
			return transactionService.getPagination(1, PAGE_SIZE, PAGINATION_WINDOW);
		}
	}
}
