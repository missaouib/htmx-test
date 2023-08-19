package io.nuevedejun.htmxtest.transaction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {
	@GetMapping
	public String index() {
		return "transactions::list";
	}
}
