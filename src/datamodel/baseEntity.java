package datamodel;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import constant.constants;

public class baseEntity {
	protected String id;
	protected String name;
	protected String description;
	protected Map<String, String> additionalInfo;
	protected List<String> relatedEntityIds;
	protected String rootURL;
	
//	getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(Map<String, String> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public List<String> getRelatedEntityIds() {
		return relatedEntityIds;
	}

	public void setRelatedEntityIds(List<String> relatedEntityIds) {
		this.relatedEntityIds = relatedEntityIds;
	}

	public String getRootURL() {
		return rootURL;
	}

	public void setRootURL(String rootURL) {
		this.rootURL = rootURL;
	}

	public String getType() {
		return constants.BASE_ENTITY;
	}
//	constructor
	public baseEntity() {
		this.id=UUID.randomUUID().toString();
		this.additionalInfo= new HashMap<String, String>();
		this.relatedEntityIds = new LinkedList<String>();
		this.description = "none";
	}

	public baseEntity(String name, Map<String, String> additionalInfo) {
		this();
		
		this.name = name;
		this.additionalInfo= additionalInfo;
	}
	public baseEntity(String name, Map<String, String> additionalInfo, String description ) {
		this();
		
		this.description = description;	
		this.name = name;
		this.additionalInfo= additionalInfo;
	}
//	convert to JSONobject
	public JSONObject toJsonObject() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(constants.ENTITY_ID,this.getId());
		jsonObject.put(constants.ENTITY_NAME,this.getName());
		jsonObject.put(constants.ENTITY_DESCRIPTION,this.getDescription());
		jsonObject.put(constants.ENTITY_ADDITIONAL_INFO,this.getAdditionalInfo());
		jsonObject.put(constants.ENTITY_RELATED_ENTITY_IDS,this.getRelatedEntityIds());
		jsonObject.put(constants.ENTITY_TYPE,this.getType());
		jsonObject.put(constants.ENTITY_ROOT_URL,this.getRootURL());
		return jsonObject;
	}
//	add relevent
	public void addRelevent(String id) {
		if(!this.relatedEntityIds.contains(id))
			this.relatedEntityIds.add(id);
	}

// paternity test 
	public boolean isContained(String text) {
		return this.getName().contains(text) || this.getAdditionalInfo().values().toString().contains(text) || this.getDescription().contains(text);  
	}
}
