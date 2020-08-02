package com.leadiro.starter.service.name.dto;

import lombok.Data;

@Data
public class NameProcessDto {

    /**
     * Name detail.
     */
    private NameDto name;

    /**
     * Processing.
     */
    private String processing;

    /**
     * Unprocessed original input.
     */
    private String originalInput;
}
