package io.nuevedejun.htmxtest.service;

import io.nuevedejun.htmxtest.entity.User;
import io.nuevedejun.htmxtest.entity.User.Preferences;
import io.nuevedejun.htmxtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public interface UserService {
	User getUser();

	Preferences getUserPreferences();

	void savePageSizePreference(int size);

	/**
	 * This implementation operates on the authenticated user.
	 */
	@Service
	@RequiredArgsConstructor
	class Default implements UserService {
		private final UserRepository repository;

		@Setter
		private SecurityContextHolderStrategy securityContextHolderStrategy =
				SecurityContextHolder.getContextHolderStrategy();

		@Override
		public User getUser() {
			Authentication authentication = securityContextHolderStrategy.getContext().getAuthentication();
			User user;
			if (authentication != null && authentication.getPrincipal() instanceof UserDetails details) {
				user = repository.findByUsername(details.getUsername()).orElseThrow(() ->
						new IllegalStateException("Unable to find user in repository: " + details.getUsername()));
			} else {
				throw new IllegalStateException("Authentication failed or principal has unexpected type");
			}
			return user;
		}

		@Override
		public Preferences getUserPreferences() {
			return getUser().getPreferences();
		}

		@Override
		@Transactional
		public void savePageSizePreference(int size) {
			User user = getUser();
			if (!Objects.equals(user.getPreferences().pageSize(), size)) {
				user.setPreferences(new Preferences(size));
				repository.save(user);
			}
		}
	}
}
