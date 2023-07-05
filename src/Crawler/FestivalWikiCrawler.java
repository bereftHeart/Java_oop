package Crawler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import datamodel.FestivalEntity;

public class FestivalWikiCrawler extends AbstractCrawler {

	String ROOT_URL = "https://vi.wikipedia.org";
	LinkedList<Object> crawler = new LinkedList<>();
	
//	cac ngay le o Viet Nam
    public void crawl_holidays() {
		String url = "https://vi.wikipedia.org/wiki/Các_ngày_lễ_ở_Việt_Nam";
		Document doc = Wiki.getDocument(url); // connection to wikipedia
		if (doc == null) return;
		
		Element table = doc.select(".wikitable").get(1); // select tables
		if (table ==  null) return;
		
		List<String> headers = Wiki.getTableHeaders(table);
		List<List<Element>> data = Wiki.getTableElements(table);
		
		for (List<Element> row : data) {

			String festivalName = row.get(1).text();	// get festival name
			String festivalDescription = Wiki.getEntityDetails(ROOT_URL + row.get(1).selectFirst("a").attr("href"));	// get festival description
			
			Map<String, String> festivalAdditionalInfo = new HashMap<String, String>();
			festivalAdditionalInfo.put(headers.get(0), row.get(0).text());
			festivalAdditionalInfo.put(headers.get(2), row.get(2).text());
			
			crawler.add(new FestivalEntity(festivalName, festivalAdditionalInfo, festivalDescription, ROOT_URL));
			System.out.println("+1 festival from Wikipedia: " + festivalName);
//			System.out.println(crawler.get(crawler.size()-1).toString());
		}
		
		System.out.println("crawl các ngày lễ ở Việt Nam: done.");
		
    }
//	Le hoi cac dan toc Viet Nam
    public void crawl_fesivalOfEthnicities() {
		String url = "https://vi.wikipedia.org/wiki/Lễ_hội_các_dân_tộc_Việt_Nam";
		Document doc = Wiki.getDocument(url); // connection to wikipedia
		if (doc == null) return;
		
		Elements data = doc.select(".mw-parser-output div ul li");
		for (Element festival : data) {
			String festivalName = festival.text();
			if (festivalName.length() < 4) continue;
			
			Element a_tag = festival.selectFirst("a");
			if (a_tag == null) continue;
			
			String festivalDescription = Wiki.getEntityDetails(ROOT_URL + a_tag.attr("href"));
			Map<String, String> festivalAdditionalInfo = Wiki.getAdditionalInfo(ROOT_URL + a_tag.attr("href"));
			
			if (festivalDescription == "Chưa có thông tin chi tiết" && festivalAdditionalInfo.size() == 0) continue;
			
			crawler.add(new FestivalEntity(festivalName, festivalAdditionalInfo, festivalDescription, ROOT_URL));
			System.out.println("+1 festival from Wikipedia: " + festivalName);
//			System.out.println(crawler.get(crawler.size()-1).toString());
		}
		
		System.out.println("crawl các lễ hội dân tộc ở Việt Nam: done.");
    }
//    
    
    
	@Override
	public void start() throws Exception {
		// TODO Auto-generated method stub
//		crawl_holidays();
//		crawl_fesivalOfEthnicities();
	}
	
//	test
	public static void main(String[] args) throws Exception {
		FestivalWikiCrawler crawler = new FestivalWikiCrawler();
		try {
			crawler.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
