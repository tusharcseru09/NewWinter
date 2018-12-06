package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.AppForm.ProductDetailsForm;
import org.zzo.AppRepository.ProductCategoryRepo;
import org.zzo.AppRepository.ProductDetailsRepo;
import org.zzo.AppRepository.ProductUomRepo;


@Service
public class ProductDetailsService {
	
	@Autowired
	private ProductUomRepo productUomRepo;
	
	@Autowired
	private ProductCategoryRepo productCategoryRepo;
	
	@Autowired
	private ProductDetailsRepo productDetailsRepo ;


	
	public List<ProductDetails> getProductDetailsObjectList(){
		List<ProductDetails> lstProductDetails = new ArrayList<ProductDetails>();
		lstProductDetails = productDetailsRepo.getObjectList();
		return lstProductDetails;
	}
	
	public Long PostProductDetailsObject(ProductDetails productDetails) {
		Long createdId = productDetailsRepo.postObject(productDetails);
		return createdId;
	}
	
	/*
	public Long PostProductObject(ProductDetailsForm productDetailsForm) {
		
		ProductDetails productDetails = new ProductDetails();
		productDetails.setProductCode(productDetailsForm.getProductCode());
		productDetails.setProductName(productDetailsForm.getProductName());
		productDetails.setProductDesc(productDetailsForm.getProductDesc());
		productDetails.setStartingInventory(productDetailsForm.getStartingInventory());
		productDetails.setReOrderPoint(productDetailsForm.getReOrderPoint());
		
		ProductUoM productUom = new ProductUoM();
		if (productDetailsForm.getProductUnit() != null) {
			productUom = productUomRepo.getObject(productDetailsForm.getProductUnit());
		}
		productDetails.setProductUoM(productUom);
		
		ProductCategory productCategory = new ProductCategory();
		if(productDetailsForm.getProductCategory() != null) {
			productCategory = productCategoryRepo.getObject(productDetailsForm.getProductCategory());
		}
		productDetails.setProductCategory(productCategory);

		productDetails.setTaxAble(productDetailsForm.getTaxAble());
		productDetails.setProductComment(productDetailsForm.getProductComment());
		
		Long generatedId = productDetailsRepo.postObject(productDetails);
		return generatedId;
	}
	*/
}
