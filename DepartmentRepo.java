package com.sjp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjp.Model.DepartmentModel;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentModel, Long>
{

}
