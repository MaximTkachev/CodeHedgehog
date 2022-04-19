package com.qwerty.codehedgehog.repository;

import com.qwerty.codehedgehog.entity.SampleSolutionEntity;
import org.springframework.data.repository.CrudRepository;

public interface SampleRepository extends CrudRepository<SampleSolutionEntity, String> {
}
