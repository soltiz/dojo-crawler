package com.thalesgroup.concurrent_crawler.poc;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.thalesgroup.concurrent_crawler.client.simple.SimplePageClient;

public class Step1 {

	public static void main(String[] args) {
		SimplePageClient pageClient = new SimplePageClient();
		Indexer indexer = new Indexer();
		Crawler crawler = new Crawler(pageClient, indexer);
		crawler.addPageToIndex(pageClient.getRootUrl());
		Map<String, List<URL>> result = indexer.getTopTenWords();
		for (Map.Entry<String, List<URL>> e : result.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue().size());
		}
	}
}
