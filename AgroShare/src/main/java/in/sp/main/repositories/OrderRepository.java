package in.sp.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Farmer;
import in.sp.main.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByBuyerId(int buyerId);
	List<Order> findByProductFarmerEmail(String email);
	
	List<Order> findByProduct_Farmer(Farmer farmer);
}
