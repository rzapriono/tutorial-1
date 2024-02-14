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

    public Product edit(Product editedProduct) {
        Product originalProduct = findProduct(editedProduct.getProductId());
        int indexOfProduct = productData.indexOf(originalProduct);
        productData.set(indexOfProduct, editedProduct);
        return editedProduct;
    }

    public Product findProduct(String productId) {
        Product foundProduct = new Product();
        boolean found = false;

        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                found = true;
                foundProduct = product;
            }
        }

        if (found){
            return foundProduct;
        } else {
            throw new IllegalArgumentException("Product doesn't exist");
        }
    }

    public Product delete(String productId) {
        Product deletedProduct = findProduct(productId);
        productData.remove(deletedProduct);
        return deletedProduct;
    }
}