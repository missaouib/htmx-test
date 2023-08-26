package io.nuevedejun.htmxtest.user;

import io.nuevedejun.htmxtest.entity.UserInfo;
import io.nuevedejun.htmxtest.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoService {
	private final UserInfoRepository repository;

	public Optional<UserInfo> getUserInfo() {
		return repository.findById(UUID.fromString("ff2fac3e-af45-4105-9189-94e9e57d09f6"));
	}

	@Transactional
	public void save(UserInfo userInfo) {
		repository.save(userInfo);
	}
}
