package com.internet.blogsearchapi.dtos.naver;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NaverBlogSearchResponse {
    String lastBuildDate;
    int total;
    int start;
    int display;
    @JsonProperty("items")
    List<NaverBlogSearchItemResponse> naverBlogSearchItemResponseList;
}
