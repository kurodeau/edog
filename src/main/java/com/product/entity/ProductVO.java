package com.product.entity;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.allenum.ProductSortEnum;
import com.seller.entity.SellerVO;

@Entity
@Table(name = "product")  // Replace "your_table_name" with the actual table name
public class ProductVO {
	public static final Integer MAX_IMAGE_SIZE =  10 * 1024 * 1024;
	public static final Integer MAX_PRODUCT_SORT =  ProductSortEnum.values().length;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer productId;

    @Column(name = "sellerId")
    private Integer sellerId;

    @Lob
    @Column(name = "productCoverImg")
    private byte[] productCoverImg;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productPrice")
    private BigDecimal productPrice;

    @Column(name = "productStockQuantity")
    private Integer productStockQuantity;

    @Column(name = "productDetails")
    private String productDetails;

    @Column(name = "productStatus")
    private String productStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "productCreationTime")
    private Date productCreationTime;

    @Column(name = "totalStars")
    private Integer totalStars;

    @Column(name = "totalReviews")
    private Integer totalReviews;

    @Column(name = "productSort")
    private Integer productSort;

    @Column(name = "isEnabled")
    private Boolean isEnabled;

    @ManyToOne
    @JoinColumn(name = "sellerId", referencedColumnName = "sellerId", insertable = false, updatable = false)
    private SellerVO seller;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public byte[] getProductCoverImg() {
		return productCoverImg;
	}

	public void setProductCoverImg(byte[] productCoverImg) {
		this.productCoverImg = productCoverImg;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductStockQuantity() {
		return productStockQuantity;
	}

	public void setProductStockQuantity(Integer productStockQuantity) {
		this.productStockQuantity = productStockQuantity;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public Date getProductCreationTime() {
		return productCreationTime;
	}

	public void setProductCreationTime(Date productCreationTime) {
		this.productCreationTime = productCreationTime;
	}

	public Integer getTotalStars() {
		return totalStars;
	}

	public void setTotalStars(Integer totalStars) {
		this.totalStars = totalStars;
	}

	public Integer getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(Integer totalReviews) {
		this.totalReviews = totalReviews;
	}

	public Integer getProductSort() {
		return productSort;
	}

	public void setProductSort(Integer productSort) {
		this.productSort = productSort;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public SellerVO getSeller() {
		return seller;
	}

	public void setSeller(SellerVO seller) {
		this.seller = seller;
	}

	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", sellerId=" + sellerId + ", productCoverImg="
				+ productCoverImg != null && productCoverImg.length > 0 ? "Has Pic" : "No Pic"+ ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productStockQuantity=" + productStockQuantity + ", productDetails=" + productDetails
				+ ", productStatus=" + productStatus + ", productCreationTime=" + productCreationTime + ", totalStars="
				+ totalStars + ", totalReviews=" + totalReviews + ", productSort=" + productSort + ", isEnabled="
				+ isEnabled + ", seller=" + seller + "]";
	}

    // Add getters and setters

    // You can also add additional methods or annotations as needed

}
