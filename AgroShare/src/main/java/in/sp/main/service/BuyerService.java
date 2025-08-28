package in.sp.main.service;

import in.sp.main.entities.Buyer;

public interface BuyerService {

	int registerBuyer(Buyer buyer);
    Buyer loginBuyer(String email, String password);
    
    Buyer findById(int id);
}
