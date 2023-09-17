package io.nuevedejun.htmxtest.repository;

import io.nuevedejun.htmxtest.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
}
