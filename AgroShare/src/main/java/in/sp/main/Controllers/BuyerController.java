package in.sp.main.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.sp.main.entities.Buyer;
import in.sp.main.entities.Order;
import in.sp.main.entities.Product;
import in.sp.main.gettersetter.GetterSetter;
import in.sp.main.service.BuyerService;
import in.sp.main.service.OrderService;
import in.sp.main.service.ProductService;

@Controller
@RequestMapping("/buyer")
public class BuyerController {
	
	@Autowired
    private BuyerService buyerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;
    
    
    
    @GetMapping("/register")
    public String openRegister(Model m) {
    	m.addAttribute("buyer", new Buyer());
    	return "buyer_register";
    	
    }
    
    
    @PostMapping("/register")
    public String register(@ModelAttribute Buyer buyer) {
    	this.buyerService.registerBuyer(buyer);
    	return "buyer_login";
    }
    
    @GetMapping("/login")
    public String openLogin(Model m) {
    	m.addAttribute("buyer", new Buyer());
    	return "buyer_login";
    }
    
    @PostMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password, Model m) {
    	Buyer loginBuyer = this.buyerService.loginBuyer(email, password);
        if (loginBuyer != null) {
            // Pass buyer to dashboard
        	GetterSetter.setBuyerId(loginBuyer.getId());
            m.addAttribute("buyer", loginBuyer);
            m.addAttribute("products", productService.getAllProducts());
            return "redirect:/buyer/dashboard?buyerId=" + loginBuyer.getId();
        } else {
            m.addAttribute("error", "Invalid email or password");
            return "buyer_login";
        }
    }
    
    
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam int buyerId, Model m) {
        Buyer buyer = this.buyerService.findById(buyerId);
        m.addAttribute("buyer", buyer);
        m.addAttribute("products", productService.getAllProducts());
        return "buyer_dashboard";
    }

    
    @GetMapping("/browseProducts")
    public String browseProducts( Model model) {
    	int buyerId = GetterSetter.getBuyerId();
        Buyer buyer = buyerService.findById(buyerId);
        List<Product> products = productService.getAllProducts();

        model.addAttribute("buyer", buyer);
        model.addAttribute("products", products);

        return "browse_products";  
    }

    @GetMapping("/placeOrder")
    public String placeOrder(@RequestParam long productId, Model model) {
    	
    	orderService.placeOrder(GetterSetter.getBuyerId(), productId);
        return "redirect:/buyer/myOrders";
        
    }
    
    
    @GetMapping("/myOrders")
    public String viewOrders(Model m) {
        int buyerId = GetterSetter.getBuyerId();
        Buyer buyer = buyerService.findById(buyerId);
        List<Order> orders = orderService.getOrdersByBuyer(buyer);

        m.addAttribute("buyer", buyer);
        m.addAttribute("orders", orders);
        return "buyer_orders"; // your JSP page to display orders
    }
    
    
    
    @GetMapping("/logout")
    public String logout() {
    	return "buyer_login";
    }
    
    

}
