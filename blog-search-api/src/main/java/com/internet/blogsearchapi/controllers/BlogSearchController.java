package com.internet.blogsearchapi.controllers;

import com.internet.blogsearchapi.dtos.BlogSearchRequest;
import com.internet.blogsearchapi.dtos.BlogSearchResponse;
import com.internet.blogsearchapi.services.BlogSearchService;
import com.internet.blogsearchcommon.entities.KeywordCount;
import com.internet.blogsearchcommon.enums.CompanyName;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog-search-api")
public class BlogSearchController {
    final BlogSearchService blogSearchService;

    @PostMapping("/{companyName}")
    public BlogSearchResponse getBlogSearchResult(@PathVariable CompanyName companyName, @RequestBody BlogSearchRequest blogSearchRequest){
        return blogSearchService.requestBlogSearchApi(companyName, blogSearchRequest);
    }

    @GetMapping("/popular-keyword")
    public List<KeywordCount> getBlogSearchPopularKeyword(){
        return blogSearchService.getBlogSearchPopularKeywordTop10List();
    }
}
