package com.internet.blogsearchapi.dtos;

import com.internet.blogsearchcommon.enums.CompanyCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BlogSearchResponse<T> {
    CompanyCode companyCode;
    int totalCount;
    int currentPage;
    int size;
    int start;
    T contents;
}
