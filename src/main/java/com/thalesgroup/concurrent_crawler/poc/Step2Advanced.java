package com.thalesgroup.concurrent_crawler.poc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.client.advanced.AdvancedPageClient;
import com.thalesgroup.concurrent_crawler.poc.crawler.Crawler;
import com.thalesgroup.concurrent_crawler.poc.crawler.MultiThreadedCrawler;
import com.thalesgroup.concurrent_crawler.poc.indexer.ConcurrentIndexer;
import com.thalesgroup.concurrent_crawler.poc.indexer.Indexer;

public class Step2Advanced {

    public static void main(String[] args) throws MalformedURLException, IOException {
        final URL rootUrl = new URL("http://www.google.fr/");
        PageClient pageClient = new AdvancedPageClient();
        Indexer indexer = new ConcurrentIndexer();
        Crawler crawler = new MultiThreadedCrawler(pageClient, indexer);
        crawler.addPageToIndex(rootUrl);
        crawler.addSubPagesToIndex(rootUrl);
        indexer.printTopTenWords();
    }
}
