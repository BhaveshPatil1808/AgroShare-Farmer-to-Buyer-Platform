package in.sp.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.entities.Farmer;
import in.sp.main.entities.Order;
import in.sp.main.entities.Product;
import in.sp.main.gettersetter.GetterSetter;
import in.sp.main.service.FarmerServiceImpl;
import in.sp.main.service.OrderService;
import in.sp.main.service.ProductService;

@Controller
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private FarmerServiceImpl farmerservice;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/register")
	public String openRegister() {
		return "farmer_register";
	}
	
	
	@PostMapping("/register")
	public String registerFarmer(@ModelAttribute Farmer farmer) {
		farmerservice.registerFarmer(farmer);
		return "farmer_login";
	}
	
	@GetMapping("/login")
	public String openLogin() {
		return "farmer_login";
	}
	
	@PostMapping("/login")
	public String farmerLogin(@RequestParam String email, @RequestParam String password, Model m) {
		
		
		Farmer f = this.farmerservice.loginFarmer(email, password);
		if(f!=null) {
			GetterSetter.setFarmer(f);
			m.addAttribute("farmer", f);
			return "farmer_dashboard";
		}
		m.addAttribute("Invalid details", "msg");
		return "";
	}
	
	@GetMapping("/myOrders")
	public String viewMyOrders(Model m) {
		Farmer farmer = farmerservice.findById(GetterSetter.getFarmer().getId());

	    if (farmer == null) {
	        m.addAttribute("error", "Farmer not found!");
	        return "error_page";
	    }

	    List<Order> orders = orderService.getOrdersByFarmer(farmer);
	    m.addAttribute("orders", orders);
	    return "my_orders";
	}
	
	
	@GetMapping("/logout")
	public String logout() {
		return "farmer_login";
	}
	
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable long id,Model m) {
		this.productService.deleteProduct(id);
		Farmer farmer = GetterSetter.getFarmer();

        List<Product> products = productService.getProductsByFarmer(farmer);
        m.addAttribute("products", products);

        return "my_products"; 
	}
	
	
	@GetMapping("/editProduct/{id}")
	public String editProduct(@PathVariable long id,Model m) {
		Product product = productService.getProductById(id);
	    m.addAttribute("product", product);
	    return "edit_product";

    
	}
	
	
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute Product product, Model m) {
	    Farmer f = GetterSetter.getFarmer();
	    product.setFarmer(f); // keep farmer associated

	    productService.addProduct(product); // save updates (same method as add works for update)

	    // reload products for farmer
	    List<Product> products = productService.getProductsByFarmer(f);
	    m.addAttribute("products", products);

	    return "my_products";  // go back to product list
	}
	
	
	
	@PostMapping("/updateOrderStatus")
    public String updateOrderStatus(@RequestParam Long orderId,
                                    @RequestParam String status,
                                    Model m) {
        orderService.updateOrderStatus(orderId, status);
        Farmer farmer = GetterSetter.getFarmer();
        List<Order> orders = orderService.getOrdersByFarmer(farmer);
        m.addAttribute("orders", orders);

        return "my_orders";
    }
	
}
