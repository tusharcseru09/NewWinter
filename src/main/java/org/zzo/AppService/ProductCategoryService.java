package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.product.ProductCategory;
import org.zzo.AppRepository.ProductCategoryRepo;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepo productCategoryRepo;

	//RETRIVE ALL
	public List<ProductCategory> getProductCategoryObjectList(){
		List<ProductCategory> lstProductCategory = new ArrayList<ProductCategory>();
		lstProductCategory = productCategoryRepo.getObjectList();
		return lstProductCategory;
	}
	
	//RETRIVE SINGLE
	public ProductCategory getProductCategoryObject(Long Id) {
		return productCategoryRepo.getObject(Id);
	}
	
	//INSERT
	public Long postProductCategoryObject(ProductCategory productCategory) {
		Long createdId = productCategoryRepo.postObject(productCategory);
		return createdId;
	}
	
	//UPDATE
	public void putProductCategoryObject(ProductCategory productCategory, Long categoryId) throws NotAbleToUpdate, Exception{
		productCategoryRepo.putObject(productCategory,categoryId);
	}
	
	//DELETE
	public void deleteProductCategoryObject(Long Id) throws  Exception {
		productCategoryRepo.deleteObject(Id);
	}
	


	
	
}
/*


@GET
@Produces(MediaType.APPLICATION_JSON)
public TypeBItem getTypeBItem() {
	TypeBItem item = itemRepository.retrieveItem(itemId);
	if (item == null) {
		throw new WebApplicationException(Response.Status.NOT_FOUND);
	}
	
	return item;
}






*/