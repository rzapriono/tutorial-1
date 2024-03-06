package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PaymentByCashOnDeliveryTest {
    List<Product> products;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        products.add(product2);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Bambang Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                products, 1708570000L, "Salsabila Sudrajat");
        orders.add(order3);
    }

    @Test
    void testCreatePaymentByCashOnDeliverySuccessful() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "Akses UI");
        paymentDataCashOnDelivery.put("deliveryFee", "9000");

        Payment payment = new PaymentByCashOnDelivery("af47141e-dae3-11ee-a506-0242ac120002", orders.get(0), PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery);
        assertSame(orders.get(0), payment.getOrder());
        assertEquals(paymentDataCashOnDelivery, payment.getPaymentData());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", payment.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), payment.getMethod());
    }

    @Test
    void testCreatePaymentCashOnDeliveryWithStatus() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "Akses UI");
        paymentDataCashOnDelivery.put("deliveryFee", "9000");

        PaymentByCashOnDelivery PaymentCashOnDelivery = new PaymentByCashOnDelivery("af47141e-dae3-11ee-a506-0242ac120002",orders.get(0), PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), PaymentCashOnDelivery.getOrder());
        assertEquals("af47141e-dae3-11ee-a506-0242ac120002", PaymentCashOnDelivery.getId());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), PaymentCashOnDelivery.getMethod());
        assertEquals(paymentDataCashOnDelivery, PaymentCashOnDelivery.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), PaymentCashOnDelivery.getStatus());
    }

    @Test
    void testCreatePaymentFailedEmptyAddress() {
        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "");
        paymentDataCashOnDelivery.put("deliveryFee", "9000");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByCashOnDelivery("af47141e-dae3-11ee-a506-0242ac120002", orders.get(1),PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery);
        });
    }

    @Test
    void testCreatePaymentFailedEmptyDeliveryFee() {
        Map<String, String> paymentDataCashOnDelivery = new  HashMap<>();
        paymentDataCashOnDelivery.put("address", "Akses UI");
        paymentDataCashOnDelivery.put("deliveryFee", "");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByCashOnDelivery("af47141e-dae3-11ee-a506-0242ac120002", orders.get(1),PaymentMethod.CASH_ON_DELIVERY.getValue(), paymentDataCashOnDelivery);
        });
    }
}