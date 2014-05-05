package com.thalesgroup.concurrent_crawler.client.advanced;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;

public class AdvancedPageClient extends PageClient {

	private static final int MAX_DELAY_MILLIS = 40;

	@Override
	public Page getPage(URL url) {
		Random r = new Random(url.hashCode());
		try {
			Thread.sleep(r.nextInt(MAX_DELAY_MILLIS));
		} catch (InterruptedException e) {
		}
		return new RandomPage(url);
	}

	@Override
	public final URL getRootUrl() {
		try {
			return new URL("file://");
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("bad root url");
		}
	}
}
