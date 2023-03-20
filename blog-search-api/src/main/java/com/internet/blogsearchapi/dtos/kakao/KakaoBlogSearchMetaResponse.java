package com.internet.blogsearchapi.dtos.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoBlogSearchMetaResponse {
    @JsonProperty("total_count")
    int totalCount;
    @JsonProperty("pageable_count")
    int pageableCount;
    @JsonProperty("is_end")
    boolean isEnd;
}
