package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Cost implements Serializable {

	private Integer costId;
	private String name;
	//����ʱ��:�ײ��ڰ�����ʱ��
	private Integer baseDuration;
	//��������:�ײ͵ĳ�ʼ����
	private Double baseCost;
	//��λ����:�����ײ͵Ĳ���ÿ���ӵķ���
	private Double unitCost;
	//״̬:0-��ͨ��1-��ͣ��
	private String status;
	//�ʷ�˵��
	private String descr;
	//����ʱ��
	private Timestamp creatime;
	//��ͨʱ��
	private Timestamp startime;
	//�ʷ�����:1-���£�2-�ײͣ�3-��ʱ
	private String costType;
	
	
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBaseDuration() {
		return baseDuration;
	}
	public void setBaseDuration(Integer baseDuration) {
		this.baseDuration = baseDuration;
	}
	public Double getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(Double baseCost) {
		this.baseCost = baseCost;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public Timestamp getStartime() {
		return startime;
	}
	public void setStartime(Timestamp startime) {
		this.startime = startime;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	@Override
	public String toString() {
		return "Cost [costId=" + costId + ", name=" + name + ", baseDuration=" + baseDuration + ", baseCost=" + baseCost
				+ ", unitCost=" + unitCost + ", status=" + status + ", descr=" + descr + ", creatime=" + creatime
				+ ", startime=" + startime + ", costType=" + costType + "]";
	}
	
	
	
	
	
	
	
}
