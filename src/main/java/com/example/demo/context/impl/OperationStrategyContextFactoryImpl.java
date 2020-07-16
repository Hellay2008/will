package com.example.demo.context.impl;

import com.example.demo.enums.OperationEnum;
import com.example.demo.context.OperationStrategyContextFactory;
import com.example.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OperationStrategyContextFactoryImpl implements OperationStrategyContextFactory {

    @Autowired
    private Map<String, OperationService> operationServices = new HashMap<>();

    @Override
    public OperationService get(OperationEnum e) {
        return operationServices.get(e.operationServiceName);
    }

    @Override
    public OperationService get(String operation) {
        return operationServices.get(OperationEnum.get(operation).operationServiceName);
    }
}
