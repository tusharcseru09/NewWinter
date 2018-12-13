package org.zzo.AppEntity.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.zzo.AppEntity.ProductOrder;
import org.zzo.StaticContent.ErrorMessages;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_TYPE")
public class OrderType {

	@Id @Column(name="ORDE_TYPE_ID")
	@SequenceGenerator(name="hbm_OrderTypeID", sequenceName="seq_OrderType_ID",initialValue=1, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_OrderTypeID")
	private Long orderTypeId;
	
	@JsonIgnore
	@OneToMany(mappedBy="orderTypeId", cascade=CascadeType.ALL)
	private List<ProductOrder> lstProductOrder = new ArrayList<ProductOrder>();
	
	
	
	@Column(name="ORDE_TYPE_DESC", unique = true, nullable = false)
	@Size(min=ErrorMessages.ORDE_TYPE_MIN_LEN, message=ErrorMessages.ORDE_TYPE_DESC_SIZE)
	private String orderTypeDesc;
	
	@Column(name="ORDER_STOCK_SIGN", nullable = false)
	private double stockSign;
	

}
