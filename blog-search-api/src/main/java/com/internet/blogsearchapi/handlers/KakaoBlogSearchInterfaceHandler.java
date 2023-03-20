package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.dtos.kakao.KakaoBlogSearchResponse;
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

@InterfaceHandler(CompanyName.KAKAO)
@RequiredArgsConstructor
@Component
public class KakaoBlogSearchInterfaceHandler implements BlogSearchInterfaceHandler{

    final RestTemplate restTemplate;
    final KeywordCountEventPublisher keywordCountEventPublisher;

    @Value("${application.properties.kakao.blog-search-url}")
    String kakaoBlogSearchRequestApiUrl;
    @Value("${application.properties.kakao.api-auth-key}")
    String kakaoApiAuthKey;

    @Override
    public BlogSearchResponse requestBlogSearchApi(CompanyName companyName, BlogSearchRequest blogSearchRequest) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "KakaoAK" + " " + kakaoApiAuthKey);

            String uri = buildUri(kakaoBlogSearchRequestApiUrl, blogSearchRequest);

            HttpEntity<String> httpEntity = new HttpEntity<>("",httpHeaders);
            ResponseEntity<KakaoBlogSearchResponse> kakaoBlogSearchResponseResponseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoBlogSearchResponse.class);

            keywordCountEventPublisher.publishKeywordCountEvent(blogSearchRequest.getQuery());

            return buildBlogSearchResponse(kakaoBlogSearchResponseResponseEntity.getBody(), blogSearchRequest);
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

        if(blogSearchRequest.getPage() > 0){
            queryString.append("&page=").append(blogSearchRequest.getPage());
        }

        if(blogSearchRequest.getSize() > 10){
            queryString.append("&size=").append(blogSearchRequest.getSize());
        }
        return url + "?" + queryString.toString();
    }

    private BlogSearchResponse buildBlogSearchResponse(KakaoBlogSearchResponse kakaoBlogSearchResponse, BlogSearchRequest blogSearchRequest){
        return BlogSearchResponse.builder()
                .companyName(CompanyName.KAKAO)
                .totalCount(kakaoBlogSearchResponse.getKakaoBlogSearchMetaResponse().getTotalCount())
                .currentPage(blogSearchRequest.getPage() > 0 ? blogSearchRequest.getPage() : 1)
                .size(blogSearchRequest.getSize() > 0 ? blogSearchRequest.getSize() : 10)
                .contents(kakaoBlogSearchResponse.getKakaoBlogSearchDocumentsResponseList())
                .build();
    }
}
