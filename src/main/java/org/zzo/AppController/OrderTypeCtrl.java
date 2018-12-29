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
import org.zzo.AppEntity.order.OrderType;
import org.zzo.AppService.OrderTypeService;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@RestController
public class OrderTypeCtrl {
	@Autowired
	private OrderTypeService orderTypeService;
	
	@RequestMapping(path="/type/{id}", method=RequestMethod.GET)
	public OrderType getOrderTypeObj(@PathVariable("id") Long orderTypeId) {
		return orderTypeService.getOrderTypeObject(orderTypeId);
	}
	
	@RequestMapping(path="/type", method=RequestMethod.GET)
	public List<OrderType> getOrderTypeObjList(){
		return orderTypeService.getOrderTypeObjectList();
	}
	
	@RequestMapping(path="/type/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteOrderTypeObj(@PathVariable("id") Long typeId) {
		try {
			orderTypeService.deleteOrderTypeObject(typeId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/type", method=RequestMethod.POST)
	public ResponseEntity<Object> postOrderTypeObj(@RequestBody @Valid OrderType orderType, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		Long createdId = orderTypeService.postOrderTypeObject(orderType);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="/type/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putOrderTypeObj(@RequestBody @Valid OrderType orderType, @PathVariable("id") Long orderTypeId, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			orderTypeService.putOrderTypeObject(orderType, orderTypeId);
		}catch(NotAbleToUpdate e ) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	

	@RequestMapping(value="/type/dataload", method=RequestMethod.POST)
	public ResponseEntity<Object> postOrderTypeObjList(@RequestBody @Valid List<OrderType> orderTypeList, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		if (orderTypeService.postOrderTypeObjectList(orderTypeList))
			return new ResponseEntity<> (HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
}
