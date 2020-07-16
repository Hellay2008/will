### diagram

![](C:\Users\o-will.gongye\Pictures\4.png)

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
   <modelVersion>4.0.0</modelVersion>
   <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.3.1.RELEASE</version>
      <relativePath/> <!-- lookup parent from repository -->
   </parent>
   <groupId>com.example</groupId>
   <artifactId>demo</artifactId>
   <version>0.0.1-SNAPSHOT</version>
   <name>demo</name>
   <description>Demo project for Spring Boot</description>

   <properties>
      <java.version>1.8</java.version>
      <powermock.version>2.0.7</powermock.version>
   </properties>

   <dependencies>
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
      </dependency>

      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-devtools</artifactId>
         <scope>runtime</scope>
         <optional>true</optional>
      </dependency>
      <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
         <scope>test</scope>
         <exclusions>
            <exclusion>
               <groupId>org.junit.vintage</groupId>
               <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
         </exclusions>
      </dependency>

      <dependency>
         <groupId>org.junit.platform</groupId>
         <artifactId>junit-platform-launcher</artifactId>
         <scope>test</scope>
      </dependency>

   </dependencies>

   <build>
      <plugins>
         <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
         </plugin>
      </plugins>
   </build>

</project>
```

### 1 OperationService.java

```java
package com.example.demo.service;

public interface OperationService {
    Double operate(Double a, Double b);
}
```

### 2 AddOperationServiceImpl.java

```java
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
```

### 3 TimesOperationServiceImpl.java

```java
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
```

### 4 OperationEnum.java

```java
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
```

### 5 OperationStrategyContextFactory.java

```java
package com.example.demo.context;

import com.example.demo.enums.OperationEnum;
import com.example.demo.service.OperationService;

public interface OperationStrategyContextFactory {
    OperationService get(OperationEnum e);
    OperationService get(String operation);
}
```

### 6 OperationStrategyContextFactoryImpl.java

```java
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
```

### 7 Junit Test DemoApplicationTests.java

```java
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
```

### 8 TestController.java

```java
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
```

### 小结

策略工厂接口可以添加多个get接口get不同的service，变成集中的策略工厂类

使用枚举类型可以让代码更加优雅