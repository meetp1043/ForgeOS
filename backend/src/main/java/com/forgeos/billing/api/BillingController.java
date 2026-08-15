package com.forgeos.billing.api;

import com.forgeos.billing.application.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/subscription/upgrade")
    public ResponseEntity<Void> upgradeSubscription(@RequestParam UUID tenantId, @RequestParam UUID planId) {
        // Enforce authorization first
        billingService.upgradeSubscription(tenantId, planId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/subscription/cancel")
    public ResponseEntity<Void> cancelSubscription(@RequestParam UUID tenantId) {
        billingService.cancelSubscription(tenantId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/subscription")
    public ResponseEntity<Object> getSubscription(@RequestParam UUID tenantId) {
        // Return active subscription details
        return ResponseEntity.ok().build();
    }
}
