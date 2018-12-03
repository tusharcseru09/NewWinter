package org.zzo.AppForm;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserDetailsForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userFirstName;
	
	private String userLastName;
	
	private Long userGender;
	
	private String userPhone;
	
	private String userEmail;
	
	private String userDob;
	
	private Long userAccessType;
	
	private String userPassword;
	
	private String userAddress;

}
