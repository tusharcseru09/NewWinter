package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.ExceptionObject.NotAbleToUpdate;

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
	@Transactional
	public List<ProductPrice> getObjectList() {
		String query = "from ProductPrice";
		Session session = sessionFactory.getCurrentSession();
		
		List<ProductPrice> lstProductPrice = new ArrayList<ProductPrice>();
		List<?> list = session.createQuery(query).list();
		
		for(int i=0; i < list.size(); i++) {
			ProductPrice productPrice = (ProductPrice)list.get(i);
			lstProductPrice.add(productPrice);
			
		}	
		return lstProductPrice;
	}

	@Override
	public Long deleteObject(Long Id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putObject(ProductPrice productPrice, Long unitId) throws NotAbleToUpdate, Exception {
		// TODO Auto-generated method stub
		
	}


}
