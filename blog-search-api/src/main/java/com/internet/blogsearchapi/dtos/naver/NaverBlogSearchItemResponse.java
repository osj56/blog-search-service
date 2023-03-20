package com.internet.blogsearchapi.dtos.naver;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NaverBlogSearchItemResponse {
    String title;
    String link;
    String description;
    String bloggername;
    String bloggerlink;
    String postdate;
}
