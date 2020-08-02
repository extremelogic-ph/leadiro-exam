package com.leadiro.starter.controller

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@CompileStatic
@SpringBootTest
class ValidateControllerTest {
    @Autowired private ValidateController controller

    @Test
    void validateEmail() {
        def actual = controller.validateEmail('john.doe@gmail.com')
        Assertions.assertEquals("ValidationResponseDto(valid=true)", actual.toString(), "Email valid");
        actual = controller.validateEmail('john.doe@gmail')
        Assertions.assertEquals("ValidationResponseDto(valid=false)", actual.toString(), "Email invalid");
        println(actual.toString())
    }

    @Test
    void validatePostCode() {
        def actual = controller.validatePostCode("EC2Y 9DT")
        println(actual.toString())
        Assertions.assertEquals("PostCodeDetailDto(result=PostCodeResultDto(region=London), valid=true)", actual.toString(), "Post code valid");
        actual = controller.validatePostCode('XXXX')
        Assertions.assertEquals("PostCodeDetailDto(result=null, valid=false)", actual.toString(), "Post code invalid");
        println(actual.toString())
    }
}
