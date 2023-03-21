package com.internet.blogsearchapi.util;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.dtos.kakao.KakaoBlogSearchDocumentsResponse;
import com.internet.blogsearchapi.dtos.kakao.KakaoBlogSearchMetaResponse;
import com.internet.blogsearchapi.dtos.kakao.KakaoBlogSearchResponse;
import com.internet.blogsearchapi.dtos.naver.NaverBlogSearchItemResponse;
import com.internet.blogsearchapi.dtos.naver.NaverBlogSearchResponse;
import com.internet.blogsearchapi.events.KeywordCountEvent;
import com.internet.blogsearchcommon.enums.CompanyCode;

import java.util.List;

public class DataUtil {
    public static KeywordCountEvent createKeywordCountEvent(Object source, String keyword){
        return new KeywordCountEvent(source, keyword);
    }

    public static BlogSearchRequest createBlogSearchRequest(){
        return BlogSearchRequest.builder()
                .query("테스트")
                .sort("recency")
                .page(1)
                .size(10)
                .build();
    }

    public static BlogSearchResponse createBlogSearchResponse(CompanyCode companyCode){
        return BlogSearchResponse.builder()
                .companyCode(companyCode)
                .totalCount(0)
                .currentPage(0)
                .size(0)
                .start(0)
                .contents("")
                .build();
    }

    public static KakaoBlogSearchResponse createKakaoBlogSearchResponse(){
        KakaoBlogSearchResponse kakaoBlogSearchResponse = new KakaoBlogSearchResponse();
        kakaoBlogSearchResponse.setKakaoBlogSearchMetaResponse(new KakaoBlogSearchMetaResponse());
        kakaoBlogSearchResponse.setKakaoBlogSearchDocumentsResponseList(List.of(new KakaoBlogSearchDocumentsResponse()));
        return kakaoBlogSearchResponse;
    }

    public static NaverBlogSearchResponse createNaverBlogSearchResponse(){
        NaverBlogSearchResponse naverBlogSearchResponse = new NaverBlogSearchResponse();
        naverBlogSearchResponse.setLastBuildDate("");
        naverBlogSearchResponse.setTotal(0);
        naverBlogSearchResponse.setStart(0);
        naverBlogSearchResponse.setDisplay(0);
        naverBlogSearchResponse.setNaverBlogSearchItemResponseList(List.of(new NaverBlogSearchItemResponse()));
        return naverBlogSearchResponse;
    }
}
