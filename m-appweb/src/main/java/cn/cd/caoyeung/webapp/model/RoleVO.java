package cn.cd.caoyeung.webapp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RoleVO implements Serializable{

	private String roleId;
	
	private String labelCn;
	
	private String relatedDistrictCuid;
	
	private String type;
	
	private String remark;
	
	private String specialtyCode;
	
	private String resType;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRelatedDistrictCuid() {
		return relatedDistrictCuid;
	}

	public void setRelatedDistrictCuid(String relatedDistrictCuid) {
		this.relatedDistrictCuid = relatedDistrictCuid;
	}

	public String getLabelCn() {
		return labelCn;
	}

	public void setLabelCn(String labelCn) {
		this.labelCn = labelCn;
	}

	private Map<String, ResVO> resMap = new HashMap<String, ResVO>();

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Map<String, ResVO> getResMap() {
		return resMap;
	}

	public void setResMap(Map<String, ResVO> resMap) {
		this.resMap = resMap;
	}

	public String getSpecialtyCode() {
		return specialtyCode;
	}

	public void setSpecialtyCode(String specialtyCode) {
		this.specialtyCode = specialtyCode;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}
	
}
