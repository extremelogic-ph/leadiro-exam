package com.leadiro.starter.service.search;

import com.leadiro.starter.service.search.dto.SearchDocumentResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class FetchDataService {
    /**
     * URL data source.
     */
    @Value("${exam.data.source.url}")
    private String url;

    public List<SearchDocumentResponseDto> fetchData() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<>(headers);
        log.debug("URL {}", this.url);
        SearchDocumentResponseDto [] response;
        response = restTemplate.exchange(this.url, HttpMethod.GET, entity,
                SearchDocumentResponseDto[].class).getBody();
        log.debug("response {}", response[0].toString());
        List<SearchDocumentResponseDto> result = Arrays.asList(response);
        return result;
    }
}
