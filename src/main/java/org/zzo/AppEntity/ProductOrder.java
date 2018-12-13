package org.zzo.AppEntity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.zzo.AppEntity.order.OrderHeader;
import org.zzo.AppEntity.order.OrderType;
import org.zzo.AppEntity.product.ProductDetails;
import lombok.Data;

@Data
@Entity
@Table(name="MST_PRODUCT_ORDER")
public class ProductOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="PRODUCT_ORDER_ID")
	@SequenceGenerator(name="hbm_ProductOrderId", sequenceName="seq_ProductOrder_ID",initialValue=115413, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_ProductOrderId")
	private Long productOrderId;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", nullable=false)
	private ProductDetails productId;
	
	@ManyToOne
	@JoinColumn(name="ORDER_NUMBER", nullable=false)
	private OrderHeader orderNumber;
	
	@ManyToOne
	@JoinColumn(name="ORDER_TYPE", nullable=false)
	private OrderType orderTypeId;
	
	@ManyToOne
	@JoinColumn(name="PARTNER_ID", nullable=false)
	private BusinessPartner partnerId;
	
	
	
	@Column(name="UNIT_DESCOUNT")
	private Double unitDiscount;
	
	@Column(name="EXTRA_CHARGE")
	private Double extraCharge;
	
	@Column(name="PRODUCT_QUANTITY")
	private Long productQuantity;
	
	@Column(name="UNIT_TAX")
	private Double taxRate;

}
