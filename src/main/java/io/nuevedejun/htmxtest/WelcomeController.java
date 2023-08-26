package io.nuevedejun.htmxtest;

import io.nuevedejun.htmxtest.transaction.TransactionService;
import io.nuevedejun.htmxtest.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import static io.nuevedejun.htmxtest.UserData.FALLBACK_PAGE;
import static io.nuevedejun.htmxtest.UserData.USER_DATA_ATTR;
import static io.nuevedejun.htmxtest.ViewOptions.VIEW_OPTS_ATTR;

@Controller
@RequiredArgsConstructor
public class WelcomeController {
	private final TransactionService transactionService;
	private final UserInfoService userInfoService;

	@ModelAttribute(USER_DATA_ATTR)
	public UserData userData() {
		return new UserData(transactionService, userInfoService, FALLBACK_PAGE, null);
	}

	@ModelAttribute(VIEW_OPTS_ATTR)
	public ViewOptions viewOptions() {
		return new ViewOptions();
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
}
