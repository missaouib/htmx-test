package io.nuevedejun.htmxtest.repository;

import io.nuevedejun.htmxtest.entity.UserInfo;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface UserInfoRepository extends ListCrudRepository<UserInfo, UUID> {
}
