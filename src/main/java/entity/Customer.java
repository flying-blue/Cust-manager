package entity;

import java.util.Date;

public class Customer {
	private String custId;
	private String name;
	private String tel;
	private Integer age;
	private String sex;
	private String addr;
	private String kbn;
	private String bodyInfo;
	private Date appendDate;
	private Date updateDate;
	private String appendUserId;
	private String content;
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getKbn() {
		return kbn;
	}
	public void setKbn(String kbn) {
		this.kbn = kbn;
	}
	public String getBodyInfo() {
		return bodyInfo;
	}
	public void setBodyInfo(String bodyInfo) {
		this.bodyInfo = bodyInfo;
	}
	public Date getAppendDate() {
		return appendDate;
	}
	public void setAppendDate(Date appendDate) {
		this.appendDate = appendDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getAppendUserId() {
		return appendUserId;
	}
	public void setAppendUserId(String appendUserId) {
		this.appendUserId = appendUserId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", name=" + name + ", tel=" + tel + ", age=" + age + ", sex=" + sex
				+ ", addr=" + addr + ", kbn=" + kbn + ", bodyInfo=" + bodyInfo + ", appendDate=" + appendDate
				+ ", updateDate=" + updateDate + ", appendUserId=" + appendUserId + ", content=" + content + "]";
	}

}
