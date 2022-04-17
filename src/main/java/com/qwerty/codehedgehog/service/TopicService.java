package com.qwerty.codehedgehog.service;

import com.qwerty.codehedgehog.converter.TopicConverter;
import com.qwerty.codehedgehog.dto.Topic.*;
import com.qwerty.codehedgehog.entity.TopicEntity;
import com.qwerty.codehedgehog.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    @Transactional(readOnly = true)
    public List<TopicDto> get_all_topics() {
        var topicEntities = topicRepository.findAll();
        List<TopicDto> result = new ArrayList<>();
        for (TopicEntity topic : topicEntities) {
            var tempTopic = TopicConverter.convertEntityToDto(topic);
            result.add(tempTopic);
        }

        return result;
    }

    @Transactional
    public TopicDto createTopic(CreateTopicDto createTopicDto) {
        TopicEntity parentTopic;
        if (createTopicDto.getParentId() != null) {
            parentTopic = topicRepository.findById(createTopicDto.getParentId())
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        }
        else
            parentTopic = null;

        var topic = TopicConverter.convertCreateDtoToEntity(createTopicDto, parentTopic);
        var savedEntity = topicRepository.save(topic);
        return TopicConverter.convertEntityToDto(savedEntity);
    }

    @Transactional(readOnly = true)
    public TopicWithChildrenDto get_topic_by_id(String id) {
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        return TopicConverter.convertEntityToDtoWithChildren(topic);
    }

    @Transactional
    public TopicWithChildrenDto update_topic(String id, UpdateTopicDto updateTopicDto) {
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        updateTopicDto.getFields().forEach((key, value) -> {
            var field = ReflectionUtils.findField(TopicEntity.class, (String) key);
            if (field == null)
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            field.setAccessible(true);
            ReflectionUtils.setField(field, topic, value);
        });

        return TopicConverter.convertEntityToDtoWithChildren(topic);
    }

    @Transactional
    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TopicDto> get_children_of_topic(String parentTopicId) {
        var parentTopic = topicRepository.findById(parentTopicId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        var childTopics = parentTopic.getChildTopics();
        List<TopicDto> result = new ArrayList<>();
        for(TopicEntity topic : childTopics) {
            var tempTopic = TopicConverter.convertEntityToDto(topic);
            result.add(tempTopic);
        }

        return result;
    }

    @Transactional
    public TopicWithChildrenDto add_child_topics(String parentTopicId, AddChildTopicsDto addChildTopicsDto) {
        Iterable<TopicEntity> childTopics = topicRepository.findAllById(addChildTopicsDto.getChildTopics());
        var parentTopic = topicRepository.findById(parentTopicId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        parentTopic.getChildTopics().addAll((Collection<TopicEntity>) childTopics);
        childTopics.forEach(topicEntity -> {
            topicEntity.setParentTopic(parentTopic);
        });
        return TopicConverter.convertEntityToDtoWithChildren(parentTopic);
    }

    @Transactional
    public TopicWithChildrenDto remove_child_topics(String parentTopicId, DeleteChildTopicsDto deleteChildTopicsDto) {
        Iterable<TopicEntity> childTopics = topicRepository.findAllById(deleteChildTopicsDto.getChildTopics());
        var parentTopic = topicRepository.findById(parentTopicId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        parentTopic.getChildTopics().removeAll((Collection<TopicEntity>) childTopics);
        childTopics.forEach(topicEntity -> {
            topicEntity.setParentTopic(null);
        });

        return TopicConverter.convertEntityToDtoWithChildren(parentTopic);
    }
}
