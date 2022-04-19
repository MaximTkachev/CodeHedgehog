package com.qwerty.codehedgehog.converter;

import com.qwerty.codehedgehog.dto.Task.CreateTaskDto;
import com.qwerty.codehedgehog.dto.Task.TaskDto;
import com.qwerty.codehedgehog.entity.TaskEntity;
import com.qwerty.codehedgehog.entity.TopicEntity;

import java.util.ArrayList;
import java.util.UUID;

public class TaskConverter {
    public static TaskDto convertEntityToDto(TaskEntity taskEntity) {
        String topicId;
        if (taskEntity.getTopic() != null) {
            topicId = taskEntity.getTopic().getId();
        }
        else
            topicId = null;

        return new TaskDto(
                taskEntity.getId(),
                taskEntity.getName(),
                topicId,
                taskEntity.getDescription(),
                taskEntity.getPrice()
        );
    }

    public static TaskEntity convertCreateDtoToEntity(CreateTaskDto createTaskDto, TopicEntity topic) {
        return new TaskEntity(
                UUID.randomUUID().toString(),
                createTaskDto.getName(),
                topic,
                createTaskDto.getDescription(),
                createTaskDto.getPrice(),
                false,
                new ArrayList<>()
        );
    }
}
