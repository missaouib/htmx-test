package io.nuevedejun.htmxtest.repository;

import io.nuevedejun.htmxtest.entity.OperationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationTransactionRepository extends JpaRepository<OperationTransaction, UUID> {
}
