package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Repository
public class ProductUomRepo implements ProductUomDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	//RETRIVE ALL
	@Override
	@Transactional
	public List<ProductUoM> getObjectList() {
		Session session = sessionFactory.getCurrentSession();
		List<ProductUoM> lstProductUoM= new ArrayList<ProductUoM>();
		
		String query = "from ProductUoM";
		List<?> list = session.createQuery(query).list();
		for(int i=0; i  < list.size(); i++) {
			ProductUoM pUom = (ProductUoM)list.get(i);
			lstProductUoM.add(pUom);
			System.out.println("Key : " + pUom.getUnitKey() + ". Desc : " + pUom.getUnitDescription());
		}	
		
		return lstProductUoM;
	}
	
	//RETRIVE SINGLE
	@Override
	@Transactional
	public ProductUoM getObject(Long Id) {
		ProductUoM prodUom = new ProductUoM();
		Session session = sessionFactory.getCurrentSession();
		prodUom = session.get(ProductUoM.class, Id);
		return prodUom;
	}

	//INSERT
	@Override
	@Transactional
	public Long postObject(ProductUoM productUoM) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(productUoM);
		return generatedId;
	}

	/*
	@Override
	public ProductUoM deleteObject(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void putObject(ProductCategory productCategory, Long categoryId) throws NotAbleToUpdate, Exception {
		// TODO Auto-generated method stub
		
	}
*/
}
