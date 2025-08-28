package in.sp.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Farmer;
import in.sp.main.entities.Product;
import in.sp.main.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public int addProduct(Product product) {
		try {
			this.repository.save(product);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateProduct(Product product) {
		try {
			this.repository.save(product);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void deleteProduct(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Product getProductById(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public List<Product> getAllProducts() {
		
		return this.repository.findAll();
	}

	@Override
	public List<Product> getProductsByFarmer(Farmer farmer) {
		List<Product> byFarmer = new ArrayList<>();
	    List<Product> allProduct = this.repository.findAll();

	    for(Product p : allProduct) {
	        if(p.getFarmer() != null && p.getFarmer().getEmail().equals(farmer.getEmail())) {
	            byFarmer.add(p);
	        }
	    }

	    return byFarmer;
	}

	@Override
	public List<Product> getProductsByType(String type) {
		List<Product> byFarmer = new ArrayList<>();
		List<Product> allProduct = this.repository.findAll();
		for(Product p:allProduct) {
			if(p.getType().equals(type)) {
				byFarmer.add(p);
			}
		}
		if(byFarmer.size()!=0) return byFarmer;
		else return null;
	}

	@Override
	public List<Product> getProductsByStatus(String status) {
		List<Product> byFarmer = new ArrayList<>();
		List<Product> allProduct = this.repository.findAll();
		for(Product p:allProduct) {
			if(p.getStatus().equals(status)) {
				byFarmer.add(p);
			}
		}
		if(byFarmer.size()!=0) return byFarmer;
		else return null;
	}

}
