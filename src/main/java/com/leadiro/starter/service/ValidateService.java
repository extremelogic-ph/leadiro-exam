package com.leadiro.starter.service;

import org.springframework.stereotype.Service;

@Service
public interface ValidateService<T> {
    T validate(final String toValidate);
}
