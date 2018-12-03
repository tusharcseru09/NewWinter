package org.zzo.AppForm;

import lombok.Data;


@Data
public class ProductDetailsForm {

	private String productCode;
	private String productName;
	private String productDesc;
	private Long startingInventory;
	private Long reOrderPoint;
	private Long productUnit;
	private Long productCategory;
	private Boolean taxAble;
	private String productComment; 
	
}
