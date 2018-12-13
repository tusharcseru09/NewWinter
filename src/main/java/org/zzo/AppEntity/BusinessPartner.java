package org.zzo.AppEntity;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name="MST_BUSINESS_PARTNER")
public class BusinessPartner {

	
	@Id @Column(name="PARTNER_ID")
	@SequenceGenerator(name="hbm_Partner_Id", sequenceName="seq_Partner_Id",initialValue=500, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_Partner_Id")
	private Long partnerId;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="partnerId", cascade=CascadeType.ALL)
	private List<ProductOrder> lstProductOrder = new ArrayList<ProductOrder>();
	
	
	
	
	@Column(name="USER_FIRSTNAME")
	private String userFirstName;


	
}
