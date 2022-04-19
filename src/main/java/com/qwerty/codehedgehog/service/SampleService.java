package com.qwerty.codehedgehog.service;

import com.qwerty.codehedgehog.entity.SampleSolutionEntity;
import com.qwerty.codehedgehog.repository.SampleRepository;
import com.qwerty.codehedgehog.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;
    private final TaskRepository taskRepository;

    public void uploadExample(String taskId, MultipartFile file) {
        if (!Objects.equals(file.getContentType(), "text/plain"))
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);

        String fileName = file.getOriginalFilename();
        var uploadPath = "C:\\uploads";
        var uploadDir = new File(uploadPath);
        if(!uploadDir.exists())
            uploadDir.mkdir();
        var resultFileName = UUID.randomUUID() + "-" +fileName;

        try {
            file.transferTo(new File(uploadPath + "\\" + resultFileName));
        } catch (Exception e){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        SampleSolutionEntity sample = new SampleSolutionEntity(
                resultFileName,
                task
        );

        sampleRepository.save(sample);
    }

    public List<String> getExamples(String taskId) {
        var task = taskRepository.findById(taskId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        var samples = sampleRepository.getAllByTask(task);

        var uploadPath = "C:\\uploads";

        List<String> result = new ArrayList<>();
        for (var sample : samples) {
            var filePath = uploadPath + "\\" + sample.getId();
            try {
                String content = Files.lines(Paths.get(filePath)).reduce("", String::concat);
                result.add(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
