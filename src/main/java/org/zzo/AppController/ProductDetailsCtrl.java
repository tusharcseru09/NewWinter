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
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.AppService.ProductDetailsService;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@RestController
public class ProductDetailsCtrl {

	@Autowired
	private ProductDetailsService productDetailsService;
	
	@RequestMapping(path="/products/{id}", method=RequestMethod.GET)
	public ProductDetails getProductDetailsObj(@PathVariable("id") Long productId) {
		return productDetailsService.getProductDetailsObject(productId);
	}
	
	@RequestMapping(path="/products", method=RequestMethod.GET)
	public List<ProductDetails> getProductDetailsList(){
		return productDetailsService.getProductDetailsObjectList();
	}
	
	@RequestMapping(path="/products/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductDetailsObj(@PathVariable("id") Long productId) {
		try {
			productDetailsService.deleteProductDetailsObject(productId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public ResponseEntity<Object> postProductDetailsObj(@RequestBody @Valid ProductDetails productDetails, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		Long createdId = productDetailsService.postProductDetailsObject(productDetails);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putProductDetailsObj(@RequestBody @Valid ProductDetails productDetails, @PathVariable("id") Long productId, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			productDetailsService.putProductDetailsObject(productDetails, productId);
		}catch(NotAbleToUpdate e ) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	

}
