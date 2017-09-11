package com.yiyou.repast.merchant.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品分类
 */
@Entity
@Table(name = "t_goods_category")
public class GoodsCategory implements Serializable {

    private static final long serialVersionUID = 1801573277087560343L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long merchantId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private GoodsCategory parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    private Set<GoodsCategory> children = new HashSet<>(0);

    private String name;//分类名称
    private Integer priority;//优先级
    private String remark;//备注
    private Date createTime;

    public String getParentName() {
        if (parent != null) {
            return parent.getName();
        } else {
            return "";
        }
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

    public GoodsCategory getParent() {
        return parent;
    }

    public void setParent(GoodsCategory parent) {
        this.parent = parent;
    }

    public Set<GoodsCategory> getChildren() {
        return children;
    }

    public void setChildren(Set<GoodsCategory> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
