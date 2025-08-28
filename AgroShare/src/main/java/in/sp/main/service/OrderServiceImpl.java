package in.sp.main.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Buyer;
import in.sp.main.entities.Farmer;
import in.sp.main.entities.Order;
import in.sp.main.entities.Product;
import in.sp.main.repositories.BuyerRepository;
import in.sp.main.repositories.OrderRepository;
import in.sp.main.repositories.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private BuyerRepository buyerRepo;

	@Autowired
	private ProductRepository productRepo;

	public Order placeOrder(int buyerId, long productId) {
		Buyer buyer = buyerRepo.findById(buyerId).get();
		Product product = productRepo.findById(productId).orElseThrow();
		Farmer farmer = product.getFarmer(); // product already has farmer

		Order order = new Order();
		order.setBuyer(buyer);
		order.setProduct(product);
		order.setFarmer(farmer);
		order.setQuantity(1);
		order.setTotalPrice(product.getPrice());
		order.setOrderDate(LocalDate.now());
		order.setStatus("PLACED");

		return orderRepo.save(order);
	}

	@Override
	public List<Order> getOrdersByBuyer(int buyerId) {
		return orderRepo.findByBuyerId(buyerId);
	}

	@Override
	public List<Order> getOrdersByBuyer(Buyer buyer) {
		return orderRepo.findByBuyerId(buyer.getId()); // This works now
	}

	@Override
	public List<Order> getOrdersByFarmer(Farmer farmer) {
		return orderRepo.findByProduct_Farmer(farmer);
	}

	@Override
	public List<Order> findByProductFarmer(Farmer farmer) {
		return orderRepo.findByProduct_Farmer(farmer);
		// return null;
	}

	
	
	@Override
	public void updateOrderStatus(Long orderId, String status) {
	    Order order = orderRepo.findById(orderId)
	                           .orElseThrow(() -> new RuntimeException("Order not found"));
	    order.setStatus(status);
	    orderRepo.save(order);
	}
}
