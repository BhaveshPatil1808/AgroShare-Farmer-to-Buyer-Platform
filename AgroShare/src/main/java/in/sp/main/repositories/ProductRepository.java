package in.sp.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.entities.Farmer;
import in.sp.main.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
    List<Product> findByFarmer(Farmer farmer);

    List<Product> findByType(String type);

    List<Product> findByStatus(String status);
}
