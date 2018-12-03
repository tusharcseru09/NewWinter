package org.zzo.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppController.StaticMembers;
import org.zzo.AppEntity.UserDetails;
import org.zzo.AppForm.UserDetailsForm;
import org.zzo.AppRepository.UserDetailsRepo;

@Service
public class UserDetailsService {
	
	@Autowired
	public UserDetailsRepo userDetailsRepo;
	
	public Long saveSingleUser(UserDetailsForm userDetailsForm) {
		
		
		UserDetails userDetails = new UserDetails();
		userDetails.setUserFirstName(userDetailsForm.getUserFirstName());
		userDetails.setUserLastName(userDetailsForm.getUserLastName());
		userDetails.setUserGender(userDetailsForm.getUserGender());
		
		userDetails.setUserPhone(userDetailsForm.getUserPhone());
		userDetails.setUserEmail(userDetailsForm.getUserEmail());
		System.out.println("=====>" + userDetailsForm.getUserDob().toString());
		userDetails.setUserDob(StaticMembers.asDate(StaticMembers.getLocalDate(userDetailsForm.getUserDob().toString(), "d.MM.yyyy")));
		
		userDetails.setUserAccessType(userDetailsForm.getUserAccessType());
		userDetails.setUserPassword(userDetailsForm.getUserPassword());
		userDetails.setUserAddress(userDetailsForm.getUserAddress());
		
		Long id = userDetailsRepo.postSingleUser(userDetails);
		return id;
	}
	
	
	
	
	
	
	

}
