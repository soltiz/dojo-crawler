package com.thalesgroup.concurrent_crawler.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public abstract class PageClient {

	abstract public String getPageContent(URL page);
	
	abstract public List<URL> getPageLinks(URL page);
	
	public final URL getRootPageUrl() {
		try {
			return new URL("/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException("bad root url");
		}
	}
}
