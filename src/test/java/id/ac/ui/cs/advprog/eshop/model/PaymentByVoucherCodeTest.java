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

class PaymentByVoucherCodeTest {
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
    void testCreatePaymentSuccessfulVoucherCode() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP2611RZK2138");

        Payment payment = new PaymentByVoucherCode("d5104f2a-dbac-11ee-a506-0242ac120002", orders.get(1), PaymentMethod.VOUCHER_CODE.getValue(), paymentDataVoucher);
        assertSame(orders.get(1), payment.getOrder());
        assertEquals("d5104f2a-dbac-11ee-a506-0242ac120002", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(paymentDataVoucher, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherWithStatus() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP2611RZK2138");

        PaymentByVoucherCode paymentVoucherCode = new PaymentByVoucherCode("bcb22408-dbac-11ee-a506-0242ac120002", orders.get(0),
                PaymentMethod.VOUCHER_CODE.getValue(), paymentDataVoucher, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), paymentVoucherCode.getOrder());
        assertEquals("bcb22408-dbac-11ee-a506-0242ac120002", paymentVoucherCode.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), paymentVoucherCode.getMethod());
        assertEquals(paymentDataVoucher, paymentVoucherCode.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), paymentVoucherCode.getStatus());
    }

    @Test
    void testCreatePaymentFailedImproperLengthVoucherCode() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP2611RZK2138Z");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByVoucherCode("ac8d3be4-dbac-11ee-a506-0242ac120002",orders.get(1),
                    PaymentMethod.VOUCHER_CODE.getValue(), paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentFailedImproperNumericalLengthVoucherCode() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP2611RZK21328");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByVoucherCode("ac8d3be4-dbac-11ee-a506-0242ac120002",orders.get(1),
                    PaymentMethod.VOUCHER_CODE.getValue(), paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentInvalidVoucherTooMuchNumerical() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP26117142138");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByVoucherCode("ac8d3be4-dbac-11ee-a506-0242ac120002",orders.get(1),
                    PaymentMethod.VOUCHER_CODE.getValue(), paymentDataVoucher);
        });
    }
}