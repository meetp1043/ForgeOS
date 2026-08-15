package com.forgeos.billing.domain;

import java.util.UUID;

public interface PaymentProvider {
    
    String createCustomer(UUID tenantId, String email);
    
    String createSubscription(String customerId, String priceId);
    
    void cancelSubscription(String providerSubscriptionId);
    
    void updateSubscription(String providerSubscriptionId, String newPriceId);
    
    String createCheckoutSession(String customerId, String priceId, String successUrl, String cancelUrl);
    
    String getPaymentStatus(String sessionId);
    
    String createInvoice(String customerId, String description, long amountCents, String currency);
}
