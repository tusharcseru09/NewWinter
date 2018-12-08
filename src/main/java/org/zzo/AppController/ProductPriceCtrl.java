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
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.AppService.ProductPriceService;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@RestController
public class ProductPriceCtrl {

	@Autowired
	private ProductPriceService productPriceService;
	
	@RequestMapping(path="/price/{id}", method=RequestMethod.GET)
	public ProductPrice getProductPriceObj(@PathVariable("id") Long priceId) {
		return productPriceService.getProductPriceObject(priceId);
	}
	
	@RequestMapping(path="/price", method=RequestMethod.GET)
	public List<ProductPrice> getProductPriceList(){
		return productPriceService.getProductPriceObjectList();
	}
	
	@RequestMapping(path="/price/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductPriceObj(@PathVariable("id") Long priceId) {
		try {
			productPriceService.deleteProductPriceObject(priceId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/price", method=RequestMethod.POST)
	public ResponseEntity<Object> postProductPriceObj(@RequestBody @Valid ProductPrice productPrice, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		Long createdId = productPriceService.postProductPriceObject(productPrice);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="/price/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putProductPriceObj(@RequestBody @Valid ProductPrice productPrice, @PathVariable("id") Long priceId, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			productPriceService.putProductPriceObject(productPrice, priceId);
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
