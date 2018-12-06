package org.zzo.AppService;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppController.StaticMembers;
import org.zzo.AppEntity.product.ProductDetails;
import org.zzo.AppEntity.product.ProductPrice;
import org.zzo.AppForm.ProductPriceForm;
import org.zzo.AppRepository.ProductDetailsRepo;
import org.zzo.AppRepository.ProductPriceRepo;

@Service
public class ProductPriceService {

	@Autowired 
	private ProductPriceRepo productPriceRepo;
	
	@Autowired
	private ProductDetailsRepo productDetailsRepo;
	
	public Long PostProductPriceObject(ProductPriceForm productPriceForm) {
		
		Long generatedId = -1L;
		ProductPrice productPrice = new ProductPrice();
		ProductDetails productDetails = new ProductDetails();
		
		productDetails = productDetailsRepo.getObject(productPriceForm.getProductId());
		productPrice.setProductDetails(productDetails);
		
		productPrice.setPurchasePrice(productPriceForm.getPurchasePrice());
		productPrice.setSalePrice(productPriceForm.getSalePrice());
		productPrice.setActivationDate(StaticMembers.asDate(StaticMembers.getLocalDate(productPriceForm.getActivationDate().toString(), "d.MM.yyyy")));
		productPrice.setComment(productPriceForm.getComment());
		productPrice.setIsActive(true);
		productPrice.setIsDeleted(false);
		
		generatedId = productPriceRepo.postObject(productPrice);
		
		return generatedId;
	}
}
*/