package com.yiyou.repast.merchant.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 短信记录
 */
@Entity
@Table(name = "t_sms")
public class Sms implements Serializable {
	
	private static final long serialVersionUID = 7097989172877267874L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mobile;
	private String content;
	private Date sendDate;
	private int ret;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	
}
