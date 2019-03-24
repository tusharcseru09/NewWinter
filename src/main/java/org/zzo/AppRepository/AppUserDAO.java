package org.zzo.AppRepository;

import java.util.List;
import org.zzo.AppEntity.userRegistration.AppUser;
import org.zzo.ExceptionObject.NotAbleToUpdate;

public interface AppUserDAO {
	
	
	public AppUser getObject(Long Id);
	
	public List<AppUser> getObjectList();
	
	public Long deleteObject(Long Id) throws  Exception;
	
	public void putObject(AppUser appUser, Long userId)  throws NotAbleToUpdate, Exception ;
	
	public Long postObject(AppUser appUser);
	
}
