package com.example.demo.controller;

import com.example.demo.enums.OperationEnum;
import com.example.demo.context.OperationStrategyContextFactory;
import com.example.demo.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @Autowired
    OperationStrategyContextFactory operationStrategyContextFactory;
    @RequestMapping("add")
    public Double add(){
        Double a = 100d;
        Double b = 200d;
        OperationService operationService = operationStrategyContextFactory.get(OperationEnum.ADD);
        OperationService operationService1 = operationStrategyContextFactory.get("ADD");
        Double c = operationService.operate(a, b);
        Double c1 = operationService1.operate(a, b);
        System.out.println(c);
        System.out.println(c1);
        return c;
    }

    @RequestMapping("times")
    public Double times(){
        Double a = 100d;
        Double b = 200d;
        OperationService operationService = operationStrategyContextFactory.get(OperationEnum.TIMES);
        OperationService operationService1 = operationStrategyContextFactory.get("TIMES");
        Double c = operationService.operate(a, b);
        Double c1 = operationService1.operate(a, b);
        System.out.println(c);
        System.out.println(c1);
        return c;
    }

}
