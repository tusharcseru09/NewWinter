package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Repository
public class ProductDetailsRepo implements ProductDetailsDAO{

	@Autowired
	public SessionFactory sessionFactory;

	
	
	//RETRIVE SINGLE
	@Override
	@Transactional
	public ProductDetails getObject(Long Id) {
		ProductDetails productDetails = new ProductDetails();
		Session session = sessionFactory.getCurrentSession();
		productDetails = session.get(ProductDetails.class, Id);
		return productDetails;
	}
	
	
	
	//RETRIVE ALL
	@Override
	@Transactional
	public List<ProductDetails> getObjectList() {
		
		String query = "from ProductDetails";
		Session session = sessionFactory.getCurrentSession();
		List<ProductDetails> lstProductDetails = new ArrayList<ProductDetails>();
		List<?> list = session.createQuery(query).list();
		
		for(int i=0; i  < list.size(); i++) {
			ProductDetails productDetails = (ProductDetails)list.get(i);
			lstProductDetails.add(productDetails);
		}	
		
		return lstProductDetails;
	}

	
	
	
	
	@Override
	@Transactional
	public Long deleteObject(Long Id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void putObject(ProductDetails productDetails, Long unitId) throws NotAbleToUpdate, Exception {
		// TODO Auto-generated method stub
		
	}

	
	//INSERT
	@Override
	@Transactional
	public Long postObject(ProductDetails productDetails) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(productDetails);
		return generatedId;
	}


	
	
	/*
	@Override
	@Transactional
	public ProductDetails getObject(Long Id) {
		ProductDetails productDetails = new ProductDetails();
		Session session = sessionFactory.getCurrentSession();
		productDetails = session.get(ProductDetails.class, Id);
		return productDetails;
	}


	@Override
	@Transactional
	public Long postObject(ProductDetails productDetails) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(productDetails);
		return generatedId;
	}


	@Override
	public ProductDetails deleteObject(Long Id) {
		return null;
	}


	@Override
	@Transactional
	public List<ProductDetails> getObjectList() {
			
		String query = "from ProductDetails";
		Session session = sessionFactory.getCurrentSession();
		List<ProductDetails> lstProductDetails = new ArrayList<ProductDetails>();
		
		List<?> list = session.createQuery(query).list();

		for(int i=0; i  < list.size(); i++) {
			ProductDetails productDetails = (ProductDetails)list.get(i);
			lstProductDetails.add(productDetails);
		}	

		return lstProductDetails;
	}

*/
}
