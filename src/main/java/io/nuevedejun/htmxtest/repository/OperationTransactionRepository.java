package io.nuevedejun.htmxtest.repository;

import io.nuevedejun.htmxtest.entity.OperationTransaction;
import io.nuevedejun.htmxtest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationTransactionRepository extends JpaRepository<OperationTransaction, UUID> {
	Page<OperationTransaction> findAllByUserAndTransactionId(User user, UUID transactionId, Pageable pageable);

	Page<OperationTransaction> findAllByUserAndOperationId(User user, UUID operationId, Pageable pageable);
}
