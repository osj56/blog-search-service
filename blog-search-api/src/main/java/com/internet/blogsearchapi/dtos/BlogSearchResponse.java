package com.internet.blogsearchapi.dtos;

import com.internet.blogsearchcommon.enums.CompanyName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlogSearchResponse<T> {
    CompanyName companyName;
    int totalCount;
    int currentPage;
    int size;
    int start;
    T contents;
}
