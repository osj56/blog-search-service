package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.dtos.kakao.KakaoBlogSearchResponse;
import com.internet.blogsearchapi.events.KeywordCountEventPublisher;
import com.internet.blogsearchcommon.enums.CompanyCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

public class KakaoBlogSearchInterfaceHandlerTest {
    @DisplayName("requestBlogSearchApi 메소드가 호출 되었을 때, 올바른 값을 반환하는지 확인한다.")
    @Test
    void given_blogSearchRequest_when_requestBlogSearchApi_then_returnCorrectValue(){
        RestTemplate restTemplate = mock(RestTemplate.class);
        KeywordCountEventPublisher keywordCountEventPublisher = mock(KeywordCountEventPublisher.class);

        KakaoBlogSearchInterfaceHandler kakaoBlogSearchInterfaceHandler = new KakaoBlogSearchInterfaceHandler(restTemplate, keywordCountEventPublisher);
        BlogSearchRequest blogSearchRequest = createBlogSearchRequest();
        KakaoBlogSearchResponse kakaoBlogSearchResponse = createKakaoBlogSearchResponse();

        ResponseEntity<KakaoBlogSearchResponse> responseEntity = new ResponseEntity<>(kakaoBlogSearchResponse, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), any(), new HttpEntity<>(any(), any()), KakaoBlogSearchResponse.class))
                .thenReturn(responseEntity);

        assertThat(kakaoBlogSearchInterfaceHandler.requestBlogSearchApi(CompanyCode.KAKAO, blogSearchRequest).getCompanyCode()).isEqualTo(CompanyCode.KAKAO);
    }

    @DisplayName("requestBlogSearchApi 메소드가 호출 되고, 비정상적인 응답을 받았을 때, 올바른 RuntimeException을 반환하는지 확인한다.")
    @Test
    void given_blogSearchRequest_when_requestBlogSearchApiAndInCorrectResultFromKakao_then_returnCorrectException(){
        RestTemplate restTemplate = mock(RestTemplate.class);
        KeywordCountEventPublisher keywordCountEventPublisher = mock(KeywordCountEventPublisher.class);

        KakaoBlogSearchInterfaceHandler kakaoBlogSearchInterfaceHandler = new KakaoBlogSearchInterfaceHandler(restTemplate, keywordCountEventPublisher);
        BlogSearchRequest blogSearchRequest = createBlogSearchRequest();

        when(restTemplate.exchange(anyString(), any(), new HttpEntity<>(any(), any()), KakaoBlogSearchResponse.class))
                .thenReturn(null);

        assertThatThrownBy(() -> kakaoBlogSearchInterfaceHandler.requestBlogSearchApi(CompanyCode.KAKAO, blogSearchRequest)).isInstanceOf(RuntimeException.class);
    }
}
