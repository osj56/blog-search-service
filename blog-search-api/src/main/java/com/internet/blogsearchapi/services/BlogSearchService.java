package com.internet.blogsearchapi.services;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.handlers.BlogSearchInterfaceManager;
import com.internet.blogsearchcommon.entities.KeywordCount;
import com.internet.blogsearchcommon.enums.CompanyCode;
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

    public BlogSearchResponse requestBlogSearchApi(String companyName, String query, String sort, int page, int size){
        CompanyCode companyCode = CompanyCode.fromCompanyName(companyName);
        BlogSearchRequest blogSearchRequest = buildBlogSearchRequest(query, sort, page, size);

        return blogSearchInterfaceManager.requestBlogSearchApi(companyCode, blogSearchRequest);
    }

    private BlogSearchRequest buildBlogSearchRequest(String query, String sort, int page, int size) {
        return BlogSearchRequest.builder()
                .query(query)
                .sort(sort)
                .page(page)
                .size(size)
                .build();
    }

    @Transactional(readOnly = true)
    public List<KeywordCount> getBlogSearchPopularKeywordTop10List(){
        return keywordCountRepository.findTop10ByOrderByCountDesc();
    }
}
