package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentByVoucherCode extends Payment {
    public PaymentByVoucherCode(String id, Order order, String method, Map<String, String> paymentData, String status) {
        super(id, method, order, paymentData, status);
    }

    public PaymentByVoucherCode(String id, Order order, String method, Map<String, String >paymentData) {
        super(id, method, order, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {

    }
}