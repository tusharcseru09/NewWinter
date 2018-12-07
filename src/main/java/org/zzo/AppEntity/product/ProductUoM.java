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
import javax.validation.constraints.Size;
import org.zzo.StaticContent.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	
	@Column(name="UOM_KEY", unique = true, nullable = false)
	@Size(min=ErrorMessages.UOM_KEY_MIN_LEN, message=ErrorMessages.PRODUCT_UOM_KEY_SIZE)
	private String unitKey;
	
	
	@Column(name="UOM_DESC", unique = true, nullable = false)
	@Size(min=ErrorMessages.UOM_DESC_MIN_LEN, message=ErrorMessages.PRODUCT_UOM_DESC_SIZE)
	private String unitDescription;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="productUoM")
	private List<ProductDetails> lstProductDetails = new ArrayList<ProductDetails>();
	
	
}
