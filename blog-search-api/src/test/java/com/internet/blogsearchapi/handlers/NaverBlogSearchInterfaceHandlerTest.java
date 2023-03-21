package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.naver.NaverBlogSearchResponse;
import com.internet.blogsearchapi.events.KeywordCountEventPublisher;
import com.internet.blogsearchcommon.enums.CompanyCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.internet.blogsearchapi.util.DataUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NaverBlogSearchInterfaceHandlerTest {
    @DisplayName("requestBlogSearchApi 메소드가 호출 되었을 때, 올바른 값을 반환하는지 확인한다.")
    @Test
    void given_blogSearchRequest_when_requestBlogSearchApi_then_returnCorrectValue(){
        RestTemplate restTemplate = mock(RestTemplate.class);
        KeywordCountEventPublisher keywordCountEventPublisher = mock(KeywordCountEventPublisher.class);

        NaverBlogSearchInterfaceHandler naverBlogSearchInterfaceHandler = new NaverBlogSearchInterfaceHandler(restTemplate, keywordCountEventPublisher);
        BlogSearchRequest blogSearchRequest = createBlogSearchRequest();
        NaverBlogSearchResponse naverBlogSearchResponse = createNaverBlogSearchResponse();

        ResponseEntity<NaverBlogSearchResponse> responseEntity = new ResponseEntity<>(naverBlogSearchResponse, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(), new HttpEntity<>(any(), any()), NaverBlogSearchResponse.class))
                .thenReturn(responseEntity);

        assertThat(naverBlogSearchInterfaceHandler.requestBlogSearchApi(CompanyCode.NAVER, blogSearchRequest).getCompanyCode()).isEqualTo(CompanyCode.NAVER);
    }

    @DisplayName("requestBlogSearchApi 메소드가 호출 되고, 비정상적인 응답을 받았을 때, 올바른 RuntimeException을 반환하는지 확인한다.")
    @Test
    void given_blogSearchRequest_when_requestBlogSearchApiAndInCorrectResultFromNaver_then_returnCorrectException(){
        RestTemplate restTemplate = mock(RestTemplate.class);
        KeywordCountEventPublisher keywordCountEventPublisher = mock(KeywordCountEventPublisher.class);

        NaverBlogSearchInterfaceHandler naverBlogSearchInterfaceHandler = new NaverBlogSearchInterfaceHandler(restTemplate, keywordCountEventPublisher);
        BlogSearchRequest blogSearchRequest = createBlogSearchRequest();

        when(restTemplate.exchange(anyString(), any(), new HttpEntity<>(any(), any()), NaverBlogSearchResponse.class))
                .thenReturn(null);

        assertThatThrownBy(() -> naverBlogSearchInterfaceHandler.requestBlogSearchApi(CompanyCode.NAVER, blogSearchRequest)).isInstanceOf(RuntimeException.class);
    }
}
