package com.qwerty.codehedgehog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class TopicEntity {
    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_topic", referencedColumnName = "id")
    private TopicEntity parentTopic;

    @OneToMany(mappedBy = "parentTopic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TopicEntity> childTopics;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskEntity> tasks;
}
