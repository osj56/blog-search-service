package com.internet.blogsearchapi.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlogSearchRequest {
    String query;
    String sort;
    int page;
    int size;
}
