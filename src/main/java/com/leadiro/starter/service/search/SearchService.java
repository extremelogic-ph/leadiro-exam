package com.leadiro.starter.service.search;

import com.leadiro.starter.service.FetchDataService;
import com.leadiro.starter.service.search.dto.MediaDto;
import com.leadiro.starter.service.search.dto.SearchByKeywordDto;
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

    public List<SearchByKeywordDto> searchByKeywords(List<String> keywords) {
        List <SearchDocumentResponseDto> data = fetchDataService.fetchData();
        List <SearchByKeywordDto> result= new ArrayList<>();
        List <Integer> usedIndex = new ArrayList<>();
        int mainRecordIndex;
        // TODO XXX Optimize by querying JSON document like in MongoDb
        // Make it work for now for test purposes.
        for (String searchKeyword : keywords) {
            mainRecordIndex = 0;
            for (SearchDocumentResponseDto item: data) {
                String keyword = "|" + searchKeyword.toLowerCase() + "|";
                if (item.getFlattenKeywords().contains(keyword) && !usedIndex.contains(mainRecordIndex)) {
                    for (MediaDto mediaDto : item.getMedia()) {
                        SearchByKeywordDto keywordDto = new SearchByKeywordDto();
                        keywordDto.setId(mediaDto.getId());
                        keywordDto.setCaption(mediaDto.getCaption());
                        result.add(keywordDto);
                        usedIndex.add(mainRecordIndex);
                    }
                }
                mainRecordIndex++;
            }
        }
        return result;
    }

    public SearchDocumentResponseDto searchFullRecordById(final String id) {
        List <SearchDocumentResponseDto> data = fetchDataService.fetchData();
        // TODO XXX Optimize by querying JSON document like in MongoDb
        // Make it work for now for test purposes.
        for (SearchDocumentResponseDto item: data) {
            for (MediaDto media : item.getMedia() ) {
                if (media.getId().equals(id)) {
                    return item;
                }
            }
        }
        return null;
    }
}
