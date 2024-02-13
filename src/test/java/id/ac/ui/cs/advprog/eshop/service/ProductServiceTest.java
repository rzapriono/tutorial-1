package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setup() {
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("db27ac37-4a86-40be-978a-e9d97fe4f089");
        product.setProductName("Doritos Nacho Cheese");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productServiceImpl.create(product);
        assertEquals(product.getProductId(), createdProduct.getProductId());
    }

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(Collections.emptyIterator());
        productServiceImpl.findAll();

        List<Product> productData = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("db27ac37-4a86-40be-978a-e9d97fe4f089");
        product1.setProductName("Doritos Nacho Cheese");
        product1.setProductQuantity(100);
        productData.add(product1);

        Product product2 = new Product();
        product2.setProductId("9fe0daaf-4a69-43d7-9664-54532cf8e13e");
        product2.setProductName("McFlurry Cookies and Cream");
        product2.setProductQuantity(50);
        productData.add(product2);

        when(productRepository.findAll()).thenReturn(productData.iterator());
        List<Product> result = productServiceImpl.findAll();

        assertEquals(productData.size(), result.size());
        assertTrue(productData.contains(product1) && productData.contains(product2));
        for (int i = 0; i < productData.size(); i++) {
            assertEquals(productData.get(i), result.get(i));
        }
        verify(productRepository, times(2)).findAll();
    }

    @Test
    void testEdit() {
        Product product = new Product();
        product.setProductId("db27ac37-4a86-40be-978a-e9d97fe4f089");
        product.setProductName("Doritos Nacho Cheese");
        product.setProductQuantity(100);

        when(productRepository.edit(product)).thenReturn(product);
        Product updatedProduct = productServiceImpl.edit(product);

        assertNotNull(updatedProduct);
        assertEquals(product, updatedProduct);
        verify(productRepository, times(1)).edit(product);
    }

    @Test
    void testFindProduct() {
        Product product = new Product();
        product.setProductId("db27ac37-4a86-40be-978a-e9d97fe4f089");
        product.setProductName("Doritos Nacho Cheese");
        product.setProductQuantity(100);
        productServiceImpl.create(product);

        when(productRepository.findProduct(product.getProductId())).thenReturn(product);
        Product foundProduct = productServiceImpl.findProduct(product.getProductId());
        assertEquals(product, foundProduct);
        verify(productRepository, times(1)).findProduct(product.getProductId());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("db27ac37-4a86-40be-978a-e9d97fe4f089");
        product.setProductName("Doritos Nacho Cheese");
        product.setProductQuantity(100);

        when(productRepository.delete(product.getProductId())).thenReturn(product);
        Product deletedProduct = productServiceImpl.delete(product.getProductId());

        assertEquals(product, deletedProduct);
        verify(productRepository, times(1)).delete(product.getProductId());
    }
}
