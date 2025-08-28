package in.sp.main.service;

import java.util.List;

import java.util.List;

import in.sp.main.entities.Farmer;
import in.sp.main.entities.Product;


public interface ProductService {

	int addProduct(Product product);

    // Update product
    int updateProduct(Product product);

    // Delete product by id
    void deleteProduct(Long id);

    // Get product by id
    Product getProductById(Long id);

    // Get all products
    List<Product> getAllProducts();

    // Get products by farmer
    List<Product> getProductsByFarmer(Farmer farmer);

    // Get products by type
    List<Product> getProductsByType(String type);

    // Get products by status
    List<Product> getProductsByStatus(String status);
}
