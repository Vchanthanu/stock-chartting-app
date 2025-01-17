package com.cognizant.stockservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.stockservice.bean.Company;
import com.cognizant.stockservice.bean.StockExchange;
import com.cognizant.stockservice.repository.CompanyRepository;
import com.cognizant.stockservice.repository.StockExchangeRepository;

/**
 * @author 805831
 *
 */
@Service
public class ManageService {
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	StockExchangeRepository stockExchangeRepository;

	public List<Company> getCompanies() {
		return companyRepository.findAll();
	}

	public List<StockExchange> getStockExchanges() {
		return stockExchangeRepository.findAll();
	}
}
