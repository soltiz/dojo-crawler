package com.thalesgroup.concurrent_crawler.poc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thalesgroup.concurrent_crawler.client.PageClient;
import com.thalesgroup.concurrent_crawler.client.SimplePageClient;

public class Crawler {

	/**
	 * Retourne les 10 mots les plus utilisés, et les URL qui les contiennent
	 * @return
	 */
	static public Map<String,List<URL>> getTopTenWords(PageClient client) {
		Map<String, List<URL>> index = new HashMap<>();
		URL rootPageUrl = client.getRootPageUrl();
		String pageContent = client.getPageContent(rootPageUrl);
		String[] wordList = pageContent.split("\\s");
		for (String word : wordList) {
			if (!index.containsKey(word)) {
				index.put(word, new ArrayList<URL>());
			}
			index.get(word).add(rootPageUrl);
		}
		// Compute 10 top words
		List<WordOccurences> wordsOccurences = new ArrayList<>();
		for (Map.Entry<String, List<URL>> e: index.entrySet()) {
			wordsOccurences.add(new WordOccurences(e.getKey(), e.getValue()));
		}
		Collections.sort(wordsOccurences);
		// Build real result
		Map<String, List<URL>> result = new HashMap<>();
		int nbResults = Math.min(9, wordsOccurences.size());
		for (WordOccurences wo:wordsOccurences.subList(0, nbResults  )) {
			result.put(wo.word, index.get(wo.word));
		}
		return result;
		
	}

	public static void main(String[] args) {
		SimplePageClient pageClient = new SimplePageClient();
		Map<String, List<URL>> result = getTopTenWords(pageClient);
		for (Map.Entry<String, List<URL>> e : result.entrySet()) {
			System.out.println(e.getKey() + ":" + e.getValue().size());
		}
	}
}
