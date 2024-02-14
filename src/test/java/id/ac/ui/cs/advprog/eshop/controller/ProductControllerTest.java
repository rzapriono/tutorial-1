package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductServiceImpl productService;
    @InjectMocks
    private ProductController controller;
    private List<Product> productData;

    private Product mockAddProduct(Product product){
        productData.add(product);
        return product;
    }

    private Product mockEditProduct(Product updatedProduct){
        for(Product product : productData){
            if(product.getProductId().equals(updatedProduct.getProductId())){
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product;
            }
        }
        throw new IllegalArgumentException("Product with id " + updatedProduct.getProductId() + " not found");
    }

    Product sampleProduct(){
        Product product = new Product();
        product.setProductId("db27ac37-4a86-40be-978a-e9d97fe4f089");
        product.setProductName("Doritos Nacho Cheese");
        product.setProductQuantity(100);
        return product;
    }

    @BeforeEach
    void setUp(){
        productData = new ArrayList<>();
    }

    @AfterEach
    void deleteRepo(){
        productData = null;
    }

    @Test
    public void testListProductPage() throws Exception{
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product's List")));
    }

    @Test
    public void testCreateProductPage() throws Exception{
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Create New Product")));
    }

    @Test
    public void testCreateProductPost() throws Exception{
        Product product = sampleProduct();
        when(productService.create(product)).thenReturn(mockAddProduct(product));
        mockMvc.perform(post("/product/create").flashAttr("product",product))
                .andExpect(status().is3xxRedirection());

        when(productService.findAll()).thenReturn(productData);
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product's List")))
                .andExpect(content().string(containsString(product.getProductName())));
    }

    @Test
    public void testEditProductPage() throws Exception{
        Product product = sampleProduct();

        when(productService.findProduct(product.getProductId())).thenReturn(product);
        mockMvc.perform(get("/product/edit/" + product.getProductId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Edit Product")));
    }

    @Test
    public void testEditProductPost() throws Exception{
        Product product = sampleProduct();
        when(productService.create(product)).thenReturn(mockAddProduct(product));
        mockMvc.perform(post("/product/create").flashAttr("product", product))
                .andExpect(status().is3xxRedirection());

        Product updatedProduct = sampleProduct();
        updatedProduct.setProductName("McFlurry Oreo");
        updatedProduct.setProductQuantity(25);

        when(productService.edit(updatedProduct)).thenReturn(mockEditProduct(updatedProduct));
        mockMvc.perform(post("/product/edit").flashAttr("product", updatedProduct))
                .andExpect(status().is3xxRedirection());

        when(productService.findAll()).thenReturn(productData);
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Product's List")))
                .andExpect(content().string(containsString(updatedProduct.getProductName())));

        verify(productService, times(1)).edit(any(Product.class));
    }

    @Test
    public void testDeleteProductPost() throws Exception{
        Product product = sampleProduct();
        doReturn(product).when(productService).findProduct(product.getProductId());

        when(productService.findProduct(product.getProductId())).thenReturn(product);

        mockMvc.perform(get("/product/delete/" + product.getProductId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("../list"));

        verify(productService, times(1)).delete(product.getProductId());
    }
}