package org.zzo.AppController;

import java.util.HashMap;
import java.util.List;
import java.util.Locale.LanguageRange;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.AppService.ProductCategoryService;
import org.zzo.AppService.ProductDetailsService;
import org.zzo.AppService.ProductPriceService;
import org.zzo.AppService.ProductUomService;
import org.zzo.ExceptionObject.NotAbleToUpdate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class WebServiceCtrl {

	
	
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
	
	
			/***	PRODUCT Details	***/
	
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
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		Long createdId = productDetailsService.postProductDetailsObject(productDetails);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	
	
	
	
			/***	PRODUCT CATEGORY	***/
	
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
	
	
	
	
	
			/***	PRODUCT UOM	***/	
	
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
	
	

	@RequestMapping(path="/categories/test")
	public ResponseEntity<Object> testHttpEntity(HttpEntity<ProductCategory> requestEntity) {
		System.out.println("*****\t Request Body and Header\t*****");
		HttpHeaders httpHeaders = requestEntity.getHeaders();
		
		System.out.println("Header: "+requestEntity.getHeaders());
		
		System.out.println("\tgetAccept:- " );
		List<MediaType> mediaTypeLst = httpHeaders.getAccept();
		for (MediaType mediaType : mediaTypeLst) {
			System.out.println(mediaType.getType() + "," + mediaType.getCharset());
		}
		
		
		System.out.println("\tGetAcceptLanguage:- " );
		List<LanguageRange> acceptLanguage = httpHeaders.getAcceptLanguage();
		for (LanguageRange languageRange : acceptLanguage) {
			System.out.println(languageRange.getRange());
		}

		
		System.out.println("Body: "+requestEntity.getBody());
		System.out.println("Obj name: "+requestEntity.getClass().getName());
		
		Map<String,String> map = new HashMap<>();
		map.put("field1", "error message 1 ");
		map.put("field2", "error message 2 ");
		map.put("field3", "error message 3 ");
		map.put("field4", "error message 4 ");
		map.put("field5", "error message 5 ");
		return new ResponseEntity<>(map,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
	
// Header: {host=[localhost:8888], connection=[keep-alive], cache-control=[no-cache], user-agent=[Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36], postman-token=[e29801fa-13f9-097b-d076-3d8404c76603], accept=[* / *], accept-encoding=[gzip, deflate, br], accept-language=[en-US,en;q=0.9]}
// Body: null
// Obj name: org.springframework.http.HttpEntity

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
