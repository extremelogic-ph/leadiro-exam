package com.leadiro.starter.service.search.dto;

import lombok.Data;

@Data
public class DimensionDto {
    /**
     * Object width.
     */
    private Integer width;

    /**
     * Object height.
     */
    private Integer height;

    /**
     * Object URI.
     */
    private String uri;

    /**
     * Object size.
     */
    private Long size;
}
