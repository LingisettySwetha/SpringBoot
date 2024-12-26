package com.sathya3.SpringBootMVC.model;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class ProductModel {

		@NotBlank(message="Product name cannot be blank")
		private String name;
		@NotBlank(message = "Brand name cannot be blank")
		private String brand;
		@NotBlank(message="MadeIn cannot be blank")
		private String madeIn;
		@Positive(message="Price cannot be blank")
		private double price;
		@Min( value=1 ,message="Quanity cannot be blank")
		private int quantity;
		@DecimalMax(value="100.0", message="DiscountRate cannot be blank")
		private double discountRate;
		


		}
