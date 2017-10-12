package com.yiyou.repast.merchant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import repast.yiyou.common.base.CommonConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 商品
 */
@Entity
@Table(name = "t_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = -5737781723933462465L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long merchantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private GoodsCategory category;//商品分类

    @ManyToMany(targetEntity = GoodsSpec.class, fetch = FetchType.EAGER)
    @JoinTable(name = "t_goods_spec_r", joinColumns = @JoinColumn(name = "goods_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "spec_id", referencedColumnName = "id"))
    @JsonBackReference
    private Set<GoodsSpec> specs = new HashSet<>();

    private String name;//商品名称
    private String pic;//商品图片地址
    private BigDecimal amount;//商品金额
    private String auxIds;//辅料ID，逗号分隔

    private Byte newon;//是否新品
    private String remark;//描述
    private Integer sales = 0;//累计销量
    private Date createTime;//发布时间
    private String operUser;//操作人

    private Boolean shelves ;//是否上架
    
    @Transient
    private List<GoodsAux> auxs = new ArrayList<>();
    @Transient
    private String picUrl;//图片访问地址

    public List<Long> getSpecsId() {
        List<Long> ids = new ArrayList<>();
        for (GoodsSpec spec : specs) {
            ids.add(spec.getId());
        }
        return ids;
    }

    public String getSpecsName() {
        if (specs.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (GoodsSpec spec : specs) {
                sb.append(spec.getName());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        } else {
            return "";
        }
    }

    public String getCategoryName() {
        if (category == null) {
            return "";
        } else {
            return category.getName();
        }
    }

    public void setShelves(Boolean shelves) {
        this.shelves = shelves;
    }

    public Boolean getShelves() {
        return shelves;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
        this.category = category;
    }

    public Set<GoodsSpec> getSpecs() {
        return specs;
    }

    public void setSpecs(Set<GoodsSpec> specs) {
        this.specs = specs;
    }

    public String getAuxIds() {
        return auxIds;
    }

    public void setAuxIds(String auxIds) {
        this.auxIds = auxIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getNewon() {
        return newon;
    }

    public void setNewon(Byte newon) {
        this.newon = newon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperUser() {
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

	public List<GoodsAux> getAuxs() {
		return auxs;
	}
	public void setAuxs(List<GoodsAux> auxs) {
		this.auxs = auxs;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = CommonConstants.IMG_DOMAIN+"/common/img/"+getPic()+"/0";
	}

}
