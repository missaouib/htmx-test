package io.nuevedejun.htmxtest.repository;

import io.nuevedejun.htmxtest.entity.Operation;
import io.nuevedejun.htmxtest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {
	Page<Operation> findAllByUser(User user, Pageable pageable);
}
