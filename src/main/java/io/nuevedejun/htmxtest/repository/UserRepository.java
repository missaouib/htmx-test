package io.nuevedejun.htmxtest.repository;

import io.nuevedejun.htmxtest.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends ListCrudRepository<User, UUID> {
	Optional<User> findByUsername(String username);
}
