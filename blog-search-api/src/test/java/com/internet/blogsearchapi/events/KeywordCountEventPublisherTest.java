package com.internet.blogsearchapi.events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KeywordCountEventPublisherTest {
    KeywordCountEventPublisher keywordCountEventPublisher;
    ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void init(){
        this.applicationEventPublisher = mock(ApplicationEventPublisher.class);
        this.keywordCountEventPublisher = new KeywordCountEventPublisher(applicationEventPublisher);
    }

    @DisplayName("KeywordCountEvent가 정상적으로 발행되는지 확인한다.")
    @Test
    void given_data_when_invoked_then_publishKeywordCountEventSuccessfully(){
        this.keywordCountEventPublisher.publishKeywordCountEvent("테스트");
        verify(this.applicationEventPublisher).publishEvent(any());
    }
}
