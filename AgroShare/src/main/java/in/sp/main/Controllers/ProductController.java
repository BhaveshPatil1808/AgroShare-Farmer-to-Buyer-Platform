package in.sp.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.sp.main.entities.Farmer;
import in.sp.main.entities.Product;
import in.sp.main.gettersetter.GetterSetter;
import in.sp.main.service.ProductService;

@Controller
@RequestMapping("/farmer")
public class ProductController {

	@Autowired
	private ProductService service;
	
	
	@GetMapping("/addProduct")
	public String openAdd(Model m) {
		m.addAttribute("product", new Product());
		return "add_product";
	}
	
	
	@PostMapping("/addProduct")
	public String saveProduct(@ModelAttribute Product product, Model m) {
	    Farmer f = GetterSetter.getFarmer();
	    product.setFarmer(f);

	    // Save product
	    service.addProduct(product);

	    // Fetch updated list
	    List<Product> products = service.getProductsByFarmer(f);
	    m.addAttribute("products", products);

	    // Return my_products page
	    return "my_products";  // or "my_products" if your page is named that
	}
	
	
	
	
	
	@GetMapping("/myProducts")
    public String viewFarmerProducts(Model m) {
        Farmer farmer = GetterSetter.getFarmer();

        List<Product> products = service.getProductsByFarmer(farmer);
        m.addAttribute("products", products);

        return "my_products";   
    }
	
	
	@GetMapping("/products")
    public String viewAllProducts(Model m) {
        List<Product> products = service.getAllProducts();
        m.addAttribute("products", products);
        return "all_products";   
    }
}
