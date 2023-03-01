package com.example.foodroads.integrationTest;

import com.example.foodroads.FoodRoadsApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {FoodRoadsApplication.class})
@ActiveProfiles("test")
@Import({TestRedisConfig.class})
public abstract class IntegrationTest {
}
