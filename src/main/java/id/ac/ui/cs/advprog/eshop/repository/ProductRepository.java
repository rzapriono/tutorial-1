package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product edit(Product originalProduct, Product editedProduct) {
        originalProduct.setProductName(editedProduct.getProductName());
        originalProduct.setProductQuantity(editedProduct.getProductQuantity());
        return editedProduct;
    }

    public Product findProduct(String productId) {
        System.out.println("param: " + productId);
        for (Product product : productData) {
            System.out.println("deleted id: " + product.getProductId());
            System.out.println(product.getProductId().equals(productId));
            if (product.getProductId().equals(productId)) {
                System.out.println("tes");
                return product;
            }
        }
        throw new IllegalArgumentException("Product doesn't exist");
    }

    public void delete(String productId) {
        Product deletedProduct = findProduct(productId);
        productData.remove(deletedProduct);
    }
}