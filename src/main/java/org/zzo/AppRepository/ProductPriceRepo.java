package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Repository
public class ProductPriceRepo implements ProductPriceDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public ProductPrice getObject(Long Id) {
		ProductPrice productPrice = new ProductPrice();
		Session session = sessionFactory.getCurrentSession();
		productPrice = session.get(ProductPrice.class, Id);
		return productPrice;
	}

	@Override
	@Transactional
	public List<ProductPrice> getObjectList() {
		
		String query = "from ProductPrice";
		Session session = sessionFactory.getCurrentSession();
		List<ProductPrice> lstProductPrice = new ArrayList<ProductPrice>();
		
		List<?> objList = session.createQuery(query).list();
		for(int i=0; i<objList.size(); i++) {
			ProductPrice productPrice = (ProductPrice)objList.get(i);
			lstProductPrice.add(productPrice);
		}
		return lstProductPrice;
	}

	@Override
	@Transactional
	public Long deleteObject(Long Id) throws Exception {
		Long result= -1L;
		ProductPrice productPrice = new ProductPrice();
		Session session = sessionFactory.getCurrentSession();
		productPrice = session.get(ProductPrice.class, Id);
		
		if(productPrice != null) {
			result = productPrice.getPriceId();
			session.delete(productPrice);
		}
		return result != -1 ? result : -1L;
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
	@Transactional
	public void putObject(ProductPrice productPrice, Long priceId) throws NotAbleToUpdate, Exception {
		Session session = sessionFactory.getCurrentSession();
		ProductPrice requestedProductPrice = this.getObject(priceId);

		if (requestedProductPrice == null ) {
			 throw new NotAbleToUpdate("Data not found with given Id " + priceId + ".");
		}
		else if(! requestedProductPrice.getPriceId().equals(productPrice.getPriceId())) {
			throw new NotAbleToUpdate("Object id and url id not matched.");
		}
		
		requestedProductPrice.setProductId(productPrice.getProductId());
		requestedProductPrice.setPurchasePrice(productPrice.getPurchasePrice());
		requestedProductPrice.setSalePrice(productPrice.getSalePrice());
		requestedProductPrice.setActivationDate(productPrice.getActivationDate());
		requestedProductPrice.setComment(productPrice.getComment());
		
		session.persist(requestedProductPrice);

	}
}
