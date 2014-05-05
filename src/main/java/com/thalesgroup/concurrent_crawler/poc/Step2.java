package com.thalesgroup.concurrent_crawler.poc;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.thalesgroup.concurrent_crawler.client.SimplePageClient;

public class Step2 {

	public static void main(String[] args) {
		SimplePageClient pageClient = new SimplePageClient();
		Crawler crawler = new Crawler(pageClient);
		crawler.addPageToIndex(pageClient.getRootPageUrl());
		crawler.addSubPagesToIndex(pageClient.getRootPageUrl());
		Map<String, List<URL>> result = crawler.getTopTenWords();
		for (Map.Entry<String, List<URL>> e : result.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue().size());
		}
	}
}
