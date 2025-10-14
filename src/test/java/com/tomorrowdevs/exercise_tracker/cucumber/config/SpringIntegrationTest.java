package com.tomorrowdevs.exercise_tracker.cucumber.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@SpringBootTest
public class SpringIntegrationTest {
    // vuota: serve solo ad avviare il contesto Spring per Cucumber
}