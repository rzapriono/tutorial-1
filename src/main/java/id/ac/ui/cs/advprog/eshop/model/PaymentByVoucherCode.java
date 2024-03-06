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
        int numericsCount = 0;

        if (paymentData.get("voucherCode").length() != 16) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < 16; i++) {
            if (Character.isDigit(paymentData.get("voucherCode").charAt(i))) {
                numericsCount += 1;
            }
        }

        if (!paymentData.get("voucherCode").startsWith("ESHOP") || numericsCount != 8) {
            throw new IllegalArgumentException();
        }

        this.paymentData = paymentData;
    }
}