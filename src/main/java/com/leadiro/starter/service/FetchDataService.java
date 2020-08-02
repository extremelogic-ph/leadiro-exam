package com.leadiro.starter.service;

import com.leadiro.starter.service.search.dto.SearchDocumentResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FetchDataService {
    /**
     * URL data source.
     */
    @Value("${exam.data.source.url}")
    private String url;

    /**
     * Fetches the remote data.
     * @return Object representation of the retrieved data
     */
    public List<SearchDocumentResponseDto> fetchData() {
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

    /**
     * Retrieve last name data.
     * @return List of last names
     */
    public List<String> fetchFirstNames() {
        List<String> names = null;
        try {
            // TODO Load this into a database
            File file = ResourceUtils.getFile("classpath:data/firstname.txt");
            try (Stream<String> stream = Files.lines(
                    Paths.get(file.getPath()))) {
                names = stream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
        return names;
    }

    /**
     * Retrieve last name data.
     * @return list of first names
     */
    public List<String> fetchLastNames() {
        List<String> names = null;
        try {
            // TODO Load this into a database
            File file = ResourceUtils.getFile("classpath:data/surname.txt");
            try (Stream<String> stream = Files.lines(
                    Paths.get(file.getPath()))) {
                names = stream.collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
        }
        return names;
    }

    /**
     * Retrieve method calls for cleansing flow.
     * @return List of method name to call
     */
    public List<String> fetchMethodCalls() {
        List<String> methodCall = new ArrayList<>();
        // TODO contents here are supposed to be in the database
        //  order of execution should also be considered
        methodCall.add("processNameSpaces");
        methodCall.add("processLastName");
        return methodCall;
    }
}
