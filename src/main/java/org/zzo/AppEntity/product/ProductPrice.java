package org.zzo.AppEntity.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_PRICE_MAIN")
public class ProductPrice {
	
	@Id @Column(name="PRICE_ID")
	@SequenceGenerator(name="hbm_ProductPriceId", sequenceName="seq_ProdPrice_ID",initialValue=1, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_ProductPriceId")
	private Long priceId;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_CODE")
	private ProductDetails productDetails;
	
	@Column(name="PURCHASE_PRICE")
	private Double purchasePrice;
	
	@Column(name="SALES_PRICE")
	private Double salePrice;
	
	@Column(name="ACTIVATION_DATE")
	private Date activationDate;
	
	@Column(name="PRICE_ACTIVE")
	private Boolean isActive;
	
	@Column(name="PRICE_DELETED")
	private Boolean isDeleted;
	
	@Column(name="USER_COMMENT")
	private String comment;
}	
