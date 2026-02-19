package com.perceptioncheck.project.services;

import com.perceptioncheck.project.exceptions.ResourceNotFoundException;
import com.perceptioncheck.project.exceptions.StockException;
import com.perceptioncheck.project.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("products")
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    /**
     * Returns a list of all products
     * @return a list of all products
     */
    public List<Product> getAll() {
        return products;
    }

    /**
     * Returns a Product found via its id
     * @return a product
     * @throws ResourceNotFoundException if the corresponding product cannot be found
     */
    public Product findById(Long pId) throws ResourceNotFoundException {
        return products.stream()
                .filter(product -> Objects.equals(product.getId(), pId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("The corresponding product could not be found."));
    }

    /**
     * Returns a boolean value based on whether a product's stock is sufficient
     * @return a boolean value
     */
    public boolean isAvailable(Product pProduct, int pQuantity) {
        return pProduct.getQuantity() > pQuantity;
    }

    /**
     * Adds the provided product to the list of products
     * @return the product that was inserted in the DB
     */
    public Product save(Product pProduct) {
        products.add(pProduct);
        return pProduct;
    }

    /**
     * Finds a product in the list of products and updates its stock as long as it is not inferior
     * to the provided quantity
     * @throws StockException if the product's stock is insufficient
     */
    public void delete(Product pProduct, int pQuantity) throws StockException {
        Product match = this.findById(pProduct.getId());
        if (!isAvailable(match, pQuantity)) {
            throw new StockException("Insufficient quantity on product " + pProduct.getName());
        }
        match.setQuantity(match.getQuantity() - pQuantity);
    }
}
