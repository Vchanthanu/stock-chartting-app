package com.cognizant.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.stockservice.bean.Company;


/**
 * @author 805831
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	@Query(value = "SELECT * FROM company c WHERE c.co_stock_code=? ",nativeQuery = true)
	public Company findByStockCode(long stockCode);
}
