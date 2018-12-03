package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppEntity.product.ProductUoM;
import org.zzo.AppRepository.ProductUomRepo;

@Service
public class ProductUomService {
	
	@Autowired
	private ProductUomRepo productUomRepo ;
	
	
	public Long postProductUomObject(ProductUoM productUoM) {
		Long Id;
		Id = productUomRepo.postObject(productUoM);
		return Id;
	}
	
	
	public List<ProductUoM> getProductUomObjectList() {
		List<ProductUoM> lstPrUom = new ArrayList<ProductUoM>();
		lstPrUom = productUomRepo.getObjectList();
		return lstPrUom;
	}

}
