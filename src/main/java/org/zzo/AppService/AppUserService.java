package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.userRegistration.AppUser;
import org.zzo.AppRepository.AppUserRepo;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Service
public class AppUserService {

	@Autowired
	private AppUserRepo appUserRepo;
	
	
	public AppUser getAppUserObject(Long Id){
		return appUserRepo.getObject(Id);
	}
	
	
	public List<AppUser> getAppUserObjectList(){
		List<AppUser> lstAppUser = new ArrayList<AppUser>();
		lstAppUser = appUserRepo.getObjectList();
		return lstAppUser;
	}
	
	
	public Long deleteAppUserObject(Long Id) throws  Exception {
		return appUserRepo.deleteObject(Id);
	}
	
	
	public Long postAppUserObject(AppUser appUser) {
		Long createdId = appUserRepo.postObject(appUser);
		return createdId;
	}
	
	public void putAppUserObject(AppUser appUser, Long userId) throws NotAbleToUpdate, Exception {
		appUserRepo.putObject(appUser, userId);
	}
	
	
	
	
}
