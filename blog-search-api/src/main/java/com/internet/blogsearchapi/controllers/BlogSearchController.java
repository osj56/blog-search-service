package com.internet.blogsearchapi.controllers;

import com.internet.blogsearchapi.dtos.BaseResponse;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.services.BlogSearchService;
import com.internet.blogsearchcommon.entities.KeywordCount;
import com.internet.blogsearchcommon.enums.ResultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog-search")
public class BlogSearchController {
    final BlogSearchService blogSearchService;

    @GetMapping("/{companyName}")
    public BaseResponse<BlogSearchResponse> getBlogSearchResult(@PathVariable String companyName,
                                                                @RequestParam("query") String query,
                                                                @RequestParam(value = "sort", required = false) String sort,
                                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return BaseResponse.<BlogSearchResponse>builder()
                .resultCode(ResultCode.SUCCEEDED)
                .data(blogSearchService.requestBlogSearchApi(companyName, query, sort, page, size))
                .build();
    }

    @GetMapping("/popular-keyword")
    public BaseResponse<List<KeywordCount>> getBlogSearchPopularKeyword() {
        return BaseResponse.<List<KeywordCount>>builder()
                .resultCode(ResultCode.SUCCEEDED)
                .data(blogSearchService.getBlogSearchPopularKeywordTop10List())
                .build();
    }
}
