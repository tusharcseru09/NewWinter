package org.zzo.AppRepository;

import java.util.List;
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.ExceptionObject.NotAbleToUpdate;


public interface ProductPriceDAO {
	
	public ProductPrice getObject(Long Id);
	
	public List<ProductPrice> getObjectList();
	
	public Long deleteObject(Long Id) throws  Exception;
	
	public void putObject(ProductPrice productPrice, Long unitId)  throws NotAbleToUpdate, Exception ;
	
	public Long postObject(ProductPrice ProductPrice);
	
}
