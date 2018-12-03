package org.zzo.AppTable;

import lombok.Data;


@Data
public class ProductPriceTable {

	private Long product_id;
	private String productCode;
	private String productName;
	private String productDesc;

	private String unitKey;
	private String unitDescription;
	
	private String categoryCode;
	private String categoryDesc;
	
	private Double purchase_price;
	private Double sales_price;
	private String priceComment;

}
