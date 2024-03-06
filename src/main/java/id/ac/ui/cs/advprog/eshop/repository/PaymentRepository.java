package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        for (Payment existingPayment : paymentData) {
            if (existingPayment.getId().equals(payment.getId())) {
                throw new IllegalStateException("Payment with id " + payment.getId() + " already exists.");
            }
        }
        paymentData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment payment : paymentData) {
            if (payment.getId().equals(id)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentData);
    }
}