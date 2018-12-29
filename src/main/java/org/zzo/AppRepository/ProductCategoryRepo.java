package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Repository
public class ProductCategoryRepo implements ProductCategoryDAO{

	@Autowired
	public SessionFactory sessionFactory;

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

	@Override
	@Transactional
	public ProductCategory getObject(Long Id) {
		ProductCategory productCategory = new ProductCategory();
		Session session = sessionFactory.getCurrentSession();
		productCategory = session.get(ProductCategory.class, Id);
		return productCategory;
	}

	@Override
	@Transactional
	public Long postObject(ProductCategory productCategory) {
		Long createdId = -1L;
		Session session = sessionFactory.getCurrentSession();
		createdId = (Long) session.save(productCategory);
		return createdId;
	}

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
	
	@Override
	@Transactional
	public Long deleteObject(Long Id) throws  Exception {
		
		Long result= -1L;
		ProductCategory productCategory = new ProductCategory();
		Session session = sessionFactory.getCurrentSession();
		productCategory = session.get(ProductCategory.class, Id);
		
		if(productCategory != null) {
			result = productCategory.getCategoryId();
			session.delete(productCategory);
		}
		return result != -1 ? result : -1L;
	}


	@Override
	@Transactional
	public Boolean postObjectList(List<ProductCategory> categoryList) {
		Long count = 0L;		
		for (ProductCategory productCategory : categoryList) {
			
			if (productCategory != null) {
				this.postObject(productCategory);
				count++;
			}
		}
		if (count == categoryList.size())
			return true;
		else 
			return false;
	}

}
