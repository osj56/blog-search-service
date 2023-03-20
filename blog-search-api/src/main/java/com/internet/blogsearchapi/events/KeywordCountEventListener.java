package com.internet.blogsearchapi.events;

import com.internet.blogsearchcommon.entities.KeywordCount;
import com.internet.blogsearchcommon.repositories.KeywordCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class KeywordCountEventListener {
    final KeywordCountRepository keywordCountRepository;

    @EventListener
    @Async("asyncTaskExecutor")
    @Transactional
    public void handleKeywordCountEvent(KeywordCountEvent keywordCountEvent){
        KeywordCount keywordCount = keywordCountRepository.findByKeyword(keywordCountEvent.getKeyword());
        if(keywordCount == null){
            KeywordCount newKeywordCount = new KeywordCount();
            newKeywordCount.setKeyword(keywordCountEvent.getKeyword());
            newKeywordCount.setCount(1);
            keywordCountRepository.save(newKeywordCount);
        }else {
            keywordCount.increaseCount();
        }
    }
}
