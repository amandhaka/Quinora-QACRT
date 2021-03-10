package com.example.post.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "reaction")
@Data
public class AnswerReaction {

    @Id
    @GenericGenerator(name = "reaction_id_seq", strategy = "increment")
    @GeneratedValue(generator = "reaction_id_seq", strategy = GenerationType.AUTO)
    private Long reactionId;

    private Long answerId;

    private String username;

    private boolean isLiked;

}
