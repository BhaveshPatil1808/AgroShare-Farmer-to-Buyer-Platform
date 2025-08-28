package in.sp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Buyer;
import in.sp.main.repositories.BuyerRepository;


@Service
public class BuyerServiceImpl implements BuyerService{

	
	@Autowired
	private BuyerRepository repository;
	
	@Override
	public int registerBuyer(Buyer buyer) {
		try {
			this.repository.save(buyer);
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public Buyer loginBuyer(String email, String password) {
		Buyer b = this.repository.findByEmailAndPassword(email, password);
		if(b!=null) return b;
		return null;
	}

	@Override
	public Buyer findById(int id) {
		
		return this.repository.findById(id).get();
	}

}
