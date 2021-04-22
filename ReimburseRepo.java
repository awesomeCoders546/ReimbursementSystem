package com.sjp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sjp.Model.ReimbursementModel;

@Repository
public interface ReimburseRepo extends JpaRepository<ReimbursementModel, Long>
{

}
