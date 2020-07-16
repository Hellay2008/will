package com.example.demo.service.impl;

import com.example.demo.service.OperationService;
import org.springframework.stereotype.Service;

@Service("timesOperationService")
public class TimesOperationServiceImpl implements OperationService {
    @Override
    public Double operate(Double a, Double b) {
        return a * b;
    }
}
