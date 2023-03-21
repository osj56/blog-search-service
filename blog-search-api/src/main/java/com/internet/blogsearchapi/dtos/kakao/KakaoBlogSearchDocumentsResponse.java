package com.internet.blogsearchapi.dtos.kakao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoBlogSearchDocumentsResponse {
    String title;
    String contents;
    String url;
    String blogname;
    String thumbnail;
    String datetime;
}
