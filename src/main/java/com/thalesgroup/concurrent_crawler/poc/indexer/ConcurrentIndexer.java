package com.thalesgroup.concurrent_crawler.poc.indexer;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentIndexer extends Indexer {

	public ConcurrentIndexer() {
		super(new ConcurrentHashMap<String, List<URL>>());
	}

	@Override
	public void addWord(String word, URL pageURL) {
		final Map<String, List<URL>> index = getIndex();
		if (!index.containsKey(word)) {
			index.put(word, Collections.synchronizedList(new LinkedList<URL>()));
		}
		index.get(word).add(pageURL);
	}

}
