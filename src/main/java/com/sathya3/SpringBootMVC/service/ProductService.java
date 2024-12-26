package com.sathya3.SpringBootMVC.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya3.SpringBootMVC.entity.ProductEntity;
import com.sathya3.SpringBootMVC.model.ProductModel;
import com.sathya3.SpringBootMVC.repository.ProductRepository;


@Service
public class ProductService {
	@Autowired
	
      ProductRepository productRepository;
	
	public List<ProductEntity> getAllProducts() {
		List<ProductEntity> products=productRepository.findAll();
		return products;
	}
	public void saveProductDetails(ProductModel productmodel)
	  {
		  double stockValue;
		  stockValue=productmodel.getPrice()*productmodel.getQuantity();
		  
		  double discountPrice;
		  discountPrice=productmodel.getPrice()*productmodel.getDiscountRate()/100;
		  
		  double offerPrice;
		  offerPrice=productmodel.getPrice()-discountPrice;
		 
		  
		  double taxPrice;
		  double taxRate=18;
		  taxPrice=offerPrice*taxRate/100;
	      
		  double finalPrice;
		  finalPrice=offerPrice +(offerPrice * (taxRate/100));
		  
		  ProductEntity productentity=new ProductEntity();
		  productentity.setName(productmodel.getName());
		  productentity.setBrand(productmodel.getBrand());
		  productentity.setMadeIn(productmodel.getMadeIn());
		  productentity.setPrice(productmodel.getPrice());
		  productentity.setQuantity(productmodel.getQuantity());
		  productentity.setDiscountRate(productmodel.getDiscountRate());
		  
		  productentity.setStockValue(stockValue);
		  productentity.setStockValue(taxPrice);
	      productentity.setDiscountPrice(discountPrice);
	      productentity.setOfferPrice(offerPrice);
	      
	      productentity.setFinalPrice(finalPrice);
		  
	      productRepository.save(productentity);
	  }
	      public ProductEntity SearchById(Long id) {
  Optional<ProductEntity> OptionalData=productRepository.findById(id);
  if(OptionalData.isPresent())
  {
	  ProductEntity product=OptionalData.get();
	  return product;
  }
  else {
return null;
  }
	     
	      }
	      public void deleteProductById(Long id) {
	    	  productRepository.deleteById(id);
	      }
	      
	      
		public ProductModel showEditForm(Long id) {
			
			Optional<ProductEntity> optionalData= productRepository.findById(id);
			ProductEntity productEntity =optionalData.get();
			ProductModel productModel= new ProductModel();
			productModel.setName(productEntity.getName());
			productModel.setBrand(productEntity.getBrand());
			productModel.setMadeIn(productEntity.getMadeIn());
			productModel.setPrice(productEntity.getPrice());
			productModel.setQuantity(productEntity.getQuantity());
			productModel.setDiscountRate(productEntity.getDiscountRate());
		
			
			return productModel;
		}
		
		public void editById(Long id, ProductModel productModel) {
           
			Optional<ProductEntity> OptionalData=productRepository.findById(id);
			  if(OptionalData.isPresent())
			  {
				  ProductEntity productEntity=OptionalData.get();
				  
				  double stockValue;
				  stockValue=productModel.getPrice()*productModel.getQuantity();
				  
				  double discountPrice;
				  discountPrice=productModel.getPrice()*productModel.getDiscountRate()/100;
				  
				  double offerPrice;
				  offerPrice=productModel.getPrice()-discountPrice;
				 
				  
				  double taxPrice;
				  double taxRate=18;
				  taxPrice=offerPrice*taxRate/100;
			      
				  double finalPrice;
				  finalPrice=offerPrice +(offerPrice * (taxRate/100));
				  
				  
				  productEntity.setName(productModel.getName());
				  productEntity.setBrand(productModel.getBrand());
				  productEntity.setMadeIn(productModel.getMadeIn());
				  productEntity.setPrice(productModel.getPrice());
				  productEntity.setQuantity(productModel.getQuantity());
				  productEntity.setDiscountRate(productModel.getDiscountRate());
				  
				  productEntity.setStockValue(stockValue);
				  productEntity.setStockValue(taxPrice);
			      productEntity.setDiscountPrice(discountPrice);
			      productEntity.setOfferPrice(offerPrice);
			      productEntity.setFinalPrice(finalPrice);

			      productRepository.save(productEntity);
				  
				  
				  
			  }
			
			
		}
	    
		
			
	}

