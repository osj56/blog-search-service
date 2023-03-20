package com.internet.blogsearchcommon.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class KeywordCount extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "keyword_count_id")
    long id;
    String keyword;
    int count;

    public void increaseCount(){
        this.count++;
    }
}
