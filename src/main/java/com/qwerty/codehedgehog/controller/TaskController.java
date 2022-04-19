package com.qwerty.codehedgehog.controller;

import com.qwerty.codehedgehog.dto.Task.CreateTaskDto;
import com.qwerty.codehedgehog.dto.Task.TaskDto;
import com.qwerty.codehedgehog.dto.Task.UpdateTaskDto;
import com.qwerty.codehedgehog.service.SampleService;
import com.qwerty.codehedgehog.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks")
public class TaskController {
    private final TaskService taskService;
    private final SampleService sampleService;

    @GetMapping
    public List<TaskDto> get_list_of_all_tasks(){
        return taskService.getListOfTasks();
    }

    @PostMapping
    public TaskDto create_new_task(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.createTask(createTaskDto);
    }

    @GetMapping("/{task_id}")
    public TaskDto get_task_by_id(@PathVariable UUID task_id) {
        return taskService.getTaskById(task_id.toString());
    }

    @PatchMapping("/{task_id}")
    public TaskDto edit_task(@PathVariable UUID task_id,
                             @Validated @RequestBody UpdateTaskDto updateTaskDto) {
        return taskService.editTask(task_id.toString(), updateTaskDto);
    }

    @DeleteMapping("/{task_id}")
    public void delete_task(@PathVariable UUID task_id) {
        taskService.deleteTask(task_id.toString());
    }

    @PostMapping("/{task_id}/example")
    public void upload_example(@PathVariable UUID task_id, @RequestParam("file") MultipartFile file) {
        sampleService.uploadExample(task_id.toString(), file);
    }

    @GetMapping("{task_id}/example")
    public List<String> get_example(@PathVariable UUID task_id) {
        return sampleService.getExamples(task_id.toString());
    }
}
