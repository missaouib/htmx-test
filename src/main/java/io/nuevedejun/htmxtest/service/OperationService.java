package io.nuevedejun.htmxtest.service;

import io.nuevedejun.htmxtest.dto.OperationData;
import io.nuevedejun.htmxtest.entity.Operation;
import io.nuevedejun.htmxtest.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

public interface OperationService {
	Page<OperationData> getLatestOperations(int page, int size);

	@Service
	@RequiredArgsConstructor
	class Default implements OperationService {
		private final OperationRepository repository;

		@Override
		public Page<OperationData> getLatestOperations(int page, int size) {
			var pageable = PageRequest.of(page, size, Sort.Direction.DESC, "date");
			return repository.findAll(pageable).map(this::toDTO);
		}

		private OperationData toDTO(Operation operation) {
			return new OperationData(
					operation.getDate(),
					"",
					operation.getCategory(),
					operation.getNote(),
					operation.getAmount()
			);
		}
	}
}
