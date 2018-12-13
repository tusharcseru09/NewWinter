package org.zzo.AppEntity.order;

import java.util.ArrayList;
import java.util.Date;
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

import org.zzo.AppEntity.ProductOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_HEADER")
public class OrderHeader {

	@Id @Column(name="ORDE_NUMBER")
	@SequenceGenerator(name="hbm_OrderHeaderID", sequenceName="seq_OrderHeader_ID",initialValue=1, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_OrderHeaderID")
	private Long orderNumber;
	
	@JsonIgnore
	@OneToMany(mappedBy="orderNumber", cascade=CascadeType.ALL)
	private List<ProductOrder> lstProductOrder = new ArrayList<ProductOrder>();
	
	
	
	@Column(name="ORDER_DATE", nullable=false)
	private Date orderDate = new Date();
	
	@Column(name="EXPECTED_DATE", nullable=false)
	private Date expectedDate;
	
	@Column(name="ORDER_COMMENTS")
	private String orderComment;
	
	
}
