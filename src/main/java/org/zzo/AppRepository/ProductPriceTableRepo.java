
/*package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppTable.ProductPriceTable;


@Repository
public class ProductPriceTableRepo {

	@Autowired
	private SessionFactory sessionFactory;
	

	@Transactional
	public List<ProductPriceTable> getProductPriceTableObjectList(){
		
		System.out.println("---------------------TUSHAR---------------------------");
		
		Session session = sessionFactory.getCurrentSession();
		String HQL = " select 	product.productId,  "+
					 
					 " product.productCode, product.productName, product.productDesc, product.startingInventory, product.reOrderPoint, product.taxAble,  product.productComment, "+
					 " product.productUoM, unit.unitId, unit.unitKey, unit.unitDescription,	"+
					 " product.productCategory, category.categoryId, category.categoryCode, category.categoryDesc, "+
					 " price.productDetails, price.purchasePrice, price.salePrice, price.activationDate, price.comment "+
					 
					 " from ProductDetails as product " +
					 " left  join product.productUoM as unit " +
					 " left  join product.productCategory as category " + 
					 " left  join product.lstProductPrice  as price " +
					 
					 " where price.isActive=:priceActive and  price.isDeleted=:priceDeleted ";

		
		Query<Object[]> query = session.createQuery(HQL);
		query.setParameter("priceActive", true);
		query.setParameter("priceDeleted", false);
		List<Object[]> listPordPrice = query.list();
	
		List<ProductPriceTable> productPriceTableList = new ArrayList<ProductPriceTable>();
		for (Object[] objects : listPordPrice) {
			ProductPriceTable obj = new ProductPriceTable();
			
			obj.setProduct_id((Long) objects[0]);
			obj.setProductCode((String) objects[1]);
			obj.setProductName((String) objects[2]);
			obj.setProductDesc((String) objects[3]);

			obj.setUnitKey((String) objects[10]);
			obj.setUnitDescription((String) objects[11]);
			
			obj.setCategoryCode((String) objects[14]);
			obj.setCategoryDesc((String) objects[15]);
			
			obj.setPurchase_price((Double) objects[17]);
			obj.setSales_price((Double) objects[18]);
			obj.setPriceComment((String) objects[02]);
			
			productPriceTableList.add(obj);
		}

		System.out.println("------------------------------------------------");
		

		
		return productPriceTableList;
		
	}
	
}
*/