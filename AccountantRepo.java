package com.sjp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjp.Model.AccountantModel;

@Repository
public interface AccountantRepo extends JpaRepository<AccountantModel, Integer>
{

	AccountantModel findByEmailAndPassword(String email, String password);
	

}
