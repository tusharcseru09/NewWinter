package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.userRegistration.AppUser;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Repository
public class AppUserRepo implements AppUserDAO{

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public AppUser getObject(Long Id) {
		AppUser appUser = new AppUser();
		Session session = sessionFactory.getCurrentSession();
		appUser = session.get(AppUser.class, Id);
		return appUser;
	}

	@Override
	@Transactional
	public List<AppUser> getObjectList() {
		String query = "from AppUser";
		Session session = sessionFactory.getCurrentSession();
		List<AppUser> lstAppUser = new ArrayList<AppUser>();
		List<?> list = session.createQuery(query).list();
		
		for(int i=0; i<list.size(); i++) {
			AppUser appUser = (AppUser)list.get(i);
			lstAppUser.add(appUser);
		}
		return lstAppUser;
	}

	@Override
	@Transactional
	public Long deleteObject(Long Id) throws Exception {
		Long result= -1L;
		AppUser appUser = new AppUser();
		Session session = sessionFactory.getCurrentSession();
		appUser = session.get(AppUser.class, Id);
		
		if(appUser != null) {
			result = appUser.getUserId();
			session.delete(appUser);
		}
		return result != -1 ? result : -1L;
	}

	@Override
	@Transactional
	public void putObject(AppUser appUser, Long userId) throws NotAbleToUpdate, Exception {
		Session session = sessionFactory.getCurrentSession();
		AppUser requestedAppUser = this.getObject(userId);

		if (requestedAppUser == null ) {
			 throw new NotAbleToUpdate("Data not found with given Id " + userId + ".");
		}
		else if(! requestedAppUser.getUserId().equals(appUser.getUserId())) {
			throw new NotAbleToUpdate("Object id and url id not matched.");
		}
		
		requestedAppUser.setUserEmail(appUser.getUserEmail());
		requestedAppUser.setUserName(appUser.getUserName());
		requestedAppUser.setUserPassword(appUser.getUserPassword());
		requestedAppUser.setUserSalt(appUser.getUserSalt());

		session.persist(requestedAppUser);
		
	}

	@Override
	@Transactional
	public Long postObject(AppUser appUser) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(appUser);
		return generatedId;
	}

}
