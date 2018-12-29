package org.zzo.AppController;


import java.util.HashMap;
import java.util.List;
import java.util.Locale.LanguageRange;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zzo.AppEntity.product.ProductCategory;


@RestController
public class WebServiceCtrl {



	

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
	
	
}
