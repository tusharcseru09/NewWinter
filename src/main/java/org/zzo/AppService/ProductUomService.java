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
	
	/*
	 * 
	 * getNameObject
		PostNameObject
		PutNameObject
		DeleteNameObject
		getNameObjectList
	 */
	
	//INSERT
	public Long postProductUomObject(ProductUoM productUoM) {
		Long createdId = productUomRepo.postObject(productUoM);
		return createdId;
	}
	
	//RETRIVE ALL
	public List<ProductUoM> getProductUomObjectList() {
		List<ProductUoM> lstPrUom = new ArrayList<ProductUoM>();
		lstPrUom = productUomRepo.getObjectList();
		return lstPrUom;
	}

	//RETRIVE SINGLE
	public ProductUoM getProductUomObject(Long Id) {
		return productUomRepo.getObject(Id);
	}
	
}
