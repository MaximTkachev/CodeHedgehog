package com.qwerty.codehedgehog.dto.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTopicDto {
    @NotBlank
    private String name;

    private String parentId;
}
