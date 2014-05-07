package com.thalesgroup.concurrent_crawler.poc.crawler;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.poc.indexer.Indexer;

public class NonThreadedCrawler extends Crawler {

	public NonThreadedCrawler(PageClient client, Indexer indexer) {
		super(client, indexer);
	}

	@Override
	public void addPagesToIndex(List<URL> urls) throws IOException {
		for (final URL u : urls) {
			try {
				addPageToIndex(u);
			} catch (IOException ex) {
				System.err.println("can't load page" + ex);
			}
		}
	}

}
