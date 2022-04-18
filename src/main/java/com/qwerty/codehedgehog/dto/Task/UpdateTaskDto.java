package com.qwerty.codehedgehog.dto.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDto {
    @NotEmpty
    private Map<Object, Object> fields;
}
