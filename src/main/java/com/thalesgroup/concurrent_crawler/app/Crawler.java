package com.thalesgroup.concurrent_crawler.app;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;

public class Crawler {

	public List<Word> crawlPageOne(URL url, PageClient client) throws IOException  {
		TopTen topTen = new TopTen();
		
		Page page = client.getPage(url);
		
		//Page même
		topTen.addPage(page);
		
		List<URL> lstUrl = page.getLinks();
		
		for (URL childUrl : lstUrl) {
			Page childPage = client.getPage(childUrl);
			topTen.addPage(childPage);
		}
		return topTen.getTopTen();
	}
	
}
