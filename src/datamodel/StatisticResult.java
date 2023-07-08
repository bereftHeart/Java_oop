package datamodel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import org.json.simple.JSONObject;
//import constant.constants;

public class StatisticResult {
	private Map<String,ArrayList<String>> totalInfo;
	private Map<String, HashMap<String, Integer>> siteInfo;
	private Integer countLinks;
	private String updateTime;
	
	public StatisticResult() { 
		this.updateTime = "";
		this.totalInfo = new HashMap<String, ArrayList<String>>();
		this.siteInfo = new HashMap<String, HashMap<String, Integer>>();
		this.countLinks = 0;
	}
	public StatisticResult(Map<String,ArrayList<String>> totalInfo,Map<String, HashMap<String, Integer>> siteInfo,Integer countLinks ) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();   
		this.updateTime = dtf.format(now).toString();
		this.totalInfo = totalInfo;
		this.siteInfo = siteInfo;
		this.countLinks = countLinks;
	}
	public StatisticResult(String updateTime,Map<String,ArrayList<String>> totalInfo,Map<String, HashMap<String, Integer>> siteInfo,Integer countLinks ) {  
		this.updateTime = updateTime;
		this.totalInfo = totalInfo;
		this.siteInfo = siteInfo;
		this.countLinks = countLinks;
	}

	public Map<String,ArrayList<String>> getTotalInfo(){
		return this.totalInfo;
	}
	public Map<String, HashMap<String, Integer>> getSiteInfo(){
		return this.siteInfo;
	}
	public Integer getCountLinks(){
		return this.countLinks;
	}
	public String getUpdateTime() {
		return this.updateTime;
	}
	
	//utility
//	public JSONObject ToJsonObject() {
//		JSONObject jEntity = new JSONObject();
//		jEntity.put(constants.UPDATE_TIME, this.getUpdateTime());
//		jEntity.put(constants.TOTAL_INFO, this.getTotalInfo());
//		jEntity.put(constants.SITE_INFO, this.getSiteInfo());
//		jEntity.put(constants.COUNT_LINKS, this.getCountLinks());
//		return jEntity;
//	}
}
