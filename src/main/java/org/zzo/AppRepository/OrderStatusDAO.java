package org.zzo.AppRepository;

import java.util.List;

import org.zzo.AppEntity.order.OrderStatus;
import org.zzo.ExceptionObject.NotAbleToUpdate;

public interface OrderStatusDAO {
	
	public OrderStatus getObject(Long Id);
	
	public List<OrderStatus> getObjectList();
	
	public Long deleteObject(Long Id) throws  Exception;
	
	public void putObject(OrderStatus orderStatus, Long statusId)  throws NotAbleToUpdate, Exception ;
	
	public Long postObject(OrderStatus orderStatus);
	
	public Boolean postObjectList(List<OrderStatus> statusList);

}
