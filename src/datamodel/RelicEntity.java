package datamodel;

import java.util.List;
import java.util.Map;

public class RelicEntity extends baseEntity {

	public RelicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RelicEntity(String name, Map<String, String> additionalInfo) {
		super(name, additionalInfo);
		// TODO Auto-generated constructor stub
	}

	public RelicEntity(String name, String description, Map<String, String> additionalInfo,
			List<String> relatedEntityIds, String rootURL) {
		super(name, description, additionalInfo, relatedEntityIds, rootURL);
		// TODO Auto-generated constructor stub
	}

	public RelicEntity(String name, String description, Map<String, String> additionalInfo) {
		super(name, description, additionalInfo);
		// TODO Auto-generated constructor stub
	}

}
