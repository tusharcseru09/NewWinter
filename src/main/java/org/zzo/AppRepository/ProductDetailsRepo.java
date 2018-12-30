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
		
		Long result= -1L;
		ProductDetails productDetails = new ProductDetails();
		Session session = sessionFactory.getCurrentSession();
		productDetails = session.get(ProductDetails.class, Id);
		
		if(productDetails != null) {
			result = productDetails.getProductId();
			session.delete(productDetails);
		}
		return result != -1 ? result : -1L;
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
	@Transactional
	public void putObject(ProductDetails productDetails, Long productId) throws NotAbleToUpdate, Exception {
		
		Session session = sessionFactory.getCurrentSession();
		ProductDetails requestedProductDetails = this.getObject(productId);

		if (requestedProductDetails == null ) {
			 throw new NotAbleToUpdate("Data not found with given Id " + productId + ".");
		}
		else if(! requestedProductDetails.getProductId().equals(productDetails.getProductId())) {
			throw new NotAbleToUpdate("Object id and url id not matched.");
		}
		
		requestedProductDetails.setProductCode(productDetails.getProductCode());
		requestedProductDetails.setProductName(productDetails.getProductName());
		requestedProductDetails.setProductDesc(productDetails.getProductDesc());
		requestedProductDetails.setUnitId(productDetails.getUnitId());
		requestedProductDetails.setCategoryId(productDetails.getCategoryId());
		requestedProductDetails.setTaxAble(productDetails.getTaxAble());
		requestedProductDetails.setProductComment(productDetails.getProductComment());
	
		session.persist(requestedProductDetails);
	}
	
	@Override
	@Transactional
	public Boolean postObjectList(List<ProductDetails> productList) {
		
		Long count = 0L;		
		for (ProductDetails productDetails : productList) {
			
			if (productDetails != null) {
				this.postObject(productDetails);
				count++;
			}
		}
		if (count == productList.size())
			return true;
		else 
			return false;
	}

}
