package com.leadiro.starter.service.search;

import com.leadiro.starter.service.FetchDataService;
import com.leadiro.starter.service.search.dto.MediaDto;
import com.leadiro.starter.service.search.dto.SearchByKeywordDto;
import com.leadiro.starter.service.search.dto.SearchDocumentResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class SearchService
        implements FetchDataService<SearchDocumentResponseDto> {
    /**
     * URL data source.
     */
    @Value("${exam.data.source.url}")
    private String url;

    /**
     * Search by keywords.
     * @param keywords Keywords to search
     * @return List of objects related to keywords
     */
    public List<SearchByKeywordDto> searchByKeywords(
            final List<String> keywords) {
        List<SearchDocumentResponseDto> data = fetchData();
        List<SearchByKeywordDto> result = new ArrayList<>();
        List<Integer> usedIndex = new ArrayList<>();
        int mainRecordIndex;
        // TODO XXX Optimize by querying JSON document like in MongoDb
        // Make it work for now for test purposes.
        for (String searchKeyword : keywords) {
            mainRecordIndex = 0;
            for (SearchDocumentResponseDto item: data) {
                String keyword = "|" + searchKeyword.toLowerCase() + "|";
                if (item.getFlattenKeywords().contains(keyword)
                        && !usedIndex.contains(mainRecordIndex)) {
                    for (MediaDto mediaDto : item.getMedia()) {
                        SearchByKeywordDto keywordDto
                                = new SearchByKeywordDto();
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

    /**
     * Search by Id.
     * @param id Record identifier.
     * @return Record identified by the id
     */
    public SearchDocumentResponseDto searchFullRecordById(final String id) {
        List<SearchDocumentResponseDto> data = fetchData();
        // TODO XXX Optimize by querying JSON document like in MongoDb
        // Make it work for now for test purposes.
        for (SearchDocumentResponseDto item: data) {
            for (MediaDto media : item.getMedia()) {
                if (media.getId().equals(id)) {
                    return item;
                }
            }
        }
        return null;
    }

    /**
     * Fetches data.
     * @return data
     */
    @Override
    public List fetchData() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        log.debug("URL {}", this.url);
        SearchDocumentResponseDto[] response;
        response = restTemplate.exchange(this.url, HttpMethod.GET, entity,
                SearchDocumentResponseDto[].class).getBody();
        log.debug("response {}", response[0].toString());
        List<SearchDocumentResponseDto> result = Arrays.asList(response);
        return result;
    }
}
