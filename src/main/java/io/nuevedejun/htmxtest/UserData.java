package io.nuevedejun.htmxtest;

import io.nuevedejun.htmxtest.entity.UserInfo;
import io.nuevedejun.htmxtest.entity.UserInfo.Preferences;
import io.nuevedejun.htmxtest.transaction.TransactionModel;
import io.nuevedejun.htmxtest.transaction.TransactionService;
import io.nuevedejun.htmxtest.transaction.TransactionService.PageItem;
import io.nuevedejun.htmxtest.user.UserInfoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Provide accessors for relevant user information to be used in web templates.
 */
@AllArgsConstructor
public class UserData {
	public static final int FIRST_PAGE = 1;
	public static final int FALLBACK_SIZE = ModelData.PAGE_SIZES.first();
	public static final int FALLBACK_WINDOW = 3;

	private final TransactionService transactionService;
	private final UserInfoService userInfoService;
	@Getter
	private final int page;

	/**
	 * Page size. If null, get the user preference from DB.
	 */
	@Nullable
	private Integer size;

	public List<TransactionModel> getTransactions() {
		return transactionService.getLatestTransactions(page - 1, getSize());
	}

	public List<PageItem> getPagination() {
		return transactionService.getPagination(page, getSize(), FALLBACK_WINDOW);
	}

	public int getSize() {
		if (size == null) {
			size = userInfoService.getUserInfo()
					.map(UserInfo::getPreferences)
					.map(Preferences::getPageSize)
					.orElse(FALLBACK_SIZE);
		}
		return size;
	}
}
