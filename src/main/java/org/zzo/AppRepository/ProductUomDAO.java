package org.zzo.AppRepository;

import java.util.List;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.ExceptionObject.NotAbleToUpdate;

public interface ProductUomDAO {
	
	public ProductUoM getObject(Long Id);
	
	public List<ProductUoM> getObjectList();
	
	public Long deleteObject(Long Id) throws  Exception;
	
	public void putObject(ProductUoM productUoM, Long unitId)  throws NotAbleToUpdate, Exception ;
	
	public Long postObject(ProductUoM productUoM);
	
	public Boolean postObjectList(List<ProductUoM> productUomList);
	
}
