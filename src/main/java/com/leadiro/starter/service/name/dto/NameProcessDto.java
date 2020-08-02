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
    private String processing = "";

    /**
     * Unprocessed original input.
     */
    private String originalInput = "";

    /**
     * Sets the process result.
     * @param process Process result
     */
    public void setProcessing(final String process) {
        if (null != process && !process.isEmpty()) {
            this.processing = process.trim();
        }
    }
}
