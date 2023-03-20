package com.internet.blogsearchapi.dtos.kakao;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class KakaoBlogSearchDocumentsResponse {
    String title;
    String contents;
    String url;
    String blogname;
    String thumbnail;
    LocalDateTime dateTime;
}
