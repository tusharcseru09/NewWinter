package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.AppRepository.ProductPriceRepo;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Service
public class ProductPriceService {

	@Autowired 
	private ProductPriceRepo productPriceRepo;
	
	public ProductPrice getProductPriceObject(Long Id){
		return productPriceRepo.getObject(Id);
	}
	
	public List<ProductPrice> getProductPriceObjectList() {
		List<ProductPrice> lstProductPrice = new ArrayList<ProductPrice>();
		lstProductPrice = productPriceRepo.getObjectList();
		return lstProductPrice;
	}
	
	public Long deleteProductPriceObject(Long Id) throws  Exception {
		return productPriceRepo.deleteObject(Id);
	}
	
	public Long postProductPriceObject(ProductPrice productPrice) {
		Long createdId = productPriceRepo.postObject(productPrice);
		return createdId;
	}
	
	public void putProductPriceObject(ProductPrice productPrice, Long priceId) throws NotAbleToUpdate, Exception {
		productPriceRepo.putObject(productPrice, priceId);
	}	

}
