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
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory {
	
	
	@Id @Column(name="CATEGORY_ID")
	@SequenceGenerator(name="hbm_ProductCategoryID", sequenceName="seq_ProdCategory_ID",initialValue=1, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_ProductCategoryID")
	private Long categoryId;
	
	@JsonIgnore
	@OneToMany(mappedBy="categoryId") 
	private List<ProductDetails> lstProductDetails = new ArrayList<ProductDetails>();
	
	
	@Column(name="CATEGORY_CODE", unique = true, nullable = false)
	@Size(min=ErrorMessages.CATEGORY_CODE_MIN_LEN, message=ErrorMessages.PRODUCT_CATETORY_CODE_SIZE)
	private String categoryCode;
	
	@Column(name="CATEGORY_DESC", unique = true, nullable = false)
	@Size(min=ErrorMessages.CATEGORY_DESC_MIN_LEN, message=ErrorMessages.PRODUCT_CATETORY_DESC_SIZE)
	private String categoryDesc;

}
