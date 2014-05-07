package com.thalesgroup.concurrent_crawler.poc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.client.simple.SimplePageClient;
import com.thalesgroup.concurrent_crawler.poc.crawler.Crawler;
import com.thalesgroup.concurrent_crawler.poc.crawler.MultiThreadedCrawler;
import com.thalesgroup.concurrent_crawler.poc.indexer.Indexer;
import com.thalesgroup.concurrent_crawler.poc.indexer.NonConcurrentIndexer;

public class Step2NonConcurrent {

    public static void main(String[] args) throws MalformedURLException, IOException {
        final URL rootUrl = new URL("http://www.google.fr/");
        PageClient pageClient = new SimplePageClient();
        Indexer indexer = new NonConcurrentIndexer();
        Crawler crawler = new MultiThreadedCrawler(pageClient, indexer);
        crawler.addPageToIndex(rootUrl);
        crawler.addSubPagesToIndex(rootUrl);
        indexer.printTopTenWords();
    }
}
