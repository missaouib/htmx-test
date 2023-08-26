package io.nuevedejun.htmxtest.repository;

import io.nuevedejun.htmxtest.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
