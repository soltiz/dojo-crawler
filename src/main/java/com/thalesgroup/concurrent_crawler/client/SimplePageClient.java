package com.thalesgroup.concurrent_crawler.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SimplePageClient extends PageClient {

	@Override
	public String getPageContent(URL page) {
		return "titi toto tata titi";
	}

	@Override
	public List<URL> getPageLinks(URL page) {
		return new ArrayList<>();
	}

}
