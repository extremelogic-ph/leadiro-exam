package com.leadiro.starter.service.postalcode.dto;

import lombok.Data;

@Data
public class PostCodeDetailDto {
    /**
     * Post region.
     */
    private PostCodeResultDto result;

    /**
     * True if post code is valid otherwise false.
     */
    private Boolean valid;
}
