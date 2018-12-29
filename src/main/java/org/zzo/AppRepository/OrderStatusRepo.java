package org.zzo.AppRepository;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zzo.AppEntity.order.OrderStatus;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Repository
public class OrderStatusRepo implements OrderStatusDAO{

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public OrderStatus getObject(Long Id) {
		OrderStatus orderStatus = new OrderStatus();
		Session session = sessionFactory.getCurrentSession();
		orderStatus = session.get(OrderStatus.class, Id);
		return orderStatus;
	}
	

	@Override
	@Transactional
	public List<OrderStatus> getObjectList() {
		String query = "from OrderStatus";
		Session session = sessionFactory.getCurrentSession();
		List<OrderStatus> lstOrderStatus = new ArrayList<OrderStatus>();
		List<?> list = session.createQuery(query).list();
		
		for(int i=0; i < list.size(); i++) {
			OrderStatus orderStatus = (OrderStatus)list.get(i);
			lstOrderStatus.add(orderStatus);
		}	
		
		return lstOrderStatus;
	}

	
	@Override
	@Transactional
	public Long deleteObject(Long Id) throws Exception {
		Long result= -1L;
		OrderStatus orderStatus = new OrderStatus();
		Session session = sessionFactory.getCurrentSession();
		orderStatus = session.get(OrderStatus.class, Id);
		
		if(orderStatus != null) {
			result = orderStatus.getStatusId();
			session.delete(orderStatus);
		}
		return result != -1 ? result : -1L;
	}

	
	
	@Override
	@Transactional
	public void putObject(OrderStatus orderStatus, Long statusId) throws NotAbleToUpdate, Exception {
		Session session = sessionFactory.getCurrentSession();
		OrderStatus requestedOrderStatus = this.getObject(statusId);

		if (requestedOrderStatus == null ) {
			 throw new NotAbleToUpdate("Data not found with given Id " + statusId + ".");
		}
		else if(! requestedOrderStatus.getStatusId().equals(orderStatus.getStatusId())) {
			throw new NotAbleToUpdate("Object id and url id not matched.");
		}
		
		requestedOrderStatus.setParentStatus(orderStatus.getParentStatus());
		requestedOrderStatus.setChildStatus(orderStatus.getChildStatus());
		requestedOrderStatus.setStatusColor(orderStatus.getStatusColor());
		
		session.persist(requestedOrderStatus);
		
	}

	@Override
	@Transactional
	public Long postObject(OrderStatus orderStatus) {
		Long generatedId = -1L;
		Session session = sessionFactory.getCurrentSession();
		generatedId = (Long) session.save(orderStatus);
		return generatedId;
	}


	@Override
	@Transactional
	public Boolean postObjectList(List<OrderStatus> statusList) {
		
		Long count = 0L;		
		for (OrderStatus orderStatus : statusList) {
			
			if (orderStatus != null) {
				this.postObject(orderStatus);
				count++;
			}
		}
		if (count == statusList.size())
			return true;
		else 
			return false;
	}
	

}
