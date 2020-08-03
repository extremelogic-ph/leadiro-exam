package com.leadiro.starter.service.postalcode;

import com.leadiro.starter.service.ValidateService;
import com.leadiro.starter.service.postalcode.dto.PostCodeDetailDto;
import com.leadiro.starter.service.postalcode.dto.PostCodeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
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
public class ValidatePostalCodeService
        implements ValidateService<PostCodeDetailDto> {
    /**
     * Postal service URL.
     */
    @Value("${exam.validation.postal.url}")
    private String url;

    private PostCodeDetailDto processPostCodeResponse(
            final PostCodeResponseDto codeResponseDto) {
        PostCodeDetailDto detailDto = new PostCodeDetailDto();

        if (Response.SC_OK == codeResponseDto.getStatus()) {
            detailDto.setValid(true);
            detailDto.setResult(codeResponseDto.getResult());

        } else {
            detailDto.setValid(false);
            detailDto.setResult(null);
        }
        return detailDto;
    }

    /**
     * Validates post code.
     * @param toValidate String to validate
     * @return Validation response object
     */
    @Override
    public PostCodeDetailDto validate(final String toValidate) {
        PostCodeResponseDto responseDto = null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PostCodeResponseDto> entity = new HttpEntity<>(headers);
        String apiUrl = this.url.replace(":postcode", toValidate);
        log.debug("validatePostCode {},{}", toValidate, apiUrl);
        try {
            responseDto = restTemplate.exchange(apiUrl, HttpMethod.GET, entity,
                    PostCodeResponseDto.class).getBody();
        } catch (HttpClientErrorException e) {
            responseDto = new PostCodeResponseDto();
            responseDto.setStatus(e.getStatusCode().value());
        }
        return processPostCodeResponse(responseDto);
    }
}
