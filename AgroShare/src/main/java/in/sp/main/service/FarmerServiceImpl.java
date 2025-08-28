package in.sp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Farmer;
import in.sp.main.entities.Order;
import in.sp.main.repositories.FarmerRepository;
import in.sp.main.repositories.OrderRepository;


@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepository repository;
	
	@Autowired
    private OrderRepository orderRepo;
	
	@Override
	public int registerFarmer(Farmer farmer) {
		try{
			this.repository.save(farmer);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public Farmer loginFarmer(String email, String password) {
		Farmer dbFarmer = this.repository.findByEmailAndPassword(email, password);
		
		if(password.equals(dbFarmer.getPassword())) {
			return dbFarmer;
		}
		return null;
	}

	@Override
	public List<Farmer> getAllFarmers() {
		
		return this.repository.findAll();
	}
	
	

	@Override
	public Farmer findById(int id) {
		return this.repository.findById(id).get();
	}

	

}
