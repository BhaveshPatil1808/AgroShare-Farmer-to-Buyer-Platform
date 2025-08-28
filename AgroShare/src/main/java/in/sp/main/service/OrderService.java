package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.Buyer;
import in.sp.main.entities.Farmer;
import in.sp.main.entities.Order;

public interface OrderService {

	List<Order> findByProductFarmer(Farmer farmer);
	Order placeOrder(int buyerId, long productId);
	List<Order> getOrdersByBuyer(Buyer buyer);
    List<Order> getOrdersByBuyer(int buyerId);
    
    List<Order> getOrdersByFarmer(Farmer farmer);
    
    
    public void updateOrderStatus(Long orderId, String status);
}
