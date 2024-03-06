package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Bambang Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP2611RZK2138");
        Payment payment1 = new Payment("3fdfd2e0-db98-11ee-a506-0242ac120002", "VOUCHER_CODE", orders.get(0), paymentData, PaymentStatus.PENDING.getValue());
        payments.add(payment1);

        paymentData = new HashMap<>();
        paymentData.put("address", "Akses UI");
        paymentData.put("deliveryFee", "9000");
        Payment payment2 = new Payment("645a22f6-db98-11ee-a506-0242ac120002", "CASH_ON_DELIVERY", orders.get(0), paymentData, PaymentStatus.PENDING.getValue());
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment1 = payments.get(0);
        doReturn(payment1).when(paymentRepository).save(any(Payment.class));
        payment1 = paymentService.addPayment(payment1.getOrder(), "VOUCHER", payment1.getPaymentData());

        Payment payment2 = payments.get(1);
        doReturn(payment2).when(paymentRepository).save(any(Payment.class));
        payment2 = paymentService.addPayment(payment2.getOrder(), "BANK", payment2.getPaymentData());

        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment findResult = paymentService.getPayment(payment1.getId());

        assertEquals(payment1.getId(),findResult.getId() );
        assertEquals(payment1.getMethod(), findResult.getMethod() );
        assertEquals(payment1.getStatus(), findResult.getStatus() );

        doReturn(payment2).when(paymentRepository).findById(payment2.getId());
        findResult = paymentService.getPayment(payment2.getId());

        assertEquals(payment2.getId(),findResult.getId() );
        assertEquals(payment2.getMethod(), findResult.getMethod() );
        assertEquals(payment2.getStatus(), findResult.getStatus() );
    }

    @Test
    void testSetStatusSuccessful() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode","ESHOP697A71B273C");
        Payment payment1 = new Payment("05b6687d-763a-4f29-9e85-144f31337282", "VOUCHER", orders.get(0), paymentData);

        assertEquals(PaymentStatus.PENDING.getValue(),payment1.getStatus());
        paymentService.setStatus(payment1, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(),payment1.getStatus());
        paymentService.setStatus(payment1, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(),payment1.getStatus());
    }

    @Test
    void testUpdateOrderStatusWhenPaymentSuccess() {
        Order order = new Order("d84e86bd-a0b3-4c64-b5f6-3c492c1468d3", products, 1708560000L, "Bambang Sugeni");
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment(UUID.randomUUID().toString(), "BANK", order, paymentData, PaymentStatus.PENDING.getValue());

        paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusFailed() {
        assertThrows(IllegalArgumentException.class, ()->
                paymentService.setStatus(payments.get(0), "MEOW")
        );
    }

    @Test
    void testUpdateOrderStatusWhenPaymentRejected() {
        Order order = new Order("3b4a64cc-61c1-44e9-b351-3f08759de529", products, 1708560000L, "Bambang Sugeni");
        Map<String, String> paymentData = new HashMap<>();
        Payment payment = new Payment(UUID.randomUUID().toString(), "BANK", order, paymentData, PaymentStatus.PENDING.getValue());

        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testGetPaymentIfFound() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment paymentFound = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), paymentFound.getId());
        assertEquals("VOUCHER",paymentFound.getMethod());
        assertEquals(payment.getStatus(), paymentFound.getStatus());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("invalidId");

        Payment payment = paymentService.getPayment("invalidId");
        assertNull(payment);
    }

    @Test
    void testGetAllPayment() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> resultPayments = paymentService.getAllPayment();

        assertNotNull(resultPayments);
        assertEquals(payments.size(), resultPayments.size());
        assertTrue(resultPayments.containsAll(payments));
    }
}