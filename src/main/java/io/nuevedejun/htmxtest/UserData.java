package io.nuevedejun.htmxtest;

import io.nuevedejun.htmxtest.transaction.TransactionModel;
import io.nuevedejun.htmxtest.transaction.TransactionService;
import io.nuevedejun.htmxtest.transaction.TransactionService.PageItem;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Provide accessors for relevant user information to be used in web templates.
 */
@RequiredArgsConstructor
public class UserData {
	public static final String USER_DATA_ATTR = "userData";
	public static final int FALLBACK_PAGE = 1;
	public static final int FALLBACK_SIZE = 20;
	public static final int FALLBACK_WINDOW = 5;

	private final TransactionService transactionService;
	private final int page;
	private final int size;

	public List<TransactionModel> getTransactions() {
		return transactionService.getLatestTransactions(page - 1, size);
	}

	public List<PageItem> getPagination() {
		return transactionService.getPagination(page, size, FALLBACK_WINDOW);
	}
}
