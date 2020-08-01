package com.leadiro.starter.controller;

import com.leadiro.starter.service.SearchService;
import com.leadiro.starter.service.search.dto.MediaDto;
import com.leadiro.starter.service.search.dto.SearchDocumentResponseDto;
import groovy.transform.CompileStatic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@CrossOrigin
@RestController
@CompileStatic
public class SearchController {
    /**
     * Use search service.
     */
    @Autowired
    private SearchService searchService;

    /**
     * Search by keywords.
     * @param keywords Keywords to search
     * @return SearchDocumentResponseDto object
     */
    @GetMapping(value = "/museum")
    def searchByKeyword(
            @RequestParam(value = "keyword", required = true)
            final List<String> keywords) {
        List<SearchDocumentResponseDto> searchDocumentResponseDto;
        searchDocumentResponseDto = searchService.searchByKeywords(keywords);
        return searchDocumentResponseDto;
    }

    /**
     * Search by id.
     * @param request To retrieve the URL
     * @return search result object List<MediaDto>
     */
    @GetMapping(value = "/museum/**")
    def search(final HttpServletRequest request) {
        // TODO This should return only one object since this is
        //  a search via ID. Remove list.
        String requestURL = request.getRequestURL().toString();
        String id = requestURL.split("/museum/")[1];
        return searchService.searchFullRecordById(id);
    }


    private List<MediaDto> searchFullRecordById(final String id) {
        List<MediaDto> mediaDto;
        mediaDto = searchService.searchFullRecordById(id);
        return mediaDto;
    }
}