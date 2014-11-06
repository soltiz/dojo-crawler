package com.thalesgroup.concurrent_crawler.app;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;

public class Crawler {

	static final int THREAD_NUMBER = 100;
	final ExecutorService crawlerExecutorService = Executors
			.newFixedThreadPool(THREAD_NUMBER);

	public List<Word> crawlPageOne(URL url, final PageClient client)
			throws IOException {
		return crawlPages(url, client, 1);
	}

	public List<Word> crawlPages(URL url, final PageClient client, int depth)
			throws IOException {
		final TopTen topTen = new TopTen();

		Page page = client.getPage(url);

		// Page même
		topTen.addPage(page);

		List<URL> lstUrl = page.getLinks();

		try {

			List<Future> futures = new ArrayList<>();
			for (final URL childUrl : lstUrl) {
				Future<List<URL>> futureUrlsList = crawlerExecutorService
						.submit(new CrawlerMonoPage(client, childUrl, topTen));
				futureUrlsList.get(10, TimeUnit.SECONDS);
			}
			crawlerExecutorService.shutdown();
			crawlerExecutorService.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

		return topTen.getTopTen();
	}

	public void crawlPageRecursive(List<URL> listOfUrls, PageClient client,
			final TopTen topTen, int depth, int currentDepth)
			throws IOException {

		try {
			for (final URL childUrl : listOfUrls) {
				Future<List<URL>> futureUrlsList = crawlerExecutorService
						.submit(new CrawlerMonoPage(client, childUrl, topTen));
				futureUrlsList.get(10, TimeUnit.SECONDS);
			}

			if (currentDepth > depth) {
				throw new UnsupportedOperationException();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} 
	}

}
