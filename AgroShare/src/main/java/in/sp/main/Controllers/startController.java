package in.sp.main.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class startController {

	
	@GetMapping("/")
	public String open() {
		return "home";
	}
}
