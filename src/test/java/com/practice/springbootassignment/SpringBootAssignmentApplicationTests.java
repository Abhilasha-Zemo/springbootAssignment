package com.practice.springbootassignment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBootAssignmentApplicationTests {

    @Test
    void contextLoads() {
        SpringBootAssignmentApplication.main(new String[]{});
        assertTrue(true);
    }

}
