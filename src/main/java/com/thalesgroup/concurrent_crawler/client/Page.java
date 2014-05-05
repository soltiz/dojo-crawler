package com.thalesgroup.concurrent_crawler.client;

import java.net.URL;
import java.util.List;

abstract public class Page {

	/**
	 * Get the text content of the pages
	 * 
	 * @return words
	 */
	abstract public String getContent();

	/**
	 * Get the links present in the pages
	 * 
	 * @return links to other pages
	 */
	abstract public List<URL> getLinks();

}
