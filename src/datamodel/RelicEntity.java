package datamodel;

import java.util.Map;

import constant.constants;

public class RelicEntity extends baseEntity {

	public RelicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RelicEntity(String name, Map<String, String> additionalInfo, String description ) {
		super(name, additionalInfo, description);
	}
	public RelicEntity(String name, Map<String, String> additionalInfo, String description, String url ) {
		super(name, additionalInfo, description);
		setRootURL(url);
	}
	public String getType() {
		return constants.RELIC_ENTITY;
	}

	@Override
	public String toString() {
		return "RelicEntity [id=" + id + ", name=" + name + ", description=" + description + ", additionalInfo="
				+ additionalInfo + ", relatedEntityIds=" + relatedEntityIds + ", rootURL=" + rootURL + "]";
	}
}
