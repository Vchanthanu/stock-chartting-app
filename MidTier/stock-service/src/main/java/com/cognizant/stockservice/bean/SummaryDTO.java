package com.cognizant.stockservice.bean;

import java.util.Date;

/**
 * @author 805831
 *
 */
public class SummaryDTO {

	private Company company;
	private int record;
	private String stockExchange;
	private Date startDate;
	private Date endDate;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}
	@Override
	public String toString() {
		return "SummaryDTO [company=" + company + ", record=" + record + ", StockExchange=" + stockExchange
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
