package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;

public enum OperationEnum {

    ADD("ADD", "addOperationService"),
    TIMES("TIMES", "timesOperationService")
    ;

    public final String operation;
    public final String operationServiceName;

    private static Map<String, OperationEnum> map = new HashMap<>();

    OperationEnum(String operation, String operationServiceName) {
        this.operation = operation;
        this.operationServiceName = operationServiceName;
    }

    static {
        for(OperationEnum e : OperationEnum.values()){
            map.put(e.name(), e);
        }
    }

    public static OperationEnum get(final String operation) {
        OperationEnum e = map.get(operation);
        if (e == null){
            throw new IllegalArgumentException( "MSG:M070");
        }
        return e;
    }

}
