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
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.AppService.ProductUomService;
import org.zzo.ExceptionObject.NotAbleToUpdate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class ProductUomCtrl {

	
	@Autowired
	private ProductUomService productUomServie;
	
	@RequestMapping(path="/uom", method=RequestMethod.GET)
	public List<ProductUoM> getProductUomList(){
		return productUomServie.getProductUomObjectList();
	}
	
	@RequestMapping(path="/uom/{id}", method=RequestMethod.GET)
	public ProductUoM getProductUomObj(@PathVariable("id") Long uomId) {
		return productUomServie.getProductUomObject(uomId);
	}
	
	@RequestMapping(value="/uom", method=RequestMethod.POST)
	public ResponseEntity<Object> postProductUomObj(@RequestBody @Valid ProductUoM productUoM, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromPojo = gsonBuilder.toJson(productUoM);
		System.out.println(jsonFromPojo);
		
		Long createdId = productUomServie.postProductUomObject(productUoM);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	
	@RequestMapping(path="/uom/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProductUomObj(@PathVariable("id") Long uomId) {
		try {
			productUomServie.deleteProductUomObject(uomId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/uom/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putProductUomObj(@RequestBody @Valid ProductUoM productUoM, @PathVariable("id") Long uomId, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			productUomServie.putProductUomObject(productUoM, uomId);
		}catch(NotAbleToUpdate e ) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/uom/dataload", method=RequestMethod.POST)
	public ResponseEntity<Object> postUomObjList(@RequestBody @Valid List<ProductUoM> uomList, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		if (productUomServie.postProductUomObjectList(uomList))
			return new ResponseEntity<> (HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}	

}
