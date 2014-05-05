package com.thalesgroup.concurrent_crawler.poc;

import java.net.URL;
import java.util.List;
import java.util.Map;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.client.advanced.AdvancedPageClient;

public class StepAdvanced {

	public static void main(String[] args) throws InterruptedException {
		PageClient pageClient = new AdvancedPageClient();
		Indexer indexer = new Indexer();
		Crawler crawler = new Crawler(pageClient, indexer);
		crawler.addPageToIndex(pageClient.getRootUrl());
		crawler.addSubPagesToIndex(pageClient.getRootUrl());
		Map<String, List<URL>> result = indexer.getTopWords(10);
		for (Map.Entry<String, List<URL>> e : result.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue().size());
		}
	}
}
