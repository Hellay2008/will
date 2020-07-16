package com.example.demo.service.impl;

import com.example.demo.service.OperationService;
import org.springframework.stereotype.Service;

@Service("addOperationService")
public class AddOperationServiceImpl implements OperationService{
    @Override
    public Double operate(Double a, Double b) {
        return a+b;
    }
}
