package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.order.OrderType;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Repository
public class OrderTypeRepo implements OrderTypeDAO{

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public OrderType getObject(Long Id) {
		OrderType orderType = new OrderType();
		Session session = sessionFactory.getCurrentSession();
		orderType = session.get(OrderType.class, Id);
		return orderType;
	}

	@Override
	@Transactional
	public List<OrderType> getObjectList() {
		String query = "from OrderType";
		Session session = sessionFactory.getCurrentSession();
		List<OrderType> lstOrderType = new ArrayList<OrderType>();
		List<?> list = session.createQuery(query).list();
		
		for(int i=0; i < list.size(); i++) {
			OrderType orderType = (OrderType)list.get(i);
			lstOrderType.add(orderType);
		}	
		
		return lstOrderType;
	}

	@Override
	@Transactional
	public Long deleteObject(Long Id) throws Exception {
		Long result= -1L;
		OrderType orderType = new OrderType();
		Session session = sessionFactory.getCurrentSession();
		orderType = session.get(OrderType.class, Id);
		
		if(orderType != null) {
			result = orderType.getOrderTypeId();
			session.delete(orderType);
		}
		return result != -1 ? result : -1L;
	}

	@Override
	@Transactional
	public void putObject(OrderType orderType, Long typeId) throws NotAbleToUpdate, Exception {
		Session session = sessionFactory.getCurrentSession();
		OrderType requestedOrderType = this.getObject(typeId);

		if (requestedOrderType == null ) {
			 throw new NotAbleToUpdate("Data not found with given Id " + typeId + ".");
		}
		else if(! requestedOrderType.getOrderTypeId().equals(orderType.getOrderTypeId())) {
			throw new NotAbleToUpdate("Object id and url id not matched.");
		}
		
		requestedOrderType.setOrderTypeDesc(orderType.getOrderTypeDesc());
		requestedOrderType.setStockSign(orderType.getStockSign());
		session.persist(requestedOrderType);
		
	}

	@Override
	@Transactional
	public Long postObject(OrderType orderType) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(orderType);
		return generatedId;
	}

	@Override
	@Transactional
	public Boolean postObjectList(List<OrderType> orderTypeList) {
		Long count = 0L;		
		for (OrderType orderType : orderTypeList) {
			
			if (orderType != null) {
				this.postObject(orderType);
				count++;
			}
		}
		if (count == orderTypeList.size())
			return true;
		else 
			return false;
	}

}
