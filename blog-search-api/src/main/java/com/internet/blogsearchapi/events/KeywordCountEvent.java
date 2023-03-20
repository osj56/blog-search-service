package com.internet.blogsearchapi.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class KeywordCountEvent extends ApplicationEvent {
    String keyword;

    public KeywordCountEvent(Object source, String keyword) {
        super(source);
        this.keyword = keyword;
    }
}
