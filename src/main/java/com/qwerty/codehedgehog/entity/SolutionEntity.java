package com.qwerty.codehedgehog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "solutions")
public class SolutionEntity {
    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private String sourceCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Verdict verdict;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskEntity task;
}
