package entity;

import java.util.Date;

public class Cust {
	private String custId;
	private Integer conNum;
	private String name;
	private String tel;
	private String sex;
	private String kbn;
	private Integer consum;
	private Date consumDate;
	private Date appendDate;
	private Date updateDate;
	private String appendUserId;
	private String content;
	private String serUser;
	private String consumKbn;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public Integer getConNum() {
		return conNum;
	}
	public void setConNum(Integer conNum) {
		this.conNum = conNum;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getKbn() {
		return kbn;
	}
	public void setKbn(String kbn) {
		this.kbn = kbn;
	}
	public Integer getConsum() {
		return consum;
	}
	public void setConsum(Integer consum) {
		this.consum = consum;
	}
	public Date getConsumDate() {
		return consumDate;
	}
	public void setConsumDate(Date consumDate) {
		this.consumDate = consumDate;
	}
	public Date getAppendDate() {
		return appendDate;
	}
	public void setAppendDate(Date date) {
		this.appendDate = date;
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
	public String getSerUser() {
		return serUser;
	}
	public void setSerUser(String serUser) {
		this.serUser = serUser;
	}
	
	public String getConsumKbn() {
		return consumKbn;
	}
	public void setConsumKbn(String consumKbn) {
		this.consumKbn = consumKbn;
	}
	@Override
	public String toString() {
		return "Cust [custId=" + custId + ", conNum=" + conNum + ", name=" + name + ", tel=" + tel + ", sex=" + sex
				+ ", kbn=" + kbn + ", consum=" + consum + ", consumDate=" + consumDate + ", appendDate=" + appendDate
				+ ", updateDate=" + updateDate + ", appendUserId=" + appendUserId + ", content=" + content
				+ ", serUser=" + serUser + ", consumKbn=" + consumKbn + "]";
	}

}
