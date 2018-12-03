package org.zzo.AppRepository;

import java.util.List;

import org.zzo.AppEntity.product.ProductUoM;

public interface ProductUomDAO {
	
	public ProductUoM getObject(Long Id);
	
	public Long postObject(ProductUoM productUoM);
	
	public ProductUoM deleteObject(Long Id);
	
	public List<ProductUoM> getObjectList();
}
