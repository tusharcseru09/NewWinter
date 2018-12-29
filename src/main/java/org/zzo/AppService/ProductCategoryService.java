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

	public List<ProductCategory> getProductCategoryObjectList(){
		List<ProductCategory> lstProductCategory = new ArrayList<ProductCategory>();
		lstProductCategory = productCategoryRepo.getObjectList();
		return lstProductCategory;
	}
	
	public ProductCategory getProductCategoryObject(Long Id) {
		return productCategoryRepo.getObject(Id);
	}
	
	public Long postProductCategoryObject(ProductCategory productCategory) {
		Long createdId = productCategoryRepo.postObject(productCategory);
		return createdId;
	}
	
	public void putProductCategoryObject(ProductCategory productCategory, Long categoryId) throws NotAbleToUpdate, Exception{
		productCategoryRepo.putObject(productCategory,categoryId);
	}
	
	public void deleteProductCategoryObject(Long Id) throws  Exception {
		productCategoryRepo.deleteObject(Id);
	}
	
	public Boolean postCategoryObjectList(List<ProductCategory> categoryList){
		return productCategoryRepo.postObjectList(categoryList);
	}	
}
