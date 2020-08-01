package com.leadiro.starter.service.postalcode.dto;

import lombok.Data;

@Data
public class PostCodeResponseDto {
    /**
     * Http status code.
     */
    private Integer status;

    /**
     * Error message.
     */
    private String error;

    /**
     * Details about the post code are stored here.
     */
    private PostCodeResultDto result;
}
