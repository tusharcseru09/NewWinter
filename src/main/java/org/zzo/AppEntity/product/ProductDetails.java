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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.zzo.StaticContent.ErrorMessages;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="MST_PRODUCT_MAIN")
public class ProductDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @Column(name="PRODUCT_ID")
	@SequenceGenerator(name="hbm_ProductId", sequenceName="seq_Product_ID",initialValue=89876, allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hbm_ProductId")
	private Long productId;
	
	@Column(name="PRODUCT_CODE", unique = true)
	@NotBlank(message=ErrorMessages.PRODUCT_CODE_NOTBLANK)
	@Size(min=ErrorMessages.PRODUCT_CODE_MIN_LEN, message=ErrorMessages.PRODUCT_CODE_SIZE)
	private String productCode;
	
	@Column(name="PRODUCT_NAME", unique = true)
	@NotBlank(message=ErrorMessages.PRODUCT_NAME_NOTBLANK)
	@Size(min=ErrorMessages.PRODUCT_NAME_MIN_LEN, message=ErrorMessages.PRODUCT_NAME_SIZE)
	private String productName;
	
	@Column(name="PRODUCT_DESC", unique = true)
	@NotBlank(message=ErrorMessages.PRODUCT_DESC_NOTBLANK)
	@Size(min=ErrorMessages.PRODUCT_DESC_MIN_LEN, message=ErrorMessages.PRODUCT_DESC_SIZE)
	private String productDesc;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_UNIT")
	private ProductUoM productUoM;
	
	@ManyToOne()
	@JoinColumn(name="PRODUCT_CATEGORY")
	private  ProductCategory productCategory;
	
	@Column(name="PRODUCT_TAXABLE")
	private Boolean taxAble;
	
	@Column(name="PRODUCT_COMMENT")
	private String productComment; 
	
	/*
	@Column(name="PRODUCT_STARING_INV")
	private Long startingInventory;
	
	@Column(name="PRODUCT_REORDER_POINT")
	private Long reOrderPoint;

	@OneToMany(mappedBy="productDetails",cascade=CascadeType.ALL)
	private List<ProductPrice> lstProductPrice = new ArrayList<ProductPrice>();
	*/
	
	@JsonIgnore
	@OneToMany(mappedBy="productId", cascade=CascadeType.ALL)
	private List<ProductPrice> lstProductDetails = new ArrayList<ProductPrice>();

}
