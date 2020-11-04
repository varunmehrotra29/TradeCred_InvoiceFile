package com.Varun.Files.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Varun.Files.models.Vendor;
import com.Varun.Files.repository.SpringReadFilesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
@Transactional
public class ServiceRead implements SpringReadFileService{

	@Autowired
	private SpringReadFilesRepository springReadFilesRepository;

	@Override
	public List<Vendor> findAll() {
		
		return (List<Vendor>) springReadFilesRepository.findAll();
	}

	@Override
	public boolean saveDataFromUploadfile(MultipartFile file) {
		boolean isFlag=false;
		String extension=FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("json")) {
			isFlag=readDataFromJson(file);
		}else if(extension.equalsIgnoreCase("csv")) {
			isFlag=readDataFromCsv(file);	
		}else if(extension.equalsIgnoreCase("xls") ||extension.equalsIgnoreCase("xlsx")) {
			isFlag=readDataFromExcel(file);
		}
		return isFlag;
	}

	@SuppressWarnings("deprecation")
	private boolean readDataFromExcel(MultipartFile file) {
		Workbook workbook= getWorkBook(file);
		Sheet sheet=workbook.getSheetAt(0);
		Iterator<Row> rows=sheet.iterator();
		rows.next();
		while(rows.hasNext()) {
			Row row=rows.next();
			Vendor vendor=new Vendor();
			if(row.getCell(0).getCellType()==Cell.CELL_TYPE_STRING) {
				vendor.setInvoiceNumbers(row.getCell(0).getStringCellValue());
			}
			if(row.getCell(1).getCellType()==Cell.CELL_TYPE_STRING) {
				vendor.setDocumentNumber(row.getCell(1).getStringCellValue());
			}
			if(row.getCell(2).getCellType()==Cell.CELL_TYPE_STRING) {
				vendor.setType(row.getCell(2).getStringCellValue());
			}			
			if(row.getCell(3).getCellType()==Cell.CELL_TYPE_STRING) {
				vendor.setAmtinloccur(row.getCell(3).getStringCellValue());
			}
			if(row.getCell(4).getCellType()==Cell.CELL_TYPE_STRING) {
				vendor.setVendorCode(row.getCell(4).getStringCellValue());
			}
			if(row.getCell(5).getCellType()==Cell.CELL_TYPE_STRING) {
				vendor.setVendorname(row.getCell(5).getStringCellValue());
			}
			if(row.getCell(6).getCellType()==Cell.CELL_TYPE_STRING) {
				vendor.setVendortype(row.getCell(6).getStringCellValue());
			}
			springReadFilesRepository.save(vendor);
			}
		
		return true;
	}

	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook=null;
		String extension=FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			if(extension.equalsIgnoreCase("xlsx")) {
				workbook=new XSSFWorkbook(file.getInputStream());
				
			}else if(extension.equalsIgnoreCase("xls")) {
				workbook =new HSSFWorkbook(file.getInputStream());
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return workbook;
	}

	private boolean readDataFromCsv(MultipartFile file) {
		try {
			InputStreamReader reader=new InputStreamReader(file.getInputStream());
			CSVReader csvReader=new CSVReaderBuilder(reader).withSkipLines(1).build();
			List<String[]> rows=csvReader.readAll();
			for(String[] row:rows) {
				springReadFilesRepository.save(new Vendor(row[0],row[1],row[2],row[3],row[4],row[5]));
			}
			return true;
			
		}catch (Exception e) {
			return false;
		}
		
	}

	private boolean readDataFromJson(MultipartFile file) {
		try {
			InputStream inputStream= file.getInputStream();
			ObjectMapper mapper= new ObjectMapper();
			List<Vendor> vendors=Arrays.asList(mapper.readValue(inputStream, Vendor[].class));
			if(vendors!=null && vendors.size()>0) {
				for(Vendor vendor: vendors) {
					vendor.setType(FilenameUtils.getExtension(file.getOriginalFilename()));
					springReadFilesRepository.save(vendor);
				}
			}
			
		}catch (Exception e) {
			return false;
		}
		
		return false;	
}
}
