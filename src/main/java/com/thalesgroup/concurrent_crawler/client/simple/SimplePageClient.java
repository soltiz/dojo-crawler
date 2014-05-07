package com.thalesgroup.concurrent_crawler.client.simple;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;
import java.io.IOException;
import java.net.URL;

public class SimplePageClient extends PageClient {

    @Override
    public Page getPage(URL url) throws IOException {
    	return new SelfReferencingPage(url);
    }
}
