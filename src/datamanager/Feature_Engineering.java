package datamanager;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import constant.constants;
import datamodel.*;

public class Feature_Engineering {
	private Map<String, baseEntity> allEntities;
	
	public Feature_Engineering() {
		allEntities = new HashMap<String, baseEntity>();
		take_from_JSON();
	}

//	filter new entities that overlap with existing entities
	public void filterData(List<baseEntity> BElist) {
		Iterator<baseEntity> i = BElist.iterator();
		while(i.hasNext()) {
			baseEntity newBE = i.next();
			Iterator<String> j = allEntities.keySet().iterator();
			while (j.hasNext()) {
				baseEntity availableBE = allEntities.get(j.next());
				if (availableBE.getName().equals(newBE.getName())) {
					if (newBE.getAdditionalInfo().size() >= availableBE.getAdditionalInfo().size())
						availableBE.setAdditionalInfo(newBE.getAdditionalInfo());
					if (newBE.getDescription().length() > availableBE.getDescription().length())
						availableBE.setDescription(newBE.getDescription());
					i.remove();
					break;
				}
			}
		}
	}
	
//	establish association between entities
	public void linkEntities(List<baseEntity> BElist) {
		for (baseEntity new1_BE: BElist) {
			// Two entities are considered to be related if the name of one appears in the name, 
			// the description, or the additional information of the other
			for (baseEntity new2_BE : BElist) {
				if (new1_BE == new2_BE) continue;
				if (new1_BE.isContained(new2_BE.getName()))
					new1_BE.addRelevent(new2_BE.getId().toString());
			}
			
			for (Map.Entry<String, baseEntity> e : allEntities.entrySet()) {
				if (e.getValue().isContained(new1_BE.getName()))
					e.getValue().addRelevent(new1_BE.getId().toString());
				if (new1_BE.isContained(e.getValue().getName())) {
					new1_BE.addRelevent(e.getKey());
					continue;
				}
				
			}
		}
	}
//	convert from JSON object to entity
	public static baseEntity parseToEntity(JSONObject obj) {
		String id = (String) obj.get(constants.ENTITY_ID);
		String name = (String) obj.get(constants.ENTITY_NAME);
		String description = (String) obj.get(constants.ENTITY_DESCRIPTION);
		String type = (String) obj.get(constants.ENTITY_TYPE);
		String rootURL = (String) obj.get(constants.ENTITY_ROOT_URL);

		// Additional Info
		HashMap<String, String> additionalInfo = new HashMap<String, String>();
		JSONObject addInfo = (JSONObject) obj.get(constants.ENTITY_ADDITIONAL_INFO);
		Iterator<String> itr = addInfo.keySet().iterator();
		while (itr.hasNext()) {
			String key = (String) itr.next();
			String val = (String) addInfo.get(key);
			if (!key.isBlank() && !key.isEmpty()) {
				additionalInfo.put(key, val);
			}
		}

		// relatedEntityIds
		List<String> relatedEntityIds = new LinkedList<String>();
		JSONArray relatedList = (JSONArray) obj.get(constants.ENTITY_RELATED_ENTITY_IDS);
		Iterator<String> iterator = ((List<String>) relatedList).iterator();
		while (iterator.hasNext()) {
			relatedEntityIds.add(iterator.next());
		}

		baseEntity entity;
		if (type.equals(constants.CHARACTER_ENTITY)) {
			entity = new CharacterEntity();
		}
		else if (type.equals(constants.DYNASTY_ENTITY)) {
			entity = new DynastyEntity();
		}
		else if (type.equals(constants.EVENT_ENTITY)) {
			entity = new EventEntity();
		}
		else if (type.equals(constants.FESTIVAL_ENTITY)) {
			entity = new FestivalEntity();
		} 
		else if (type.equals(constants.RELIC_ENTITY)) {
			entity = new RelicEntity();
		} 
		else {
			entity = new baseEntity();
		}
		entity.setId(id);
		entity.setName(name);
		entity.setDescription(description);
		entity.setAdditionalInfo(additionalInfo);
		entity.setRelatedEntityIds(relatedEntityIds);
		entity.setRootURL(rootURL);

		return entity;
	}
//	add new entities after extracting
	public void addEntities(List<baseEntity> newE) {
		filterData(newE);
		linkEntities(newE);
		
		for (baseEntity e : newE)
			this.allEntities.put(e.getId(), e);
	}
	
//	take data from JSON file
	public void take_from_JSON() {
		this.allEntities = new HashMap<String, baseEntity>();
		JSONParser parser = new JSONParser();
		try { 
			Object obj = parser.parse(new FileReader("F:\\\\user\\\\2022-2\\\\oop_\\\\project\\\\OOP_Project\\\\src\\\\JSON\\\\data.json"));
	        JSONObject jsonObject = (JSONObject)obj;
	        
	        JSONArray entities = (JSONArray) jsonObject.get(constants.JSON_ENTITIES); 
	        Iterator<JSONObject> iterator = ((List<JSONObject>) entities).iterator(); 
	        
	        while (iterator.hasNext()) {
	        	JSONObject jObject = iterator.next();
	        	baseEntity e = Feature_Engineering.parseToEntity(jObject);
	        	allEntities.put(e.getId(), e);
	        }
	        
			System.out.println("load data successfully!");
	    } catch (Exception e) { 
	        e.printStackTrace();
	    }
	}
	
//	save data to JSON
	public void save_to_JSON() throws JSONException {
		try {
			JSONArray jsArr = new JSONArray();
			
			for (Map.Entry<String, baseEntity> entry: allEntities.entrySet()) 
				jsArr.put(entry.getValue().toJsonObject());
				
//			FileWriter file = new FileWriter("../JSON/data.json",false);
			FileWriter file = new FileWriter("F:\\user\\2022-2\\oop_\\project\\OOP_Project\\src\\JSON\\data.json",false);
			file.write(jsArr.toString());
			file.close();
			System.out.println("\n File saved successfully");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
