package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Repository
public class ProductCategoryRepo implements ProductCategoryDAO{

	@Autowired
	public SessionFactory sessionFactory;

	//RETRIVE ALL
	@Override
	@Transactional
	public List<ProductCategory> getObjectList() {
		String query = "from ProductCategory";
		Session session = sessionFactory.getCurrentSession();
		
		List<ProductCategory> lstProductCategory = new ArrayList<ProductCategory>();
		List<?> list = session.createQuery(query).list();
		
		for(int i=0; i < list.size(); i++) {
			ProductCategory pCategory = (ProductCategory)list.get(i);
			lstProductCategory.add(pCategory);
			System.out.println("Key : " + pCategory.getCategoryCode() + ". Desc : " + pCategory.getCategoryDesc());
		}	
		return lstProductCategory;
	}

	
	//RETRIVE SINGLE
	@Override
	@Transactional
	public ProductCategory getObject(Long Id) {
		ProductCategory productCategory = new ProductCategory();
		Session session = sessionFactory.getCurrentSession();
		productCategory = session.get(ProductCategory.class, Id);
		return productCategory;
	}

	
	//INSERT
	@Override
	@Transactional
	public Long postObject(ProductCategory productCategory) {
		Long createdId = -1L;
		Session session = sessionFactory.getCurrentSession();
		createdId = (Long) session.save(productCategory);
		return createdId;
	}

	//UPDATE
	@Override
	@Transactional
	public void putObject(ProductCategory productCategory, Long categoryId) throws NotAbleToUpdate, Exception {
		
		Session session = sessionFactory.getCurrentSession();
		ProductCategory catObj = this.getObject(categoryId);

		if (catObj == null ) {
			 throw new NotAbleToUpdate("Object Not Found With Given Category Id " + categoryId + ".");
		}
		else if(! catObj.getCategoryId().equals(productCategory.getCategoryId())) {
			throw new NotAbleToUpdate("Object Id [" + productCategory.getCategoryId() + "] Not Matched With Given Category Id [" + categoryId + "].");
		}
		
		catObj.setCategoryCode(productCategory.getCategoryCode()) ;
		catObj.setCategoryDesc(productCategory.getCategoryDesc());
		session.persist(catObj);	
	}
	
	//DELETE
	@Override
	@Transactional
	public Long deleteObject(Long Id) throws  Exception {
		
		String queryStr = "delete from ProductCategory pc where pc.categoryId=:categoryId";
		Session session = sessionFactory.getCurrentSession();
		Query query =  session.createQuery(queryStr);
		query.setParameter("categoryId", Id);
		long result = query.executeUpdate();
		System.out.println(result + " row deleted.");
		return result;
	}

	


	


}
