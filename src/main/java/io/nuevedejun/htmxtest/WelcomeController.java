package io.nuevedejun.htmxtest;

import io.nuevedejun.htmxtest.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import static io.nuevedejun.htmxtest.UserData.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WelcomeController {
	private final TransactionService transactionService;

	@ModelAttribute(USER_DATA_ATTR)
	public UserData userData() {
		log.debug("Binding user data to model attribute: {}", USER_DATA_ATTR);
		return new UserData(transactionService, FALLBACK_PAGE, FALLBACK_SIZE);
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
}
