package org.zzo.AppForm;

import lombok.Data;

@Data
public class ProductPriceForm {

	private Long productId;
	private String activationDate;
	private Double purchasePrice;
	private Double salePrice;
	private String comment;
	
}
