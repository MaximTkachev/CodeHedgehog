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
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private TopicEntity topic;

    @Column
    private String description;

    @Column
    private Integer price;

    @Column
    private Boolean isDraft;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private List<SampleSolutionEntity> examples;
}
