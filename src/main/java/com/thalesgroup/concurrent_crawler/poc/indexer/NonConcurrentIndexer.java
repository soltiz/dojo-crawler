package com.thalesgroup.concurrent_crawler.poc.indexer;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NonConcurrentIndexer extends Indexer {

	public NonConcurrentIndexer() {
		super(new HashMap<String, List<URL>>());
	}

    @Override
	public void addWord(String word, URL pageURL) {
		final Map<String, List<URL>> index = getIndex();
		if (!index.containsKey(word)) {
            index.put(word, new LinkedList<URL>());
        }
        index.get(word).add(pageURL);
    }

}
