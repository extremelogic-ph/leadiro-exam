package com.leadiro.starter.service.postalcode;

import com.leadiro.starter.service.postalcode.dto.PostCodeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j
public class PostalCodeService {
    /**
     * Postal service URL.
     */
    @Value("${exam.validation.postal.url}")
    private String url;

    /**
     * Validate post code.
     * @param postCode Code to validate
     * @return Validation/Status object
     */
    public PostCodeResponseDto validatePostCode(final String postCode) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PostCodeResponseDto> entity = new HttpEntity<>(headers);
        String apiUrl = this.url.replace(":postcode", postCode);
        log.debug("validatePostCode {},{}", postCode, apiUrl);
        try {
            return restTemplate.exchange(apiUrl, HttpMethod.GET, entity,
                    PostCodeResponseDto.class).getBody();
        } catch (HttpClientErrorException e) {
            PostCodeResponseDto responseDto = new PostCodeResponseDto();
            responseDto.setStatus(e.getStatusCode().value());
            return responseDto;
        }
    }
}
