package com.cognizant.stockservice.service;

import java.io.IOException;
import java.util.Date;
import java.sql.Time;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cognizant.stockservice.bean.Company;
import com.cognizant.stockservice.bean.StockExchange;
import com.cognizant.stockservice.bean.StockPrice;
import com.cognizant.stockservice.bean.SummaryDTO;
import com.cognizant.stockservice.exception.InvalidDetailsException;
import com.cognizant.stockservice.repository.CompanyRepository;
import com.cognizant.stockservice.repository.StockExchangeRepository;
import com.cognizant.stockservice.repository.StockPriceRepository;

/**
 * @author 805831
 *
 */
@Service
public class ImportService {

	private long companyCode;
	Date startDate ;
	Date endDate;
	int record ;
	String exchange;

	@Autowired
	StockPriceRepository stockPriceRepository;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	StockExchangeRepository stockExchangeRepository;

	public void mapReapExcelDatatoDB(MultipartFile reapExcelDataFile) throws IOException, InvalidDetailsException {
		DataFormatter formatter = new DataFormatter();
		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		System.out.println(workbook);

		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			StockPrice stockPrice = new StockPrice();
			XSSFRow row = worksheet.getRow(i);
			StockExchange stockExchange = stockExchangeRepository
					.findByStockExchange(formatter.formatCellValue(row.getCell(1)).trim());
			exchange = formatter.formatCellValue(row.getCell(1)).trim();
			if (stockExchange != null) {
				stockPrice.setStockExchange(stockExchange.getStockExchange());
				Company company = companyRepository
						.findByStockCode(Long.parseLong((formatter.formatCellValue(row.getCell(0))).trim()));
				if (company != null) {
					companyCode = company.getStockCode();
					stockPrice.setCompanyCode(company.getStockCode());
					stockPrice.setCurrentPrice((float) row.getCell(2).getNumericCellValue());
					stockPrice.setDate(row.getCell(3).getDateCellValue());
					
					if(startDate==null) {
						startDate = row.getCell(3).getDateCellValue();
						endDate = row.getCell(3).getDateCellValue();
					}
					if(startDate.after(row.getCell(3).getDateCellValue())) {
						startDate= row.getCell(3).getDateCellValue();
					}
					if(endDate.before(row.getCell(3).getDateCellValue())){
						endDate= row.getCell(3).getDateCellValue();
					}
					
					stockPrice.setTime(Time.valueOf(formatter.formatCellValue(row.getCell(4)).trim()));
					stockPriceRepository.save(stockPrice);
					record++;
				} else {
					throw new InvalidDetailsException("Invalid Company Code");
				}

			} else {
				throw new InvalidDetailsException("Invalid Stock Exchange");
			}
		}
		
	}

	public SummaryDTO getCompany() {
		SummaryDTO summary = new SummaryDTO();
		summary.setCompany(companyRepository.findByStockCode(companyCode));
		summary.setStockExchange(exchange);
		summary.setEndDate(endDate);
		summary.setStartDate(startDate);
		summary.setRecord(record);
		record=0;
		startDate=null;
		endDate=null;
		return summary;
		
	}
}
