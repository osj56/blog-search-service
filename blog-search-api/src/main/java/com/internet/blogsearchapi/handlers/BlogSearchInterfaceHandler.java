package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchcommon.enums.CompanyCode;

public interface BlogSearchInterfaceHandler {
    BlogSearchResponse requestBlogSearchApi(CompanyCode companyCode, BlogSearchRequest blogSearchRequest);
}
