package datamanager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import datamodel.baseEntity;

public class Feature_Engineering {
	private Map<String, baseEntity> allEntities;
	
	
	
public Feature_Engineering() {
		allEntities = new HashMap<String, baseEntity>();
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
//	add new entities after extracting
	public void addEntities(List<baseEntity> newE) {
		filterData(newE);
		linkEntities(newE);
		
		for (baseEntity e : newE)
			this.allEntities.put(e.getId(), e);
	}
	
//	save data to JSON
	public void save_to_JSON() throws JSONException {
		try {
			JSONArray jsArr = new JSONArray();
			
			for (Map.Entry<String, baseEntity> entry: allEntities.entrySet()) 
				jsArr.put(entry.getValue().toJsonObject());
				
//			FileWriter file = new FileWriter("./json/data.json",false);
			FileWriter file = new FileWriter("F:\\user\\2022-2\\oop_\\project\\OOP_Project\\src\\json\\data.json",false);
			file.write(jsArr.toString());
			file.close();
			System.out.println("\n File saved successfully");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
