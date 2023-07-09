package datamodel;

import java.util.Map;

import constant.constants;

public class DynastyEntity extends baseEntity {

	public DynastyEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DynastyEntity(String name, Map<String, String> additionalInfo, String description ) {
		super(name, additionalInfo, description);
	}
	public DynastyEntity(String name, Map<String, String> additionalInfo, String description, String url ) {
		super(name, additionalInfo, description);
		setRootURL(url);
	}
	public String getType() {
		return constants.DYNASTY_ENTITY;
	}

	@Override
	public String toString() {
		return "DynastyEntity [id=" + id + ", name=" + name + ", description=" + description + ", additionalInfo="
				+ additionalInfo + ", relatedEntityIds=" + relatedEntityIds + ", rootURL=" + rootURL + "]";
	}
	

}
