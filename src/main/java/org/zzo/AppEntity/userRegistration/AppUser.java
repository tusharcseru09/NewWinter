package org.zzo.AppEntity.userRegistration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name = "APP_USER")
public class AppUser {

	@Id @Column(name="USER_ID")
	@SequenceGenerator(name="hbm_AppUserId", sequenceName="seq_AppUser_Id", initialValue=10115400, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_AppUserId")
	private Long userId;
	
	@Column(name="USER_NAME", unique = true, nullable = false)
	private String userName;
	
	@Column(name="USER_EMAIL", nullable = false)
	private String userEmail;
	
	@Column(name="USER_PASSWORD", nullable = false)
	private String userPassword;
	
	@Column(name="USER_SALT", nullable = false)
	private String userSalt;
	
	
	
}
