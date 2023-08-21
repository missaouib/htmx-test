package io.nuevedejun.htmxtest.transaction;

import io.nuevedejun.htmxtest.entity.Transaction;
import io.nuevedejun.htmxtest.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository repository;

	public List<TransactionModel> getLatestTransactions(int page, int size) {
		var pageable = PageRequest.of(page, size, Sort.Direction.DESC, "date");
		return repository.findAll(pageable).stream()
				.map(this::toModel).toList();
	}

	private TransactionModel toModel(Transaction transaction) {
		return new TransactionModel(
				transaction.getDate(),
				transaction.getAccount(),
				transaction.getCategory(),
				transaction.getNote(),
				transaction.getAmount()
		);
	}
}
