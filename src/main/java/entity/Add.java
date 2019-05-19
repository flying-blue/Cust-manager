package entity;

/**
 * ∑÷µÍ–≈œ¢
 * @author Xu_fei
 *
 */
public class Add {
	
	private String addNo;
	private String addName;
	private Integer sortNo;
	private String addMaster;
	private String addContent;
	public String getAddNo() {
		return addNo;
	}
	public void setAddNo(String addNo) {
		this.addNo = addNo;
	}
	public String getAddName() {
		return addName;
	}
	public void setAddName(String addName) {
		this.addName = addName;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	public String getAddMaster() {
		return addMaster;
	}
	public void setAddMaster(String addMaster) {
		this.addMaster = addMaster;
	}
	public String getAddContent() {
		return addContent;
	}
	public void setAddContent(String addContent) {
		this.addContent = addContent;
	}
	@Override
	public String toString() {
		return "Add [addNo=" + addNo + ", addName=" + addName + ", sortNo=" + sortNo + ", addMaster=" + addMaster
				+ ", addContent=" + addContent + "]";
	}
	

}
