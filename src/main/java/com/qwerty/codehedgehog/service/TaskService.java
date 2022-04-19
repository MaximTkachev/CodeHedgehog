package com.qwerty.codehedgehog.service;

import com.qwerty.codehedgehog.converter.TaskConverter;
import com.qwerty.codehedgehog.dto.Task.CreateTaskDto;
import com.qwerty.codehedgehog.dto.Task.TaskDto;
import com.qwerty.codehedgehog.dto.Task.UpdateTaskDto;
import com.qwerty.codehedgehog.entity.SampleSolutionEntity;
import com.qwerty.codehedgehog.entity.TaskEntity;
import com.qwerty.codehedgehog.repository.TaskRepository;
import com.qwerty.codehedgehog.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TopicRepository topicRepository;

    public List<TaskDto> getListOfTasks() {
        var taskEntities = taskRepository.findAll();
        List<TaskDto> result = new ArrayList<>();

        taskEntities.forEach(task -> {
            result.add(TaskConverter.convertEntityToDto(task));
        });

        return result;
    }

    public TaskDto createTask(CreateTaskDto createTaskDto) {
        var topic = topicRepository.findById(createTaskDto.getTopicId())
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        var task = TaskConverter.convertCreateDtoToEntity(createTaskDto, topic);
        var savedEntity = taskRepository.save(task);
        return TaskConverter.convertEntityToDto(savedEntity);
    }

    public TaskDto getTaskById(String id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return TaskConverter.convertEntityToDto(task);
    }

    public TaskDto editTask(String id, UpdateTaskDto updateTaskDto) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        updateTaskDto.getFields().forEach((key, value) -> {
            var field = ReflectionUtils.findField(TaskEntity.class, (String) key);
            if (field == null)
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            field.setAccessible(true);
            ReflectionUtils.setField(field, task, value);
        });

        return TaskConverter.convertEntityToDto(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
