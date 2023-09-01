package io.nuevedejun.htmxtest.transaction;

import io.nuevedejun.htmxtest.ModelData;
import io.nuevedejun.htmxtest.UserData;
import io.nuevedejun.htmxtest.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

import static io.nuevedejun.htmxtest.ModelData.MODEL_DATA_ATTR;
import static io.nuevedejun.htmxtest.UserData.FIRST_PAGE;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {
	private final TransactionService service;
	private final UserInfoService userInfoService;

	@GetMapping
	public ModelAndView transactions(@RequestParam @Nullable Integer page, @RequestParam @Nullable Integer size) {
		final int goodPage = validatePage(page);
		final Integer goodSize = validateSize(size);
		final UserData userData = new UserData(service, userInfoService, goodPage, goodSize);
		final ModelData model = new ModelData(userData);
		return new ModelAndView("transactions", MODEL_DATA_ATTR, model);
	}

	private int validatePage(@Nullable Integer page) {
		if (page == null) return FIRST_PAGE;
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
	private Integer validateSize(@Nullable Integer size) {
		if (size == null) return null;

		final Integer picked = ModelData.PAGE_SIZES.floor(size);
		if (picked != null) savePageSizePreference(picked);
		return picked;
	}

	private void savePageSizePreference(int picked) { // TODO move this to the service
		userInfoService.getUserInfo().ifPresent(info -> {
			if (!Objects.equals(info.getPreferences().getPageSize(), picked)) {
				info.getPreferences().setPageSize(picked);
				userInfoService.save(info);
			}
		});
	}
}
