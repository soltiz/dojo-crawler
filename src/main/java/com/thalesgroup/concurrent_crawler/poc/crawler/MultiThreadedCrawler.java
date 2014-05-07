package com.thalesgroup.concurrent_crawler.poc.crawler;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.poc.indexer.Indexer;

public class MultiThreadedCrawler extends Crawler {

	private static final int NB_THREADS = 100;
	private static final int TIMEOUT_SECONDS = 20;

	public MultiThreadedCrawler(PageClient client, Indexer indexer) {
		super(client, indexer);
	}

	@Override
	public void addPagesToIndex(List<URL> urls) throws IOException {
		ExecutorService executors = Executors.newFixedThreadPool(NB_THREADS);
		for (final URL u : urls) {
			executors.execute(new Runnable() {
				@Override
				public void run() {
					try {
						addPageToIndex(u);
					} catch (IOException ex) {
						System.err.println("can't load page" + ex);
					}
				}
			});

		}
		executors.shutdown();
		try {
			boolean done = executors.awaitTermination(TIMEOUT_SECONDS,
					TimeUnit.SECONDS);
			if (!done) {
				throw new RuntimeException("not done");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
