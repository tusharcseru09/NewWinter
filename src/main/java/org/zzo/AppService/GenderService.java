package org.zzo.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.GenderMain;
import org.zzo.AppRepository.GenderRepo;



@Service
public class GenderService {

	@Autowired
	GenderRepo genderRepo;
	
	public void saveInstituteInfo(GenderMain instituteInfo) {
		genderRepo.save(instituteInfo);
	}
	
}
