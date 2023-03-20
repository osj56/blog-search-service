package com.internet.blogsearchapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogSearchRequest {
    String query;
    String sort;
    int display;
    int start;
    int page;
    int size;
}
