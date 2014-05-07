package com.thalesgroup.concurrent_crawler.poc.crawler;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.poc.indexer.Indexer;

public abstract class Crawler {

	private Indexer indexer;
	private PageClient client;

	protected Crawler(PageClient client, Indexer indexer) {
        this.client = client;
        this.indexer = indexer;
    }

	public void addPageToIndex(URL url) throws IOException {
	    final String pageContent = client.getPage(url).getText();
	    indexer.addText(pageContent, url);
	}

	public void addSubPagesToIndex(URL url) throws IOException {
		List<URL> urls = client.getPage(url).getLinks();
		long start = System.currentTimeMillis();
		addPagesToIndex(urls);
		long time = System.currentTimeMillis() - start;
		System.out.println(((double) time) / 1000);
	}
	
	protected abstract void addPagesToIndex(List<URL> urls) throws IOException;
}