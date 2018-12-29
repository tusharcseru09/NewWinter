package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.order.OrderType;
import org.zzo.AppRepository.OrderTypeRepo;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Service
public class OrderTypeService {

	@Autowired
	private OrderTypeRepo orderTypeRepo;
	
	public OrderType getOrderTypeObject(Long Id){
		return orderTypeRepo.getObject(Id);
	}
		
	public List<OrderType> getOrderTypeObjectList(){
		List<OrderType> lstOrderType = new ArrayList<OrderType>();
		lstOrderType = orderTypeRepo.getObjectList();
		return lstOrderType;
	}
	
	public Long deleteOrderTypeObject(Long Id) throws  Exception {
		return orderTypeRepo.deleteObject(Id);
	}
	
	public Long postOrderTypeObject(OrderType orderType) {
		Long createdId = orderTypeRepo.postObject(orderType);
		return createdId;
	}
	
	public void putOrderTypeObject(OrderType orderType, Long orderTypeId) throws NotAbleToUpdate, Exception {
		orderTypeRepo.putObject(orderType, orderTypeId);
	}
	
	public Boolean postOrderTypeObjectList(List<OrderType> orderTypesList){
		return orderTypeRepo.postObjectList(orderTypesList);
	}
	
}
