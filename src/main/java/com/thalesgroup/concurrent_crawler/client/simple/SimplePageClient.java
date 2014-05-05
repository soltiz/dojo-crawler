package com.thalesgroup.concurrent_crawler.client.simple;

import java.net.MalformedURLException;
import java.net.URL;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;

public class SimplePageClient extends PageClient {

	@Override
	public Page getPage(URL url) {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
		return new SelfReferencingPage(url);
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
