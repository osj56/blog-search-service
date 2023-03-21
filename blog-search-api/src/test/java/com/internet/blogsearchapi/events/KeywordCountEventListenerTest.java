package com.internet.blogsearchapi.events;

import com.internet.blogsearchcommon.repositories.KeywordCountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.internet.blogsearchapi.util.DataUtil.createKeywordCountEvent;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {KeywordCountEventListener.class})
public class KeywordCountEventListenerTest {
    @Autowired
    KeywordCountEventListener keywordCountEventListener;

    @MockBean
    KeywordCountRepository keywordCountRepository;

    @DisplayName("keywordCountEvent를 받았을 때, keywordCountRepository가 정상적으로 호출되는지 확인한다.")
    @Test
    void given_event_when_invoked_then_handleKeywordCountEventSuccessfully(){
        KeywordCountEvent keywordCountEvent = createKeywordCountEvent(this, "테스트");
        keywordCountEventListener.handleKeywordCountEvent(keywordCountEvent);
        verify(keywordCountRepository).findByKeyword("테스트");
    }
}
