package com.thalesgroup.concurrent_crawler.poc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.thalesgroup.concurrent_crawler.client.PageClient;

public class Crawler {

	private Map<String, List<URL>> index = new ConcurrentHashMap<>();
	private PageClient client;

	static private final int NB_THREADS = 100;
	
	public Crawler(PageClient client) {
		this.client = client;
	}
	
	public void addPageToIndex(URL url) {
		String pageContent = client.getPageContent(url);
		String[] wordList = pageContent.split("\\s");
		for (String word : wordList) {
			if (!index.containsKey(word)) {
				index.put(word, Collections.synchronizedList(new LinkedList<URL>()));
			}
			index.get(word).add(url);
		}
	}
	
	public void addSubPagesToIndex(URL url) {
		List<URL> urls = client.getPageLinks(url);
		long start = System.currentTimeMillis();
		ExecutorService executors = Executors.newFixedThreadPool(NB_THREADS);
		for (final URL u: urls) {
			executors.execute(new Runnable() {
				@Override
				public void run() {
					addPageToIndex(u);
				}
				
			});
			
		}
		executors.shutdown();
		try {
			boolean done = executors.awaitTermination(20, TimeUnit.SECONDS);
			if (!done) {
				throw new RuntimeException("not done");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = System.currentTimeMillis() - start;
		System.out.println(((double)time) / 1000);
	}
	
	/**
	 * Retourne les 10 mots les plus utilisés, et les URL qui les contiennent
	 * 
	 * @return
	 */
	public Map<String, List<URL>> getTopTenWords() {
		// Compute 10 top words
		List<WordOccurences> wordsOccurences = new ArrayList<>();
		for (Map.Entry<String, List<URL>> e : index.entrySet()) {
			wordsOccurences.add(new WordOccurences(e.getKey(), e.getValue()));
		}
		Collections.sort(wordsOccurences);
		// Build real result
		Map<String, List<URL>> result = new HashMap<>();
		int nbResults = Math.min(9, wordsOccurences.size());
		for (WordOccurences wo : wordsOccurences.subList(0, nbResults)) {
			result.put(wo.word, index.get(wo.word));
		}
		return result;

	}


}
