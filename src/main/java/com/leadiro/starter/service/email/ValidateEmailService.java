package com.leadiro.starter.service.email;

import com.leadiro.starter.dto.ValidationResponseDto;
import com.leadiro.starter.service.ValidateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidateEmailService
        implements ValidateService<ValidationResponseDto> {
    // TODO check response DTO above if can be placed in this package
    /**
     * Email pattern.
     */
     @Value("${exam.validation.postal.email-pattern}")
     private String emailPattern;

    /**
     * Validate email.
     * @param toValidate String to validate
     * @return validation response
     */
    @Override
    public ValidationResponseDto validate(final String toValidate) {
        ValidationResponseDto responseDto = new ValidationResponseDto();
        boolean valid = false;
        if (isEmail(toValidate)) {
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
    // TODO Place this in a common package or library for reuse
    private boolean isEmail(final String email) {
        return email.toUpperCase().matches(this.emailPattern);
    }
}
