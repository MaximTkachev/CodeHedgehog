package com.qwerty.codehedgehog.dto.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTopicDto {
    @NotEmpty
    private Map<Object, Object> fields;
}
