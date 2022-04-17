package com.qwerty.codehedgehog.controller;

import com.qwerty.codehedgehog.dto.Topic.*;
import com.qwerty.codehedgehog.service.TopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topics")
@Tag(name = "Topic")
public class TopicController {
    private final TopicService topicService;

    @GetMapping
    public List<TopicDto> get_list_of_all_topics() {
        return topicService.get_all_topics();
    }

    @PostMapping
    public TopicDto create_new_topic(@Validated @RequestBody CreateTopicDto createTopicDto) {
        return topicService.createTopic(createTopicDto);
    }

    @GetMapping("/{topic_id}")
    public TopicWithChildrenDto get_topic_by_id(@PathVariable UUID topic_id) {
        return topicService.get_topic_by_id(topic_id.toString());
    }

    @PatchMapping("/{topic_id}")
    public TopicWithChildrenDto update_existing_topic(@PathVariable UUID topic_id,
                                                      @Validated @RequestBody UpdateTopicDto updateTopicDto) {
        return topicService.update_topic(topic_id.toString(), updateTopicDto);
    }

    @DeleteMapping("/{topic_id}")
    public void delete_topic(@PathVariable UUID topic_id) {
        topicService.deleteTopic(topic_id.toString());
    }

    @GetMapping("/{topic_id}/childs")
    public List<TopicDto> get_list_of_children_of_topic(@PathVariable UUID topic_id) {
        return topicService.get_children_of_topic(topic_id.toString());
    }

    @PostMapping("/{topic_id}/childs")
    public TopicWithChildrenDto add_children_to_topic(@PathVariable UUID topic_id,
                                                      @Validated @RequestBody AddChildTopicsDto addChildTopicsDto){
        return topicService.add_child_topics(topic_id.toString(), addChildTopicsDto);
    }

    @DeleteMapping("/{topic_id}/childs")
    public TopicWithChildrenDto remove_children_from_topic(@PathVariable UUID topic_id,
                                                           @Validated @RequestBody DeleteChildTopicsDto deleteChildTopicsDto) {
        return topicService.remove_child_topics(topic_id.toString(), deleteChildTopicsDto);
    }
}
