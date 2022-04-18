package com.qwerty.codehedgehog.dto.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskDto {
    @NotBlank
    private String name;

    private String topicId;

    private String description;

    @Min(0)
    private int price;
}
