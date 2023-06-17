package Crawler;
import java.util.List;

import datamodel.baseEntity;
public abstract class AbstractCrawler {
	public abstract void start() throws Exception;
	public List<baseEntity> dataCrawler;
	public List<baseEntity> getCrawledData(){
		return this.dataCrawler;
	};
}
