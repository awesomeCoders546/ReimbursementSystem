package com.sjp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjp.Model.SupervisorModel;

@Repository
public interface SupervisorRepo extends JpaRepository<SupervisorModel, Long> 
{

	boolean existsByEmail(String email);

	SupervisorModel findByEmailAndPassword(String email, String password);

	

}
