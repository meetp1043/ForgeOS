package com.forgeos.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class SecurityTests {

    @Test
    void securityContextLoads() {
        // Context loading implies SecurityFilterChain is correctly configured
        // without circular dependencies or unresolvable Beans.
        assertTrue(true);
    }
}
