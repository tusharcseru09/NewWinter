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
import org.zzo.AppEntity.order.OrderStatus;
import org.zzo.AppService.OrderStatusService;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@RestController
public class OrderStatusCtrl {
	
	@Autowired
	private OrderStatusService orderStatusService;
	
	@RequestMapping(path="/status/{id}", method=RequestMethod.GET)
	public OrderStatus getStatusObj(@PathVariable("id") Long statusId) {
		return orderStatusService.getOrderStatusObject(statusId);
	}
	
	@RequestMapping(path="/status", method=RequestMethod.GET)
	public List<OrderStatus> getStatusObjList(){
		return orderStatusService.getOrderStatusObjectList();
	}
	
	@RequestMapping(path="/status/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteStatusObj(@PathVariable("id") Long statusId) {
		try {
			orderStatusService.deleteOrderStatusObject(statusId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/status", method=RequestMethod.POST)
	public ResponseEntity<Object> postStatusObj(@RequestBody @Valid OrderStatus status, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		Long createdId = orderStatusService.postOrderStatusObject(status);
		if(createdId >= 0)
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value="/status/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putStatusObj(@RequestBody @Valid OrderStatus status, @PathVariable("id") Long statusId, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			orderStatusService.putOrderStatusObject(status, statusId);
		}catch(NotAbleToUpdate e ) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	

	@RequestMapping(value="/status/dataload", method=RequestMethod.POST)
	public ResponseEntity<Object> postStatusObjList(@RequestBody @Valid List<OrderStatus> statusList, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);	
		}
		
		if (orderStatusService.postOrderStatusObjectList(statusList))
			return new ResponseEntity<> (HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.CONFLICT);
	}
}
