package io.nuevedejun.htmxtest.transaction;

import io.nuevedejun.htmxtest.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static io.nuevedejun.htmxtest.UserData.*;

@Controller
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionsController {
	private final TransactionService service;

	@GetMapping
	public ModelAndView transactions(@Nullable @RequestParam Integer page, @Nullable @RequestParam Integer size) {
		validatePage(page);
		validateSize(size);
		var userData = new UserData(service, coalesce(page, FALLBACK_PAGE), coalesce(size, FALLBACK_SIZE));
		return new ModelAndView("transactions", USER_DATA_ATTR, userData);
	}

	private void validatePage(Integer page) {
		if (page != null && page < 1) {
			throw new IllegalArgumentException("page must be greater than zero");
		}
	}

	private void validateSize(Integer size) {
		if (size != null && size < 1) {
			throw new IllegalArgumentException("size must be greater than zero");
		}
	}

	@NonNull
	public static <T> T coalesce(@Nullable T a, @NonNull T b) {
		return a == null ? b : a;
	}
}
