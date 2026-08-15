package com.forgeos.billing.infrastructure;

import com.forgeos.billing.domain.PaymentProvider;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

@Component
public class MockPaymentProvider implements PaymentProvider {

    private static final Logger logger = Logger.getLogger(MockPaymentProvider.class.getName());

    @Override
    public String createCustomer(UUID tenantId, String email) {
        logger.info("MockPaymentProvider: Creating customer for tenant " + tenantId);
        return "mock_cus_" + UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String createSubscription(String customerId, String priceId) {
        logger.info("MockPaymentProvider: Creating subscription for customer " + customerId);
        return "mock_sub_" + UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public void cancelSubscription(String providerSubscriptionId) {
        logger.info("MockPaymentProvider: Canceling subscription " + providerSubscriptionId);
    }

    @Override
    public void updateSubscription(String providerSubscriptionId, String newPriceId) {
        logger.info("MockPaymentProvider: Updating subscription " + providerSubscriptionId + " to price " + newPriceId);
    }

    @Override
    public String createCheckoutSession(String customerId, String priceId, String successUrl, String cancelUrl) {
        logger.info("MockPaymentProvider: Creating checkout session for customer " + customerId);
        return "mock_cs_" + UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public String getPaymentStatus(String sessionId) {
        // Always simulate success in local development mock
        return "PAID";
    }

    @Override
    public String createInvoice(String customerId, String description, long amountCents, String currency) {
        logger.info("MockPaymentProvider: Creating invoice for customer " + customerId + " amount: " + amountCents);
        return "mock_inv_" + UUID.randomUUID().toString().replace("-", "");
    }
}
