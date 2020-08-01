package com.leadiro.starter.service.search.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class SearchDocumentResponseDto {
    private String recordType;
    private List<String> keywords;
    private String displayTitle;
    private List<MediaDto> media;

    // This is created to support case insensitive keyword search without modifying the data
    @JsonIgnore
    private String flattenKeywords;

    public String getFlattenKeywords() {
        String result = "";
        if (null != keywords && !keywords.isEmpty()) {
            result = "|" + String.join("|", keywords).toLowerCase() + "|";
        }
        return result;
    }
}
