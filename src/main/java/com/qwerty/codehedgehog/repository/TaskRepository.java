package com.qwerty.codehedgehog.repository;

import com.qwerty.codehedgehog.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, String> {
}
