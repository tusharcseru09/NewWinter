package org.zzo.AppRepository;

import java.util.List;

import org.zzo.AppEntity.order.OrderType;
import org.zzo.ExceptionObject.NotAbleToUpdate;

public interface OrderTypeDAO {
	
	public OrderType getObject(Long Id);
	
	public List<OrderType> getObjectList();
	
	public Long deleteObject(Long Id) throws  Exception;
	
	public void putObject(OrderType orderType, Long typeId)  throws NotAbleToUpdate, Exception ;
	
	public Long postObject(OrderType orderType);
	
	public Boolean postObjectList(List<OrderType> orderTypeList);

}
