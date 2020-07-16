package com.example.demo;

import com.example.demo.context.OperationStrategyContextFactory;
import com.example.demo.enums.OperationEnum;
import com.example.demo.service.OperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	OperationStrategyContextFactory operationStrategyContextFactory;

	@Test
	void contextLoads() {

	}

	@Test
	public void add(){
		Double a = 100d;
		Double b = 200d;
		OperationService operationService = operationStrategyContextFactory.get(OperationEnum.ADD);
		OperationService operationService1 = operationStrategyContextFactory.get("ADD");
		Double c = operationService.operate(a, b);
		Double c1 = operationService1.operate(a, b);
		Assertions.assertEquals(300d, c);
		Assertions.assertEquals(300d, c1);
	}

	@Test
	public void times(){
		Double a = 100d;
		Double b = 200d;
		OperationService operationService = operationStrategyContextFactory.get(OperationEnum.TIMES);
		OperationService operationService1 = operationStrategyContextFactory.get("TIMES");
		Double c = operationService.operate(a, b);
		Double c1 = operationService1.operate(a, b);
		Assertions.assertEquals(20000d, c);
		Assertions.assertEquals(20000d, c1);
	}

}
