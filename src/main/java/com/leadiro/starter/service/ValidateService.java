package com.leadiro.starter.service;

import com.leadiro.starter.dto.ValidationResponseDto;
import com.leadiro.starter.service.postalcode.PostalCodeService;
import com.leadiro.starter.service.postalcode.dto.PostCodeDetailDto;
import com.leadiro.starter.service.postalcode.dto.PostCodeResponseDto;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {
    /**
     * Using postal code service.
     */
    @Autowired
    private PostalCodeService postalCodeService;

    /**
     * Email pattern.
     */
    @Value("${exam.validation.postal.email-pattern}")
    private String emailPattern;

    /**
     * Validates email.
     * @param email Email to validate
     * @return Status/Validation object
     */
    public ValidationResponseDto validateEmail(final String email) {
        ValidationResponseDto responseDto = new ValidationResponseDto();
        boolean valid = false;
        if (isEmail(email)) {
            valid = true;
        }
        responseDto.setValid(valid);
        return responseDto;
    }

    /**
     * Validates email.
     * @param email
     * @return true if its an email otherwise false
     */
    // TODO Place this in a common package for reuse
    private boolean isEmail(final String email) {
        return email.toUpperCase().matches(this.emailPattern);
    }

    /**
     * Validates a post code.
     * @param postCode Post code to validate
     * @return Status/Validation object
     */
    public PostCodeDetailDto validatePostCode(final String postCode) {
        PostCodeDetailDto detailDto = new PostCodeDetailDto();
        PostCodeResponseDto codeResponseDto =
                postalCodeService.validatePostCode(postCode);
        if (Response.SC_OK == codeResponseDto.getStatus()) {
            detailDto.setValid(true);
            detailDto.setResult(codeResponseDto.getResult());

        } else {
            detailDto.setValid(false);
            detailDto.setResult(null);
        }
        return detailDto;
    }
}
