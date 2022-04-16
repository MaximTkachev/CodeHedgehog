package com.qwerty.codehedgehog.repository;

import com.qwerty.codehedgehog.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
