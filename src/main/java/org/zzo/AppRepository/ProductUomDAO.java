package org.zzo.AppRepository;

import java.util.List;

import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.ExceptionObject.NotAbleToUpdate;

public interface ProductUomDAO {
	
	public ProductUoM getObject(Long Id);
	
	public List<ProductUoM> getObjectList();
	
	//public ProductUoM deleteObject(Long Id) throws  Exception;
	
	//public void putObject(ProductCategory productCategory, Long categoryId)  throws NotAbleToUpdate, Exception ;
	
	public Long postObject(ProductUoM productUoM);
	
}
