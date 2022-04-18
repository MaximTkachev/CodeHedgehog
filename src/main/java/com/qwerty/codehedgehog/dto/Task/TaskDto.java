package com.qwerty.codehedgehog.dto.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String id;
    private String name;
    private String topicId;
    private String description;
    private int price;
}
