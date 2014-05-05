package com.thalesgroup.concurrent_crawler.client;

import java.net.URL;

public abstract class PageClient {

	/**
	 * Entry point
	 */
	abstract public URL getRootUrl();

	/**
	 * Download a page : this may takes some time
	 */
	abstract public Page getPage(URL url);

}
