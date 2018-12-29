package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.AppRepository.ProductUomRepo;
import org.zzo.ExceptionObject.NotAbleToUpdate;

@Service
public class ProductUomService {
	
	@Autowired
	private ProductUomRepo productUomRepo ;
	
	public Long postProductUomObject(ProductUoM productUoM) {
		Long createdId = productUomRepo.postObject(productUoM);
		return createdId;
	}
	
	public List<ProductUoM> getProductUomObjectList() {
		List<ProductUoM> lstPrUom = new ArrayList<ProductUoM>();
		lstPrUom = productUomRepo.getObjectList();
		return lstPrUom;
	}

	public ProductUoM getProductUomObject(Long Id) {
		return productUomRepo.getObject(Id);
	}
	
	public void putProductUomObject(ProductUoM productUoM, Long uomId) throws NotAbleToUpdate, Exception{
		productUomRepo.putObject(productUoM,uomId);
	}
	
	public void deleteProductUomObject(Long Id) throws  Exception {
		productUomRepo.deleteObject(Id);
	}
	
	public Boolean postProductUomObjectList(List<ProductUoM> productUom){
		return productUomRepo.postObjectList(productUom);
	}
}
