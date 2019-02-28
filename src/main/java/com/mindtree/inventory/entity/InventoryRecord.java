package com.mindtree.inventory.entity;

import java.util.Date;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class InventoryRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;
	
	private String productName;
	private String vendor;
	private double mrp;
	private int batchNum;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date batchDate;
	
	private int quantity;
	
//	@Column(nullable = false, columnDefinition = "varchar default pending")
	private String status;//pending or approved
	
	public InventoryRecord() {
		super();
	}
	
	public InventoryRecord(int productId, String productName, String vendor, double mrp, int batchNum,
			Date batchDate, int quantity, String status) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.vendor = vendor;
		this.mrp = mrp;
		this.batchNum = batchNum;
		this.batchDate = batchDate;
		this.quantity = quantity;
		this.status = status;
	}
	
	public InventoryRecord(String productName, String vendor, double mrp, int batchNum, Date batchDate,
			int quantity, String status) {
		super();
		this.productName = productName;
		this.vendor = vendor;
		this.mrp = mrp;
		this.batchNum = batchNum;
		this.batchDate = batchDate;
		this.quantity = quantity;
		this.status = status;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public int getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(int batchNum) {
		this.batchNum = batchNum;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "InventoryRecord [productId=" + productId + ", productName=" + productName + ", vendor=" + vendor
				+ ", mrp=" + mrp + ", batchNum=" + batchNum + ", batchDate=" + batchDate + ", quantity=" + quantity
				+ ", status=" + status + "]";
	}
	
	
	
}
