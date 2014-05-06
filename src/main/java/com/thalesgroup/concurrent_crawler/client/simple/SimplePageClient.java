package com.thalesgroup.concurrent_crawler.client.simple;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;
import java.io.IOException;
import java.net.URL;

public class SimplePageClient extends PageClient {

    private static final int DELAY_MILLIS = 20;

    @Override
    public Page getPage(URL url) throws IOException {
        try {
            Thread.sleep(DELAY_MILLIS);
        } catch (InterruptedException ex) {
            throw new IOException("interrupted", ex);
        }
        return new SelfReferencingPage(url);
    }
}
