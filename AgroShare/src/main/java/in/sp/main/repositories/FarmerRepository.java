package in.sp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.entities.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Integer>{

	Farmer findByEmailAndPassword(String email, String password);
}
