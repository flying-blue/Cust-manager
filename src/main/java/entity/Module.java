package entity;


public class Module {

	private Integer moduleId;
	private String name;

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", name=" + name + "]";
	}

}
