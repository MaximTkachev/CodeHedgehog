package com.qwerty.codehedgehog.repository;

import com.qwerty.codehedgehog.entity.TopicEntity;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<TopicEntity, String> {
}
