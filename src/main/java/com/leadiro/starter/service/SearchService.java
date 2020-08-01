package com.leadiro.starter.service;

import com.leadiro.starter.service.search.FetchDataService;
import com.leadiro.starter.service.search.dto.MediaDto;
import com.leadiro.starter.service.search.dto.SearchDocumentResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchService {
    @Autowired
    private FetchDataService fetchDataService;

    public List<SearchDocumentResponseDto> searchByKeywords(List<String> keywords) {
        List <SearchDocumentResponseDto> data = fetchDataService.fetchData();
        List <SearchDocumentResponseDto> result= new ArrayList<>();
        // TODO XXX Optimize by querying JSON document like in MongoDb
        // Make it work for now for test purposes.
        for (String searchKeyword : keywords) {
            for (SearchDocumentResponseDto item: data) {
                String keyword = "|" + searchKeyword.toLowerCase() + "|";
                if (item.getFlattenKeywords().contains(keyword)) {
                    log.info("flattened keywords: {}", item.getFlattenKeywords());
                    log.info("keyword: {}", keyword);
                    result.add(item);
                }
            }
        }
        return result;
    }

    public List<MediaDto> searchFullRecordById(final String id) {
        List <SearchDocumentResponseDto> data = fetchDataService.fetchData();
        List <MediaDto> result= new ArrayList<>();
        // TODO XXX Optimize by querying JSON document like in MongoDb
        // Make it work for now for test purposes.
        for (SearchDocumentResponseDto item: data) {
            for (MediaDto media : item.getMedia() ) {
                if (media.getId().equals(id)) {
                    result.add(media);
                }
            }
        }
        return result;
    }
}
