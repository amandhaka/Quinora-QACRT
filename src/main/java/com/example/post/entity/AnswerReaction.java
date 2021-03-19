package com.example.post.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "reaction")
@Data
public class AnswerReaction {

    @Id
    @GenericGenerator(name = "reaction_id_seq", strategy = "increment")
    @GeneratedValue(generator = "reaction_id_seq", strategy = GenerationType.AUTO)
    private Long reactionId;

    @Column(nullable = false)
    private Long answerId;

    @Column(nullable = false)
    private String username;

    private boolean isLiked;

}
