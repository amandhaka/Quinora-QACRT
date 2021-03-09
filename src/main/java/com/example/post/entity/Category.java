package com.example.post.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity(name = "category")
@Data
public class Category {
    @Id
    @GenericGenerator(name = "category_id_seq", strategy = "increment")
    @GeneratedValue(generator = "category_id_seq", strategy = GenerationType.AUTO)
    private Long categoryId;

    @NotBlank
    private String categoryName;

}
