package org.zzo.AppRepository;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.UserDetails;


@Repository
public class UserDetailsRepo implements UserDetailsDAO{

	@Autowired
	public SessionFactory sessionFactory;

	
	@Override
	public UserDetails getSingleUser(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Long postSingleUser(UserDetails userDetails) {
		Session session = sessionFactory.getCurrentSession();
		Long id = (Long) session.save(userDetails);
		return id;
	}

	@Override
	public UserDetails deleteSingleUser(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDetails> getUserList() {
		// TODO Auto-generated method stub
		return null;
	}

}
