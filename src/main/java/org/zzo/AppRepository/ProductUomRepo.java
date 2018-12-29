package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Repository
public class ProductUomRepo implements ProductUomDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
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
	
	@Override
	@Transactional
	public ProductUoM getObject(Long Id) {
		ProductUoM prodUom = new ProductUoM();
		Session session = sessionFactory.getCurrentSession();
		prodUom = session.get(ProductUoM.class, Id);
		return prodUom;
	}

	@Override
	@Transactional
	public Long postObject(ProductUoM productUoM) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(productUoM);
		return generatedId;
	}

	@Override
	@Transactional
	public Long deleteObject(Long Id) throws  Exception {

		Long result= -1L;
		ProductUoM productUoM = new ProductUoM();
		Session session = sessionFactory.getCurrentSession();
		productUoM = session.get(ProductUoM.class, Id);
		
		if(productUoM != null) {
			result = productUoM.getUnitId();
			session.delete(productUoM);
		}
		return result != -1 ? result : -1L;
	}

	@Override
	@Transactional
	public void putObject(ProductUoM productUoM, Long unitId) throws NotAbleToUpdate, Exception {
		
		Session session = sessionFactory.getCurrentSession();
		ProductUoM uomObj = this.getObject(unitId);

		if (uomObj == null ) {
			 throw new NotAbleToUpdate("Object Not Found With Given Category Id " + unitId + ".");
		}
		else if(! uomObj.getUnitId().equals(productUoM.getUnitId())) {
			throw new NotAbleToUpdate("Object Id [" + productUoM.getUnitId() + "] Not Matched With Given Category Id [" + unitId + "].");
		}
		
		uomObj.setUnitKey(productUoM.getUnitKey()) ;
		uomObj.setUnitDescription(productUoM.getUnitDescription());
		session.persist(uomObj);
	}

	@Override
	@Transactional
	public Boolean postObjectList(List<ProductUoM> productUomList) {
		Long count = 0L;		
		for (ProductUoM productUom : productUomList) {
			
			if (productUom != null) {
				this.postObject(productUom);
				count++;
			}
		}
		if (count == productUomList.size())
			return true;
		else 
			return false;
	}

}
