package datamodel;

import java.util.Map;

import constant.constants;

public class EventEntity extends baseEntity {

	public EventEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventEntity(String name, Map<String, String> additionalInfo, String description ) {
		super(name, additionalInfo, description);
	}
	public EventEntity(String name, Map<String, String> additionalInfo, String description, String url ) {
		super(name, additionalInfo, description);
		this.setRootURL(url);
	}
	
	public String getType() {
		return constants.EVENT_ENTITY;
	}

}
