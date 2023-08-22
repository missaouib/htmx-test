package io.nuevedejun.htmxtest;

import io.nuevedejun.htmxtest.UserDataProvider.UserData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WelcomeController {
	private static final String USER_DATA_ATTR = "userData";

	private final UserDataProvider userDataProvider;

	@ModelAttribute(USER_DATA_ATTR)
	public UserData userData() {
		log.debug("Binding user data to model attribute: {}", USER_DATA_ATTR);
		return userDataProvider.get();
	}

	@GetMapping("/landing")
	public String landing() {
		return "landing::contents";
	}
}
