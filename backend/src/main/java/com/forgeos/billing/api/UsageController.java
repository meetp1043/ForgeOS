package com.forgeos.billing.api;

import com.forgeos.billing.application.UsageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usage")
public class UsageController {

    private final UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    @GetMapping("/summary")
    public ResponseEntity<Object> getUsageSummary(@RequestParam UUID tenantId) {
        // Retrieve current usage metrics vs quotas for the tenant
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/limits")
    public ResponseEntity<Object> getUsageLimits(@RequestParam UUID tenantId) {
        // Retrieve explicit plan limits without calculating current usage
        return ResponseEntity.ok().build();
    }
}
