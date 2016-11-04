package com.core.bean;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统用户
 * 
 * @author sdyang
 * @date 2016年1月23日 下午5:32:11
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 登陆名 */
	private String loginName;

	/** 用户名 */
	private String name;

	/** 密码 */
	private String password;

	/** 性别 */
	private Integer sex;

	/** 年龄 */
	private Integer age;

	/** 手机号 */
	private String phone;

	/** 用户类别 */
	private Integer userType;

	/** 用户状态 */
	private Integer status;

	/** 所属机构 */
	private Integer organizationId;

	/** 创建时间 */
	private Date createTime;

	@Column
	private String email;// 邮箱

	@Column
	private String merchant_code;//商户编码

	@Column
	private String merchant_name;//商户名称

	@Column
	private String app_no;//应用号

	@Column(name="md5key")
	private String key;//MD5加密所需的KEY

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMerchant_code() {
		return merchant_code;
	}

	public void setMerchant_code(String merchant_code) {
		this.merchant_code = merchant_code;
	}

	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public String getApp_no() {
		return app_no;
	}

	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
