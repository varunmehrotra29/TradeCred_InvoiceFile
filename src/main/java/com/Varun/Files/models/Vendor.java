package com.Varun.Files.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="invoice")
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IN")
	private String InvoiceNumbers;
	@Column(name="DN")
	private String DocumentNumber;
	@Column(name="TY")
	private String Type;
	@Column(name="ND")
	private String Amtinloccur;
	@Column(name="VC")
    private String VendorCode;
	@Column(name="VM")
    private String Vendorname;
	@Column(name="VT")
    private String Vendortype;
	
	@Transient
	private MultipartFile file;
    
    
   	public Vendor() {}
    



	public Vendor( String documentNumber, String type, String amtinloccur, String vendorCode,
			String vendorname, String vendortype) {
		super();

		DocumentNumber = documentNumber;
		Type = type;
		Amtinloccur = amtinloccur;
		VendorCode = vendorCode;
		Vendorname = vendorname;
		Vendortype = vendortype;
	}




	public String getInvoiceNumbers() {
		return InvoiceNumbers;
	}




	public void setInvoiceNumbers(String invoiceNumbers) {
		InvoiceNumbers = invoiceNumbers;
	}




	public String getDocumentNumber() {
		return DocumentNumber;
	}




	public void setDocumentNumber(String documentNumber) {
		DocumentNumber = documentNumber;
	}




	public String getType() {
		return Type;
	}




	public void setType(String type) {
		Type = type;
	}




	public String getAmtinloccur() {
		return Amtinloccur;
	}




	public void setAmtinloccur(String amtinloccur) {
		Amtinloccur = amtinloccur;
	}




	public String getVendorCode() {
		return VendorCode;
	}




	public void setVendorCode(String vendorCode) {
		VendorCode = vendorCode;
	}




	public String getVendorname() {
		return Vendorname;
	}




	public void setVendorname(String vendorname) {
		Vendorname = vendorname;
	}




	public String getVendortype() {
		return Vendortype;
	}




	public void setVendortype(String vendortype) {
		Vendortype = vendortype;
	}




	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}



	


	
}
