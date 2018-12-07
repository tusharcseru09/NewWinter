package org.zzo.AppController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.AppService.ProductCategoryService;
import org.zzo.AppService.ProductDetailsService;
import org.zzo.AppService.ProductUomService;
import org.zzo.dataloding.JsonDataLoad;

@RestController
@RequestMapping(path="/dataload")
public class DataloadingCtrl {
	
	@Autowired
	private ProductUomService productUomServie;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private ProductDetailsService productDetailsService;
	
	@RequestMapping(path="/uom", method=RequestMethod.GET)
	public String loadUnitOfMeasure() {
		
		String ids = "Id List - loadUom : "; 
		try {
			
			List<ProductUoM> objList = JsonDataLoad.uomLoading("InfoNote/unit.json");
			for (ProductUoM obj : objList) 
				ids = ids +  productUomServie.postProductUomObject(obj).toString() + ", ";
			
		} 
		catch (FileNotFoundException fnfe) {} 
		catch (IOException ioEx) {}
		return ids;
	}
	

	@RequestMapping(path="/categories", method=RequestMethod.GET)
	public String loadProductCategories() {
		
		String ids = "Id List - loadCategory : "; 
		try {
			
			List<ProductCategory> objList = JsonDataLoad.pcLoading("InfoNote/category.json");
			for (ProductCategory obj : objList) 
				ids = ids +  productCategoryService.postProductCategoryObject(obj).toString() + ", ";
			
		} 
		catch (FileNotFoundException fnfe) {} 
		catch (IOException ioEx) {}
		return ids;
	}
	
	
	@RequestMapping(path="/products", method=RequestMethod.GET)
	public String loadProductDetails() {
		
		String ids = "Id List - loadProduct : "; 
		try {
			
			List<ProductDetails> objList = JsonDataLoad.pdLoading("InfoNote/product.json");
			for (ProductDetails obj : objList) 
				ids = ids +  productDetailsService.postProductDetailsObject(obj).toString() + ", ";
			
		} 
		catch (FileNotFoundException fnfe) {} 
		catch (IOException ioEx) {}
		return ids;
	}
	

}
