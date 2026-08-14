package com.forgeos.observability.domain;

import com.forgeos.organization.domain.security.TenantContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class MdcContextFilterTests {

    private MdcContextFilter filter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        filter = new MdcContextFilter();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        MDC.clear();
        TenantContextHolder.clear();
    }

    @AfterEach
    void tearDown() {
        MDC.clear();
        TenantContextHolder.clear();
    }

    @Test
    void testMdcPopulatedWithRequestIdAndTenantId() throws Exception {
        when(request.getHeader("X-Request-ID")).thenReturn("req-123");
        UUID tenantId = UUID.randomUUID();
        TenantContextHolder.setTenantId(tenantId);

        // We use a mock filter chain to verify MDC state during execution
        doAnswer(invocation -> {
            assertEquals("req-123", MDC.get("requestId"));
            assertEquals(tenantId.toString(), MDC.get("tenantId"));
            return null;
        }).when(filterChain).doFilter(request, response);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        // Ensure MDC is cleared after filter completes
        assertNull(MDC.get("requestId"));
        assertNull(MDC.get("tenantId"));
    }

    @Test
    void testMdcPopulatedWithGeneratedRequestIdWhenMissing() throws Exception {
        when(request.getHeader("X-Request-ID")).thenReturn(null);

        doAnswer(invocation -> {
            assertNotNull(MDC.get("requestId"));
            return null;
        }).when(filterChain).doFilter(request, response);

        filter.doFilterInternal(request, response, filterChain);
    }
}
