package datamodel;

import java.util.List;
import java.util.Map;

public class EventEntity extends baseEntity {

	public EventEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventEntity(String name, Map<String, String> additionalInfo) {
		super(name, additionalInfo);
		// TODO Auto-generated constructor stub
	}

	public EventEntity(String name, String description, Map<String, String> additionalInfo,
			List<String> relatedEntityIds, String rootURL) {
		super(name, description, additionalInfo, relatedEntityIds, rootURL);
		// TODO Auto-generated constructor stub
	}

	public EventEntity(String name, String description, Map<String, String> additionalInfo) {
		super(name, description, additionalInfo);
		// TODO Auto-generated constructor stub
	}

}
