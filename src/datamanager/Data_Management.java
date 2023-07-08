package datamanager;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import Crawler.AbstractCrawler;
import Crawler.FestivalWikiCrawler;

public class Data_Management {
	List<AbstractCrawler> web_crawlers;
	Feature_Engineering feature_extractor;
	
	public Feature_Engineering getData() {
		return this.feature_extractor;
	}

	public Data_Management() {
		super();
		this.web_crawlers = new ArrayList<AbstractCrawler>();
		this.feature_extractor = new Feature_Engineering();
	}

	public void add_web_crawler() {
		this.web_crawlers.add(new FestivalWikiCrawler());
//		this.web_crawlers.add(new RelicnguoikesuCrawler());
//		this.web_crawlers.add(new RelicWikiCrawler());
	}
	
	public void crawl_raw_data() throws JSONException {
		add_web_crawler();
		for (AbstractCrawler crawler : web_crawlers) {
			try {
				crawler.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			feature_extractor.addEntities(crawler.getDataCrawler());
		}
		
		feature_extractor.save_to_JSON();
	}
	
//	test
	public static void main(String[] args) throws JSONException {
		
		Data_Management dm = new Data_Management();
		dm.crawl_raw_data();
	}
}

