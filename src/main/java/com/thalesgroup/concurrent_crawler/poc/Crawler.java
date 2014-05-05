package com.thalesgroup.concurrent_crawler.poc;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.thalesgroup.concurrent_crawler.client.PageClient;

public class Crawler {

	private PageClient client;
	private Indexer indexer;

	static private final int NB_THREADS = 100;
	static private final int TIMEOUT_SECONDS = 20;

	public Crawler(PageClient client, Indexer indexer) {
		this.client = client;
		this.indexer = indexer;
	}

	public void addPageToIndex(URL url) {
		final String pageContent = client.getPage(url).getContent();
		indexer.addWordsToIndex(pageContent, url);
	}

	public void addSubPagesToIndex(URL url) {
		List<URL> urls = client.getPage(url).getLinks();
		long start = System.currentTimeMillis();
		ExecutorService executors = Executors.newFixedThreadPool(NB_THREADS);
		for (final URL u : urls) {
			executors.execute(new Runnable() {
				@Override
				public void run() {
					addPageToIndex(u);
				}
			});

		}
		executors.shutdown();
		try {
			boolean done = executors.awaitTermination(TIMEOUT_SECONDS, TimeUnit.SECONDS);
			if (!done) {
				throw new RuntimeException("not done");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = System.currentTimeMillis() - start;
		System.out.println(((double) time) / 1000);
	}

}
