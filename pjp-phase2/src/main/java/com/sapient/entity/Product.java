package com.sapient.entity;

import lombok.Data;

@Data 
public class Product {
	private Integer id;
	private String name;
	private String description;
	private String picture;
	private String quantityPerUnit;
	private String brand;
	private String category;
	private Double unitPrice;
	private Double discount;
}
