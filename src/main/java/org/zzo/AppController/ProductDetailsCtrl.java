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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.AppService.ProductDetailsService;

@RestController
public class ProductDetailsCtrl {

	@Autowired
	private ProductDetailsService productDetailsService;
	
	
	@RequestMapping(path="/products", method=RequestMethod.GET)
	public List<ProductDetails> getProductDetailsList(){
		return productDetailsService.getProductDetailsObjectList();
	}
	
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public ResponseEntity<Object> postProductDetailsObj(@RequestBody @Valid ProductDetails productDetails, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			System.out.println("Got Product Post Error");
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		Long createdId = productDetailsService.postProductDetailsObject(productDetails);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	
}
