package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentTest {
    Map<String, String> paymentData;
    List<Product> products;
    Order order;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        this.products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product2);

        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6",
                products, 1708560000L, "Bambang Suryanto");
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                    "", null, paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherPendingStatus() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherSuccessStatus() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData, "SUCCESS");
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherRejectedStatus() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData, "REJECTED");
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherInvalidStatus() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                    "", order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherNullStatus() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                    "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryPendingStatus() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliverySuccessStatus() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData, "SUCCESS");
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryRejectedStatus() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData, "REJECTED");
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryInvalidStatus() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                    "", order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryNullStatus() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                    "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToSuccess() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToRejected() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToInvalidStatus() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToNullStatus() {
        paymentData.put("voucherCode", "ESHOPRZK26");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryToSuccess() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryToRejected() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryToInvalidStatus() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentCashOnDeliveryToNullStatus() {
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");

        Payment payment = new Payment("af47141e-dae3-11ee-a506-0242ac120002",
                "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }
}