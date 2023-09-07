package io.nuevedejun.htmxtest.controller;

import io.nuevedejun.htmxtest.model.ModelData;
import io.nuevedejun.htmxtest.model.UserData;
import io.nuevedejun.htmxtest.service.TransactionService;
import io.nuevedejun.htmxtest.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static io.nuevedejun.htmxtest.model.ModelData.MODEL_DATA_ATTR;
import static io.nuevedejun.htmxtest.model.UserData.FIRST_PAGE;

@RequestMapping("/app")
public interface AppController {
	@GetMapping
	ModelAndView app(@RequestParam @Nullable Integer page, @RequestParam @Nullable Integer size);

	@Controller
	@RequiredArgsConstructor
	class Default implements AppController {
		private final TransactionService transactionService;
		private final UserInfoService userInfoService;

		@Override
		public ModelAndView app(@Nullable Integer page, @Nullable Integer size) {
			final int goodPage = validatePage(page);
			final Integer goodSize = validateSize(size);
			final UserData userData = new UserData(transactionService, userInfoService, goodPage, goodSize);
			final ModelData model = new ModelData(userData);
			return new ModelAndView("app", MODEL_DATA_ATTR, model);
		}

		private int validatePage(@Nullable Integer page) {
			if (page == null || page < 1) return FIRST_PAGE;
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
			if (picked != null) userInfoService.savePageSizePreference(picked);
			return picked;
		}
	}
}
