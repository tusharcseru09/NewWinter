package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.AppRepository.ProductDetailsRepo;
import org.zzo.ExceptionObject.NotAbleToUpdate;


@Service
public class ProductDetailsService {
	
	@Autowired
	private ProductDetailsRepo productDetailsRepo ;

	
	public ProductDetails getProductDetailsObject(Long Id){
		return productDetailsRepo.getObject(Id);
	}
	
	
	public List<ProductDetails> getProductDetailsObjectList(){
		List<ProductDetails> lstProductDetails = new ArrayList<ProductDetails>();
		lstProductDetails = productDetailsRepo.getObjectList();
		return lstProductDetails;
	}
	
	
	public Long deleteProductDetailsObject(Long Id) throws  Exception {
		return productDetailsRepo.deleteObject(Id);
	}
	
	
	public Long postProductDetailsObject(ProductDetails productDetails) {
		Long createdId = productDetailsRepo.postObject(productDetails);
		return createdId;
	}
	
	public void putProductDetailsObject(ProductDetails productDetails, Long productID) throws NotAbleToUpdate, Exception {
		 productDetailsRepo.putObject(productDetails, productID);
	}	
	
}
