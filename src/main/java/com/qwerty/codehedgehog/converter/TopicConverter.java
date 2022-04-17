package com.qwerty.codehedgehog.converter;

import com.qwerty.codehedgehog.dto.Topic.CreateTopicDto;
import com.qwerty.codehedgehog.dto.Topic.TopicDto;
import com.qwerty.codehedgehog.dto.Topic.TopicWithChildrenDto;
import com.qwerty.codehedgehog.entity.TopicEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TopicConverter {
    public static TopicDto convertEntityToDto(TopicEntity topicEntity) {
        String parentTopicId;
        if (topicEntity.getParentTopic() != null)
            parentTopicId = topicEntity.getParentTopic().getId();
        else
            parentTopicId = null;

        return new TopicDto(
                topicEntity.getId(),
                topicEntity.getName(),
                parentTopicId
        );
    }

    public static TopicWithChildrenDto convertEntityToDtoWithChildren(TopicEntity topicEntity) {
        var children = topicEntity.getChildTopics();
        List<TopicDto> childTopics = new ArrayList<>();
        for(TopicEntity child : children) {
            var tempTopic = convertEntityToDto(child);
            childTopics.add(tempTopic);
        }

        String parentTopic;
        if (topicEntity.getParentTopic() != null)
            parentTopic = topicEntity.getParentTopic().getId();
        else
            parentTopic = null;

        return new TopicWithChildrenDto(
                topicEntity.getId(),
                topicEntity.getName(),
                parentTopic,
                childTopics
        );
    }

    public static TopicEntity convertCreateDtoToEntity(CreateTopicDto createTopicDto, TopicEntity parentTopic) {
        return new TopicEntity(
                UUID.randomUUID().toString(),
                createTopicDto.getName(),
                parentTopic,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
