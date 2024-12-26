package com.sathya3.SpringBootMVC.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sathya3.SpringBootMVC.entity.ProductEntity;
import com.sathya3.SpringBootMVC.model.ProductModel;
import com.sathya3.SpringBootMVC.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping("/productform")
	public String getProductForm(Model model)
	{
		ProductModel productModel=new ProductModel();
	    productModel.setMadeIn("INDIA");
	    productModel.setQuantity(1);
	    productModel.setDiscountRate(10);
	    model.addAttribute("productModel",productModel);
	
		return "add-product";
	}
	/*@PostMapping("/SaveProduct")
	public String SaveProduct(ProductModel productModel)
	{
		System.out.println(productModel);
		productService.saveProductDetails(productModel);
		return "success";
	}*/
	
	@PostMapping("/SaveProduct")
	public String SaveProduct(@Valid ProductModel productModel, BindingResult bindingResult, Model model) {
	    HashMap<String, String> validationErrors = new HashMap<>();
	    if (bindingResult.hasErrors()) {
	        for (FieldError fieldError : bindingResult.getFieldErrors()) {
	            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
	        }
	        model.addAttribute("validationErrors", validationErrors);
	        return "add-product";
	    }
	    productService.saveProductDetails(productModel);
	    return "redirect:/getallproducts";
	}

	@GetMapping("/getallproducts")
	public String getAllProducts(Model model) {
		List<ProductEntity> products=productService.getAllProducts();
		model.addAttribute("products",products);
		return "product-list";
	}/*
	@GetMapping("/getallproducts")
	public String getAllProducts(Model model) {
	    List<ProductEntity> productentity = productService.getAllProducts();
	    model.addAttribute("products", productentity);  // "products" should match the template
	    return "product-list";
	}*/

	@GetMapping("/getsearchform")
	public String getSearchForm() {
		return "search-product";
	}
	@PostMapping("/searchbyid")
	public String SearchById(@RequestParam Long id,Model model) {
		ProductEntity product=productService.SearchById(id);
		model.addAttribute("product",product);
		
		return "search-product";
	}
	@GetMapping("/delete/{id}")
	public String deleteProductById(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "redirect:/getallproducts";
	}
	
	

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable ("id")Long id,Model model) {

		ProductModel product=productService.showEditForm(id);
		model.addAttribute("product",product);
		model.addAttribute("id",id);
	    return "edit-product";
	}
	 
	@PostMapping("/editbyid/{id}")
	public String editById(@PathVariable("id") Long id, ProductModel productModel ) {
		productService.editById(id, productModel);	
		return "redirect:/getallproducts";
	}
	
	
	
	
 }
	

	

	



