package com.leadiro.starter.controller

import com.leadiro.starter.service.name.NameService;
import groovy.transform.CompileStatic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@CompileStatic
public class NameCleanController {
    /**
     * Use name clean service.
     */
    @Autowired
    private NameService nameService;

    @PostMapping(value = "/parse/name")
    def parseName(
            @RequestBody(required = true)
            final List<String> names) {
        return nameService.processNames(names);
    }
}

