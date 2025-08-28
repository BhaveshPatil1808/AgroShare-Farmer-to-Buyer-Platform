package in.sp.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Buyer;
import in.sp.main.entities.Order;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

	
	Buyer findByEmailAndPassword(String email,String password);
}
