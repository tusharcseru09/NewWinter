package org.zzo.AppRepository;

import java.util.List;
import org.zzo.AppEntity.UserDetails;

public interface UserDetailsDAO {

	public UserDetails getSingleUser(Long Id);
	
	public Long postSingleUser(UserDetails userDetails);
	
	public UserDetails deleteSingleUser(Long Id);
	
	public List<UserDetails> getUserList();
	
}
