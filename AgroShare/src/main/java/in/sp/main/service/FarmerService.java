package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.Farmer;

public interface FarmerService {

	int registerFarmer(Farmer farmer);
    Farmer loginFarmer(String email, String password);
    List<Farmer> getAllFarmers();
    
    Farmer findById(int id);
    
}
