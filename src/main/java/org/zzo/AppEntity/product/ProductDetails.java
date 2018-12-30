package org.zzo.AppEntity.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="MST_PRODUCT_DETAILS")
public class ProductDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @Column(name="PRODUCT_ID")
	@SequenceGenerator(name="hbm_ProductId", sequenceName="seq_Product_ID",initialValue=1, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_ProductId")
	private Long productId;
	
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_UNIT", nullable=false)
	private ProductUoM unitId;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_CATEGORY", nullable=false)
	private  ProductCategory categoryId;
	
	@JsonIgnore
	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
	private List<ProductPrice> lstProductPrice = new ArrayList<ProductPrice>();
	
	@JsonIgnore
	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
	private List<ProductOrder> lstProductOrder = new ArrayList<ProductOrder>();
	
	
	@Column(name="PRODUCT_CODE", unique=true, nullable=false)
	@Size(min=ErrorMessages.PRODUCT_CODE_MIN_LEN, message=ErrorMessages.PRODUCT_CODE_SIZE)
	private String productCode;
	
	@Column(name="PRODUCT_NAME", nullable=false)
	@Size(min = ErrorMessages.PRODUCT_NAME_MIN_LEN, message = ErrorMessages.PRODUCT_NAME_SIZE)
	private String productName;
	
	@Column(name="PRODUCT_DESC", nullable=false)
	@Size(min=ErrorMessages.PRODUCT_DESC_MIN_LEN, message = ErrorMessages.PRODUCT_DESC_SIZE)
	private String productDesc;
	
	@Column(name="PRODUCT_TAXABLE", nullable=false, columnDefinition="boolean default false")
	private Boolean taxAble = false;

	@Column(name="PRODUCT_COMMENT")
	private String productComment; 

}
