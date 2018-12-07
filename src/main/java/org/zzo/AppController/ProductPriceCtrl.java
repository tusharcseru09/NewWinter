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
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.AppService.ProductPriceService;


@RestController
public class ProductPriceCtrl {

	@Autowired
	private ProductPriceService productPriceService;
	
	@RequestMapping(path="/price", method=RequestMethod.GET)
	public List<ProductPrice> getProductPriceList(){
		return productPriceService.getProductPriceObjectList();
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
	
	
}
