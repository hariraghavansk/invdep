package com.sl.ms.inventorymanagement;


public class PrdRequest {
	
	public PrdRequest(Product product) {
		super();
		this.product = product;
	}

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
