package com.qwerty.codehedgehog.repository;

import com.qwerty.codehedgehog.entity.SampleSolutionEntity;
import com.qwerty.codehedgehog.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SampleRepository extends CrudRepository<SampleSolutionEntity, String> {

    List<SampleSolutionEntity> getAllByTask(TaskEntity task);
}
