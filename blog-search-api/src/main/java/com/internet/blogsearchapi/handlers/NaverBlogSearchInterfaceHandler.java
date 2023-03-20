package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.dtos.naver.NaverBlogSearchResponse;
import com.internet.blogsearchapi.events.KeywordCountEventPublisher;
import com.internet.blogsearchcommon.annotations.InterfaceHandler;
import com.internet.blogsearchcommon.enums.CompanyName;
import com.internet.blogsearchcommon.utils.UrlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@InterfaceHandler(CompanyName.NAVER)
@RequiredArgsConstructor
@Component
public class NaverBlogSearchInterfaceHandler implements BlogSearchInterfaceHandler {

    final RestTemplate restTemplate;
    final KeywordCountEventPublisher keywordCountEventPublisher;

    @Value("${application.properties.naver.blog-search-url}")
    String blogSearchRequestApiUrl;
    @Value("${application.properties.naver.client-id}")
    String naverClientId;
    @Value("${application.properties.naver.client-secret-key}")
    String naverClientSecretKey;

    @Override
    public BlogSearchResponse requestBlogSearchApi(CompanyName companyName, BlogSearchRequest blogSearchRequest) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-Naver-Client-Id", naverClientId);
            httpHeaders.set("X-Naver-Client-Secret", naverClientSecretKey);

            String uri = buildUri(blogSearchRequestApiUrl, blogSearchRequest);
            HttpEntity<String> httpEntity = new HttpEntity<>("",httpHeaders);
            ResponseEntity<NaverBlogSearchResponse> naverBlogSearchResponseResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, NaverBlogSearchResponse.class);

            keywordCountEventPublisher.publishKeywordCountEvent(blogSearchRequest.getQuery());

            return buildBlogSearchResponse(naverBlogSearchResponseResponseEntity.getBody(), blogSearchRequest);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private String buildUri(String url, BlogSearchRequest blogSearchRequest) throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder();
        queryString.append("query=").append(UrlUtil.URLEncoder(blogSearchRequest.getQuery()));

        if(blogSearchRequest.getSort() != null){
            queryString.append("&sort=").append(blogSearchRequest.getSort());
        }

        if(blogSearchRequest.getStart() > 0){
            queryString.append("&start=").append(blogSearchRequest.getStart());
        }

        if(blogSearchRequest.getDisplay() > 10){
            queryString.append("&display=").append(blogSearchRequest.getDisplay());
        }
        return url + "?" + queryString.toString();
    }

    private BlogSearchResponse buildBlogSearchResponse(NaverBlogSearchResponse naverBlogSearchResponse, BlogSearchRequest blogSearchRequest){
        return BlogSearchResponse.builder()
                .companyName(CompanyName.NAVER)
                .totalCount(naverBlogSearchResponse.getTotal())
                .currentPage(naverBlogSearchResponse.getStart())
                .size(blogSearchRequest.getDisplay() > 10 ? blogSearchRequest.getDisplay() : 10)
                .start(blogSearchRequest.getStart() > 0 ? blogSearchRequest.getStart() : 1)
                .contents(naverBlogSearchResponse.getNaverBlogSearchItemResponseList())
                .build();
    }
}
