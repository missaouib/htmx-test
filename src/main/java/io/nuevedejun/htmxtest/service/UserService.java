package io.nuevedejun.htmxtest.service;

import io.nuevedejun.htmxtest.entity.User;
import io.nuevedejun.htmxtest.entity.User.Preferences;
import io.nuevedejun.htmxtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public interface UserService {
	Optional<User> getUser();

	void savePageSizePreference(int size);

	@Service
	@RequiredArgsConstructor
	class Default implements UserService {
		private final UserRepository repository;

		@Override
		public Optional<User> getUser() {
			return repository.findAll().stream().findAny();
		}

		@Override
		@Transactional
		public void savePageSizePreference(int size) {
			User user = getUser().orElseThrow(() -> new NoSuchElementException("Unable to find user"));
			if (!Objects.equals(user.getPreferences().pageSize(), size)) {
				user.setPreferences(new Preferences(size));
				repository.save(user);
			}
		}
	}
}
