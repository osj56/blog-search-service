package com.internet.blogsearchapi.events;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordCountEventPublisher {
    final ApplicationEventPublisher applicationEventPublisher;

    public void publishKeywordCountEvent(String keyword){
        KeywordCountEvent keywordCountEvent = new KeywordCountEvent(this, keyword);
        applicationEventPublisher.publishEvent(keywordCountEvent);
    }
}
