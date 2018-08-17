package com.sf.wdx.domain;

/**
 * 描述：商品类
 * @author 80002888
 * @date   2018年8月17日
 */
public class Product {

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 主题
	 */
	private String title;

	/**
	 * 卖点
	 */
	private String sellpoint;

	/**
	 * 价格
	 */
	private Long price;

	/**
	 * 数量
	 */
	private Integer num;

	/**
	 * 图片地址
	 */
	private String image;

	/**
	 * 分类id
	 */
	private Long cid;

	/**
	 * 状态
	 */
	private Integer status;

	public Product() {
	}

	public Product(Long id, String title, String sellpoint, Long price, Integer num, String image, Long cid,
			Integer status) {
		this.id = id;
		this.title = title;
		this.sellpoint = sellpoint;
		this.price = price;
		this.num = num;
		this.image = image;
		this.cid = cid;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellpoint() {
		return sellpoint;
	}

	public void setSellpoint(String sellpoint) {
		this.sellpoint = sellpoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", sellpoint=" + sellpoint + ", price=" + price + ", num="
				+ num + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
	}

}
