package org.zzo.AppController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppService.ProductCategoryService;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@RestController
public class ProductCategoryCtrl {
	
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(path="/categories", method=RequestMethod.GET)
	public List<ProductCategory> getProductCategoryList(){
		return productCategoryService.getProductCategoryObjectList();
	}
	
	@RequestMapping(path="/categories/{id}", method=RequestMethod.GET)
	public ProductCategory getProductCategoryObj(@PathVariable("id") Long categoryId) {
		return productCategoryService.getProductCategoryObject(categoryId);
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public ResponseEntity<Object> postProductCategoryObj(@RequestBody @Valid ProductCategory productCategory, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		Long createdId = productCategoryService.postProductCategoryObject(productCategory);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putProductCategoryObj(@RequestBody @Valid ProductCategory productCategory, @PathVariable("id") Long categoryId, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			productCategoryService.putProductCategoryObject(productCategory, categoryId);
		}catch(NotAbleToUpdate e ) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path="/categories/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductCategoryObj(@PathVariable("id") Long categoryId) {
		try {
			 productCategoryService.deleteProductCategoryObject(categoryId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/category/dataload", method=RequestMethod.POST)
	public ResponseEntity<Object> postCategoryObjList(@RequestBody @Valid List<ProductCategory> categoryList, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		if (productCategoryService.postCategoryObjectList(categoryList))
			return new ResponseEntity<> (HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}

}
