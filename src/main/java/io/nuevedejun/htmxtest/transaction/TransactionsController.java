package io.nuevedejun.htmxtest.transaction;

import io.nuevedejun.htmxtest.UserData;
import io.nuevedejun.htmxtest.ViewOptions;
import io.nuevedejun.htmxtest.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Objects;

import static io.nuevedejun.htmxtest.UserData.FALLBACK_PAGE;
import static io.nuevedejun.htmxtest.UserData.USER_DATA_ATTR;
import static io.nuevedejun.htmxtest.ViewOptions.VIEW_OPTS_ATTR;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {
	private final TransactionService service;
	private final UserInfoService userInfoService;

	@GetMapping
	public ModelAndView transactions(@Nullable @RequestParam Integer page, @Nullable @RequestParam Integer size) {
		final int goodPage = validatePage(page);
		final Integer goodSize = validateSize(size);
		var userData = new UserData(service, userInfoService, goodPage, goodSize);
		final Map<String, ?> model = Map.of(
				USER_DATA_ATTR, userData,
				VIEW_OPTS_ATTR, new ViewOptions());
		return new ModelAndView("transactions", model);
	}

	private int validatePage(Integer page) {
		if (page == null) return FALLBACK_PAGE;
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater than zero");
		}
		return page;
	}

	/**
	 * Validates the page size. This method has side effects because it will update the user page size preference if
	 * required.
	 */
	@Nullable
	private Integer validateSize(Integer size) {
		if (size == null) return null;

		final Integer picked = ViewOptions.PAGE_SIZES.floor(size);
		if (picked != null) savePageSizePreference(picked);
		return picked;
	}

	private void savePageSizePreference(int picked) {
		userInfoService.getUserInfo().ifPresent(info -> {
			if (!Objects.equals(info.getPreferences().getPageSize(), picked)) {
				info.getPreferences().setPageSize(picked);
				userInfoService.save(info);
			}
		});
	}
}
