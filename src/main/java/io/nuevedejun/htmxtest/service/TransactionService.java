package io.nuevedejun.htmxtest.service;

import io.nuevedejun.htmxtest.dto.TransactionData;
import io.nuevedejun.htmxtest.entity.Transaction;
import io.nuevedejun.htmxtest.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
	private final TransactionRepository repository;

	public Page<TransactionData> getLatestTransactions(int page, int size) {
		var pageable = PageRequest.of(page, size, Sort.Direction.DESC, "date");
		return repository.findAll(pageable).map(this::toDTO);
	}

	private TransactionData toDTO(Transaction transaction) {
		return new TransactionData(
				transaction.getDate(),
				transaction.getAccount(),
				transaction.getCategory(),
				transaction.getNote(),
				transaction.getAmount()
		);
	}
}
