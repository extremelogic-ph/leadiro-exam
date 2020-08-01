package com.leadiro.starter.dto;

import lombok.Data;

@Data
public class ValidationResponseDto {
    /**
     * Result flag, true if valid otherwise false.
     */
    private Boolean valid;
}
