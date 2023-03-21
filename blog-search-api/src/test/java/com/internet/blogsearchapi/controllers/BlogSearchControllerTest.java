package com.internet.blogsearchapi.controllers;

import com.internet.blogsearchapi.services.BlogSearchServiceTest;
import com.internet.blogsearchcommon.enums.ResultCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogSearchController.class)
public class BlogSearchControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BlogSearchServiceTest blogSearchService;

    @DisplayName("blog search api가 정상적으로 호출되는지 확인한다.")
    @Test
    void given_getBlogSearchResultUri_when_called_then_returnOk() throws Exception {
        mockMvc.perform(get("/blog-search/kakao")
                .queryParam("query", "%EC%98%81%ED%99%94"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value(ResultCode.SUCCEEDED.name()));
    }

    @DisplayName("blog search 키워드 검색 api가 정상적으로 호출되는지 확인한다.")
    @Test
    void given_getBlogSearchPopularKeyword_when_called_then_returnOk() throws Exception {
        mockMvc.perform(get("/blog-search/popular-keyword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultCode").value(ResultCode.SUCCEEDED.name()));
    }
}
