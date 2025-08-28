package in.sp.main.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.main.repositories.BuyerRepository;
import in.sp.main.repositories.FarmerRepository;
import in.sp.main.repositories.OrderRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private FarmerRepository farmerrepo;
	
	@Autowired
	private BuyerRepository buyerrepo;
	
	@Autowired
	private OrderRepository orderRepository;

    // Show login page
    @GetMapping("/login")
    public String loginPage() {
        return "admin_login";
    }

    // Hardcoded login check
    @PostMapping("/doLogin")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          Model model) {
        if (email.equals("admin@gmail.com") && password.equals("123")) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid Email or Password!");
            return "admin_login";
        }
    }
    
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin_dashboard";
    }
    
    
    @GetMapping("/farmers")
    public String viewFarmers(Model model) {
        model.addAttribute("farmers", farmerrepo.findAll());
        return "admin_farmers";
    }

    // Delete farmer
    @GetMapping("/deleteFarmer/{id}")
    public String deleteFarmer(@PathVariable int id, Model m) {
        farmerrepo.deleteById(id);
        m.addAttribute("farmers", this.farmerrepo.findAll());
        return "admin_farmers";
    }
    
    
 // View all buyers
    @GetMapping("/buyers")
    public String viewBuyers(Model model) {
        model.addAttribute("buyers", buyerrepo.findAll());
        return "admin_buyers";  // page name
    }

    // Delete buyer
    @GetMapping("/deleteBuyer/{id}")
    public String deleteBuyer(@PathVariable int id, Model model) {
        buyerrepo.deleteById(id);
        model.addAttribute("buyers", buyerrepo.findAll());
        return "admin_buyers";  // reload page after delete
    }

    
    
 // View all orders
    @GetMapping("/orders")
    public String viewOrders(Model m) {
        m.addAttribute("orders", orderRepository.findAll());
        return "admin_orders";
    }

}
