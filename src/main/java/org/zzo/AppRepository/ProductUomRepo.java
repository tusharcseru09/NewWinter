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

	//DELETE
	@Override
	@Transactional
	public Long deleteObject(Long Id) throws  Exception {
		String queryStr = "delete from ProductUoM pu where pu.unitId=:unitId";
		Session session = sessionFactory.getCurrentSession();
		Query query =  session.createQuery(queryStr);
		query.setParameter("unitId", Id);
		long result = query.executeUpdate();
		System.out.println(result + " row deleted.");
		return result;
	}


	//UPDATE
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

}
