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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zzo.AppEntity.userRegistration.AppUser;
import org.zzo.AppService.AppUserService;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@RestController
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.GET)
	public AppUser getAppUserObj(@PathVariable("id") Long userId) {
		return appUserService.getAppUserObject(userId);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(path="/users", method=RequestMethod.GET)
	public List<AppUser> getAppUserObjList() {
		return appUserService.getAppUserObjectList();
	}
	
	@RequestMapping(path="/users/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAppUserObj(@PathVariable("id") Long userId) {
		try {
			appUserService.deleteAppUserObject(userId);
		}catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public ResponseEntity<Object> postAppUserObj(@RequestBody @Valid AppUser appUser, BindingResult bindingResult) {
		Map<String, String> errorMap;
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error: bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap,HttpStatus.NOT_ACCEPTABLE);
		}
		
		Long createdId = appUserService.postAppUserObject(appUser);
		if (createdId >=0 )
			return new ResponseEntity<> (createdId,HttpStatus.OK);
		else
			return new ResponseEntity<> (createdId,HttpStatus.CONFLICT);
	}
	

	@RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> putAppUserObj(@RequestBody @Valid AppUser appUser, @PathVariable("id") Long userId, BindingResult bindingResult) {
		
		Map<String , String> errorMap;
		
		if(bindingResult.hasErrors()) {
			errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errorMap, HttpStatus.NOT_ACCEPTABLE);
		}
		
		try {
			appUserService.putAppUserObject(appUser, userId);
		}catch(NotAbleToUpdate e ) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}	
}
