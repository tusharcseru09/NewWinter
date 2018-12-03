package org.zzo.AppRepository;

import java.util.List;

import org.zzo.AppEntity.product.ProductDetails;



public interface ProductDetailsDAO {
	
	public ProductDetails getObject(Long Id);
	
	public Long postObject(ProductDetails productDetails);
	
	public ProductDetails deleteObject(Long Id);
	
	public List<ProductDetails> getObjectList();
}
