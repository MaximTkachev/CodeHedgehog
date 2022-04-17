package com.qwerty.codehedgehog.dto.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicWithChildrenDto {
    private String id;
    private String name;
    private String parentId;
    private List<TopicDto> children;
}
