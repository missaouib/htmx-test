package io.nuevedejun.htmxtest;

import io.nuevedejun.htmxtest.transaction.TransactionService;
import io.nuevedejun.htmxtest.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static io.nuevedejun.htmxtest.ModelData.MODEL_DATA_ATTR;
import static io.nuevedejun.htmxtest.UserData.FIRST_PAGE;

@Controller
@RequiredArgsConstructor
public class WelcomeController {
	private final TransactionService transactionService;
	private final UserInfoService userInfoService;

	@GetMapping("/welcome")
	public ModelAndView welcome() {
		final UserData userData = new UserData(transactionService, userInfoService, FIRST_PAGE, null);
		final ModelData model = new ModelData(userData);
		return new ModelAndView("welcome", MODEL_DATA_ATTR, model);
	}
}
