package org.zzo.AppService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzo.AppRepository.ProductPriceTableRepo;
import org.zzo.AppTable.ProductPriceTable;

@Service
public class ProductPriceTableServie {

	@Autowired
	private ProductPriceTableRepo productPriceTableRepo;
	
	public List<ProductPriceTable> getProductPriceObjectList(){
		
		List<ProductPriceTable> lstProductPriceTable = new ArrayList<ProductPriceTable>();
		lstProductPriceTable = productPriceTableRepo.getProductPriceTableObjectList();
		return lstProductPriceTable;
		
	}
	
}
