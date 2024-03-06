package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentByCashOnDelivery extends Payment {
    public PaymentByCashOnDelivery(String id, Order order, String method, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }

    public PaymentByCashOnDelivery(String id, Order order, String method, Map<String, String > paymentData) {
        super(id, method, order, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.get("address").isBlank() || paymentData.get("deliveryFee").isBlank()) {
            throw new IllegalArgumentException();
        }

        this.paymentData = paymentData;
    }
}