package com.mirai.importback.cucumber;

import com.mirai.importback.ImportbackApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = ImportbackApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = ImportbackApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}
