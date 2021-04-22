package com.sjp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjp.Model.EmployeeModel;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long>
{

	boolean existsEmployeeModelByEmail(String email);

	EmployeeModel findByEmailAndPassword(String email, String password);

}
