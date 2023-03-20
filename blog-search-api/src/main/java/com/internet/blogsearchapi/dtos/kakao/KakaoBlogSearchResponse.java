package com.internet.blogsearchapi.dtos.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KakaoBlogSearchResponse {
    @JsonProperty("meta")
    KakaoBlogSearchMetaResponse kakaoBlogSearchMetaResponse;
    @JsonProperty("documents")
    List<KakaoBlogSearchDocumentsResponse> kakaoBlogSearchDocumentsResponseList;
}
