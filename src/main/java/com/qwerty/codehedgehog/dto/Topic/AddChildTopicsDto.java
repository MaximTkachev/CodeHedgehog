package com.qwerty.codehedgehog.dto.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddChildTopicsDto {
    @NotEmpty
    private List<String> childTopics;
}
