package com.example.demo.context;

import com.example.demo.enums.OperationEnum;
import com.example.demo.service.OperationService;

public interface OperationStrategyContextFactory {
    OperationService get(OperationEnum e);
    OperationService get(String operation);
}
