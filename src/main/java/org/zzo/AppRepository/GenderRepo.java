package org.zzo.AppRepository;


import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.GenderMain;


@Repository
public class GenderRepo implements GenderDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void save(GenderMain genderMain) {
		Session session = sessionFactory.getCurrentSession();
		session.save(genderMain);
	}

}
