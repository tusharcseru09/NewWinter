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
import javax.validation.constraints.NotNull;
import org.zzo.StaticContent.ErrorMessages;
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
	@NotNull(message=ErrorMessages.PRODUCT_PRICE_NOTNULL)
	@JoinColumn(name="PRODUCT_ID", nullable = false)
	private ProductDetails productDetails;

	
	@Column(name="PURCHASE_PRICE", nullable=false) 
	private Double purchasePrice;

	
	@Column(name="SALES_PRICE", nullable=false)
	private Double salePrice;
	
	
	@Column(name="ACTIVATION_DATE", nullable=false)
	private Date activationDate = new Date();

	
	@Column(name="USER_COMMENT")
	private String comment;
	
}	
