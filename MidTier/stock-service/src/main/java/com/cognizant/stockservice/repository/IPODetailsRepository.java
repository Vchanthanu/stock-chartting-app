package com.cognizant.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.stockservice.bean.IPODetails;



/**
 * @author 805831
 *
 */
@Repository
public interface IPODetailsRepository extends JpaRepository<IPODetails, Long> {

}
