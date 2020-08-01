package com.leadiro.starter.service.search.dto;

import lombok.Data;

@Data
public class MediaDto {
    private String type;
    private String alternativeText;
    private DimensionDto large;
    private DimensionDto medium;
    private DimensionDto small;
    private DimensionDto thumbnail;
    private String id;
    // TODO This is date, do the format
    private String dateModified;
    private String caption;
    private String[] creators;
    private String[] sources;
    private String credit;
    private String rightsStatement;
    private LicenceDto licence;
}
