package com.thalesgroup.concurrent_crawler.poc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.client.simple.SimplePageClient;
import com.thalesgroup.concurrent_crawler.poc.crawler.Crawler;
import com.thalesgroup.concurrent_crawler.poc.crawler.NonThreadedCrawler;
import com.thalesgroup.concurrent_crawler.poc.indexer.Indexer;
import com.thalesgroup.concurrent_crawler.poc.indexer.NonConcurrentIndexer;

public class Step1 {

    public static Indexer indexPage(URL url) throws IOException {
        PageClient pageClient = new SimplePageClient();
        Indexer indexer = new NonConcurrentIndexer();
        Crawler crawler = new NonThreadedCrawler(pageClient, indexer);
        crawler.addPageToIndex(url);
        return indexer;
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        final URL rootUrl = new URL("http://www.google.fr/");
        Map<String, List<URL>> result = indexPage(rootUrl).getTopTenWords();
        for (Map.Entry<String, List<URL>> e : result.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue().size());
        }
    }
}
