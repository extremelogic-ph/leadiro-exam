package com.leadiro.starter.service.search.dto;

import lombok.Data;

@Data
public class LicenceDto {
    /**
     * Licence name detail.
     */
    private String name;

    /**
     * Licence short name detail.
     */
    private String shortName;

    /**
     * Licence URI detail.
     */
    private String uri;
}
