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

import org.zzo.AppEntity.ProductOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_STATUS")
public class OrderStatus {
	
	@Id @Column(name="STATUS_ID")
	@SequenceGenerator(name="hbm_Status_Id", sequenceName="seq_Status_Id",initialValue=1, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_Status_Id")
	private Long statusId;
	
	@JsonIgnore
	@OneToMany(mappedBy="statusId", cascade=CascadeType.ALL)
	private List<ProductOrder> lstProductOrder = new ArrayList<ProductOrder>();
	
	@Column(name="LIFE_CYCLE", nullable=false)
	private String lifeCycle;
	
	@Column(name="PARENT_STATUS", nullable=false)
	private String parentStatus;
	
	@Column(name="CHILD_STATUS")
	private String childStatus;
	
	@Column(name="STATUS_COLOR")
	private String statusColor;

}
