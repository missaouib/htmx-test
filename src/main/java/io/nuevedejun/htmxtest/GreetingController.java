package io.nuevedejun.htmxtest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
	@GetMapping("/greeting")
	@ResponseBody
	public String index() {
		return "Hi there!";
	}
}
