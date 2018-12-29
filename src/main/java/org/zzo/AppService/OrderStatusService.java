package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.order.OrderStatus;
import org.zzo.AppRepository.OrderStatusRepo;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Service
public class OrderStatusService {

	@Autowired
	private OrderStatusRepo orderStatusRepo ;

	public OrderStatus getOrderStatusObject(Long Id){
		return orderStatusRepo.getObject(Id);
	}
	
	
	public List<OrderStatus> getOrderStatusObjectList(){
		List<OrderStatus> lstOrderStatus = new ArrayList<OrderStatus>();
		lstOrderStatus = orderStatusRepo.getObjectList();
		return lstOrderStatus;
	}
	
	
	public Long deleteOrderStatusObject(Long Id) throws  Exception {
		return orderStatusRepo.deleteObject(Id);
	}
	
	
	public Long postOrderStatusObject(OrderStatus orderStatus) {
		Long createdId = orderStatusRepo.postObject(orderStatus);
		return createdId;
	}
	
	public void putOrderStatusObject(OrderStatus orderStatus, Long statusId) throws NotAbleToUpdate, Exception {
		orderStatusRepo.putObject(orderStatus, statusId);
	}
	
	public Boolean postOrderStatusObjectList(List<OrderStatus> statusList){
		return orderStatusRepo.postObjectList(statusList);
	}
}
