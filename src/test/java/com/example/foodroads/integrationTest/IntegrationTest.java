package com.example.foodroads.integrationTest;

import com.example.foodroads.FoodRoadsApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {FoodRoadsApplication.class})
@ActiveProfiles("dev")
public abstract class IntegrationTest {


}
