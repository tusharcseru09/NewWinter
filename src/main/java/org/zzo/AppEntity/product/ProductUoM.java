package org.zzo.AppEntity.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_UOM_MAIN")
public class ProductUoM {
	
	@Id 
	@Column(name="UOM_ID")
	@SequenceGenerator(name="hbm_ProductUomId", sequenceName="seq_ProdUom_ID",initialValue=1, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_ProductUomId")
	private Long unitId;
	
	@Column(name="UOM_KEY")
	private String unitKey;
	
	@Column(name="UOM_DESC")
	private String unitDescription;
	
	@OneToMany(mappedBy="productUoM")
	private List<ProductDetails> lstProductDetails = new ArrayList<ProductDetails>();
	
}
