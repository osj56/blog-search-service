package com.internet.blogsearchapi.services;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.handlers.BlogSearchInterfaceManager;
import com.internet.blogsearchcommon.entities.KeywordCount;
import com.internet.blogsearchcommon.enums.CompanyName;
import com.internet.blogsearchcommon.repositories.KeywordCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogSearchService {
    final RestTemplate restTemplate;
    final BlogSearchInterfaceManager blogSearchInterfaceManager;
    final KeywordCountRepository keywordCountRepository;

    public BlogSearchResponse requestBlogSearchApi(CompanyName companyName, BlogSearchRequest blogSearchRequest){
        return blogSearchInterfaceManager.requestBlogSearchApi(companyName, blogSearchRequest);
    }

    @Transactional(readOnly = true)
    public List<KeywordCount> getBlogSearchPopularKeywordTop10List(){
        return keywordCountRepository.findTop10ByOrderByCountDesc();
    }
}
