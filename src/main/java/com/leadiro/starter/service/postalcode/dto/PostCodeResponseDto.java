package com.leadiro.starter.service.postalcode.dto;

import lombok.Data;

@Data
public class PostCodeResponseDto {
    /**
     * Http status code.
     */
    private Long status;

    /**
     * Error message.
     */
    private String error;

    /**
     * Result flag, true if valid otherwise false.
     */
    private Boolean result;
}
