package com.internet.blogsearchapi.handlers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchcommon.enums.CompanyName;

public interface BlogSearchInterfaceHandler {
    BlogSearchResponse requestBlogSearchApi(CompanyName companyName, BlogSearchRequest blogSearchRequest);
}
