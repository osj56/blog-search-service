package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchcommon.enums.CompanyCode;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.internet.blogsearchapi.util.DataUtil.createBlogSearchRequest;
import static com.internet.blogsearchapi.util.DataUtil.createBlogSearchResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BlogSearchInterfaceManager.class})
public class BlogSearchInterfaceManagerTest {
    @Autowired
    BlogSearchInterfaceManager blogSearchInterfaceManager;

    @MockBean
    ApplicationContext applicationContext;

    @MockBean
    NaverBlogSearchInterfaceHandler naverBlogSearchInterfaceHandler;

    @MockBean
    KakaoBlogSearchInterfaceHandler kakaoBlogSearchInterfaceHandler;

    @DisplayName("KakaoBlogSearchInterfaceHandler의 requestBlogSearchApi가 호출되는지 확인한다.")
    @Test
    void given_KakaoRequestBlogSearchApi_when_calledWithParaters_then_invokedProperHandler(){
        BlogSearchRequest blogSearchRequest = createBlogSearchRequest();
        BlogSearchResponse blogSearchResponse = createBlogSearchResponse(CompanyCode.KAKAO);

        when(kakaoBlogSearchInterfaceHandler.requestBlogSearchApi(CompanyCode.KAKAO, blogSearchRequest)).thenReturn(blogSearchResponse);

        assertThat(blogSearchInterfaceManager.requestBlogSearchApi(CompanyCode.KAKAO, blogSearchRequest).getCompanyCode()).isEqualTo(blogSearchResponse.getCompanyCode());
    }

    @DisplayName("NaverBlogSearchInterfaceHandler의 requestBlogSearchApi가 호출되는지 확인한다.")
    @Test
    void given_NaverRequestBlogSearchApi_when_calledWithParaters_then_invokedProperHandler(){
        BlogSearchRequest blogSearchRequest = createBlogSearchRequest();
        BlogSearchResponse blogSearchResponse = createBlogSearchResponse(CompanyCode.NAVER);

        when(naverBlogSearchInterfaceHandler.requestBlogSearchApi(CompanyCode.NAVER, blogSearchRequest)).thenReturn(blogSearchResponse);

        assertThat(blogSearchInterfaceManager.requestBlogSearchApi(CompanyCode.NAVER, blogSearchRequest).getCompanyCode()).isEqualTo(blogSearchResponse.getCompanyCode());
    }
}
