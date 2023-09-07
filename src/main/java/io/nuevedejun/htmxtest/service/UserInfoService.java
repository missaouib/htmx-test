package io.nuevedejun.htmxtest.service;

import io.nuevedejun.htmxtest.entity.UserInfo;
import io.nuevedejun.htmxtest.entity.UserInfo.Preferences;
import io.nuevedejun.htmxtest.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public interface UserInfoService {
	Optional<UserInfo> getUserInfo();

	void savePageSizePreference(int size);

	@Service
	@RequiredArgsConstructor
	class Default implements UserInfoService {
		private final UserInfoRepository repository;

		@Override
		public Optional<UserInfo> getUserInfo() {
			return repository.findById(UUID.fromString("ff2fac3e-af45-4105-9189-94e9e57d09f6"));
		}

		@Override
		@Transactional
		public void savePageSizePreference(int size) {
			UserInfo info = getUserInfo().orElseThrow(() -> new NoSuchElementException("Unable to find user info"));
			if (!Objects.equals(info.getPreferences().pageSize(), size)) {
				info.setPreferences(new Preferences(size));
				repository.save(info);
			}
		}
	}
}
