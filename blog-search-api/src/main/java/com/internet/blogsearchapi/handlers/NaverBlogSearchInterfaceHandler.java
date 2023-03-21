package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.dtos.naver.NaverBlogSearchResponse;
import com.internet.blogsearchapi.events.KeywordCountEventPublisher;
import com.internet.blogsearchcommon.annotations.InterfaceHandler;
import com.internet.blogsearchcommon.enums.CompanyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@InterfaceHandler(CompanyCode.NAVER)
@RequiredArgsConstructor
@Component
public class NaverBlogSearchInterfaceHandler implements BlogSearchInterfaceHandler {

    final RestTemplate restTemplate;
    final KeywordCountEventPublisher keywordCountEventPublisher;

    @Value("${application.properties.naver.blog-search-url}")
    String naverBlogSearchUrl;
    @Value("${application.properties.naver.client-id}")
    String naverClientId;
    @Value("${application.properties.naver.client-secret-key}")
    String naverClientSecretKey;

    @Override
    public BlogSearchResponse requestBlogSearchApi(CompanyCode companyCode, BlogSearchRequest blogSearchRequest) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-Naver-Client-Id", naverClientId);
            httpHeaders.set("X-Naver-Client-Secret", naverClientSecretKey);

            String uri = buildUri(naverBlogSearchUrl, blogSearchRequest);
            HttpEntity<String> httpEntity = new HttpEntity<>("",httpHeaders);
            ResponseEntity<NaverBlogSearchResponse> naverBlogSearchResponseResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, NaverBlogSearchResponse.class);

            keywordCountEventPublisher.publishKeywordCountEvent(blogSearchRequest.getQuery());

            return buildBlogSearchResponse(naverBlogSearchResponseResponseEntity.getBody(), blogSearchRequest);
        }catch (Exception e){
            throw new RuntimeException(String.format("Failed requestNaverBlogSearchApi: %s", e.getMessage(), e));
        }
    }

    private String buildUri(String url, BlogSearchRequest blogSearchRequest) {
        StringBuilder queryString = new StringBuilder();

        queryString.append("query=").append(blogSearchRequest.getQuery());

        if(blogSearchRequest.getSort() != null){
            queryString.append("&sort=").append(convertNaverSortValue(blogSearchRequest.getSort()));
        }

        if(blogSearchRequest.getPage() > 0){
            queryString.append("&start=").append(blogSearchRequest.getPage());
        }

        if(blogSearchRequest.getSize() > 10){
            queryString.append("&display=").append(blogSearchRequest.getSize());
        }
        return url + "?" + queryString.toString();
    }

    private BlogSearchResponse buildBlogSearchResponse(NaverBlogSearchResponse naverBlogSearchResponse, BlogSearchRequest blogSearchRequest){
        return BlogSearchResponse.builder()
                .companyCode(CompanyCode.NAVER)
                .totalCount(naverBlogSearchResponse.getTotal())
                .currentPage(naverBlogSearchResponse.getStart())
                .size(blogSearchRequest.getSize() > 10 ? blogSearchRequest.getSize() : 10)
                .start(blogSearchRequest.getPage() > 0 ? blogSearchRequest.getPage() : 1)
                .contents(naverBlogSearchResponse.getNaverBlogSearchItemResponseList())
                .build();
    }

    private String convertNaverSortValue(String inputSort){
        if(inputSort == null){
            return null;
        }

        return switch (inputSort){
            case "accuracy" -> "sim";
            case "recency" -> "date";
            default -> throw new IllegalStateException("Unexpected value: " + inputSort);
        };
    }
}
