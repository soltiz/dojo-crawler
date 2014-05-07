package com.thalesgroup.concurrent_crawler.poc;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.client.advanced.AdvancedPageClient;
import com.thalesgroup.concurrent_crawler.poc.crawler.Crawler;
import com.thalesgroup.concurrent_crawler.poc.crawler.MultiThreadedCrawler;
import com.thalesgroup.concurrent_crawler.poc.indexer.ConcurrentIndexer;
import com.thalesgroup.concurrent_crawler.poc.indexer.Indexer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Step2Advanced {

    public static Indexer indexPages(URL url) throws IOException {
        PageClient pageClient = new AdvancedPageClient();
        Indexer indexer = new ConcurrentIndexer();
        Crawler crawler = new MultiThreadedCrawler(pageClient, indexer);
        crawler.addPageToIndex(url);
        crawler.addSubPagesToIndex(url);
        return indexer;
    }

    public static void main(String[] args) throws MalformedURLException, IOException {
        final URL rootUrl = new URL("http://www.google.fr/");
        Map<String, List<URL>> result = indexPages(rootUrl).getTopWords(10);
        for (Map.Entry<String, List<URL>> e : result.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue().size());
        }
    }
}
