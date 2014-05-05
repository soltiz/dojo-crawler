package com.thalesgroup.concurrent_crawler.client.advanced;

import java.net.URL;
import java.util.Random;
import java.io.IOException;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.PageClient;

public class AdvancedPageClient extends PageClient {

    private static final int MIN_DELAY_MILLIS = 20;
    private static final int MAX_DELAY_MILLIS = 50;

    @Override
    public Page getPage(URL url) throws IOException {
        Random r = new Random(url.toString().hashCode());
        try {
            Thread.sleep(MIN_DELAY_MILLIS + r.nextInt(MAX_DELAY_MILLIS - MIN_DELAY_MILLIS));
        } catch (InterruptedException ex) {
            throw new IOException("interrupted", ex);
        }
        return new RandomPage(url);
    }
}