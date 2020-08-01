package com.leadiro.starter.service.search.dto;

import lombok.Data;

@Data
public class SearchByKeywordDto {
    /**
     * Media identifier.
     */
    private String id;

    /**
     * Caption or title.
     */
    private String caption;
}
