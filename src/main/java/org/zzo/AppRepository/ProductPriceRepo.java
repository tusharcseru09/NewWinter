package org.zzo.AppRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductPrice;


@Repository
public class ProductPriceRepo implements ProductPriceDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductPrice getObject(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Long postObject(ProductPrice productPrice) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(productPrice);
		return generatedId;
	}

	@Override
	public ProductPrice deleteObject(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductPrice> getObjectList() {
		// TODO Auto-generated method stub
		return null;
	}

}
