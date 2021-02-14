package com.sl.ms.inventorymanagement;



import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	private int productid;	
	private String name;
	private int price;
	private int quantity;
	
	public Product(int productid, String name, int price, int quantity) {
		super();
		this.productid = productid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
			
	public Product() {
		
	}

	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
