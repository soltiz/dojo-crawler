package com.thalesgroup.concurrent_crawler.app;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;

public class CrawlerMonoPage implements Callable<List <URL>> {

	private final PageClient client;
	private final URL childUrl;
	private final TopTen topTen;
	
	public CrawlerMonoPage(final PageClient client, final URL childUrl, final TopTen topTen) {
		this.client = client;
		this.childUrl = childUrl;
		this.topTen = topTen;
	}

//	@Override
//	public void run() {
//		try {
//			final Page childPage = client.getPage(childUrl);
//			topTen.addPage(childPage);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public List<URL> call() throws Exception {
		try {
			final Page childPage = client.getPage(childUrl);
			topTen.addPage(childPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
