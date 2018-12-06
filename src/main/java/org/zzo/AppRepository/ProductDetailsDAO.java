package org.zzo.AppRepository;

import java.util.List;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.ExceptionObject.NotAbleToUpdate;


public interface ProductDetailsDAO {
	
	public ProductDetails getObject(Long Id);
	
	public List<ProductDetails> getObjectList();
	
	public Long deleteObject(Long Id) throws  Exception;
	
	public void putObject(ProductDetails productDetails, Long unitId)  throws NotAbleToUpdate, Exception ;
	
	public Long postObject(ProductDetails productDetails);
	
	
}
