package com.leadiro.starter.controller;


import com.leadiro.starter.service.email.ValidateEmailService;
import com.leadiro.starter.service.postalcode.ValidatePostalCodeService;
import groovy.transform.CompileStatic;
import com.leadiro.starter.service.ValidateService;
import groovy.transform.CompileStatic
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@CompileStatic
public class ValidateController {
    /**
     * Use validate email service.
     */
    @Autowired
    private ValidateEmailService validateEmail;

    /**
     * Use validate post code service.
     */
    @Autowired
    private ValidatePostalCodeService validatePostCodeService;

    /**
     * Validate email.
     * @param email Email to validate
     * @return Status object of validation
     */
    @GetMapping(value = "/validate/email")
    @ApiOperation(value = "",
            authorizations = [ @Authorization(value="basicAuth") ])
    def validateEmail(
            @RequestParam(value = "email", required = true)
            final String email) {
        return validateEmail.validate(email);
    }

    /**
     * Validate post code.
     * @param postCode Code to validate
     * @return Status object of validation
     */
    @GetMapping(value = "/validate/postcode")
    @ApiOperation(value = "",
            authorizations = [ @Authorization(value="basicAuth") ])
    def validatePostCode(
            @RequestParam(value = "postcode", required = true)
            final String postCode) {
        return validatePostCodeService.validate(postCode);
    }
}
