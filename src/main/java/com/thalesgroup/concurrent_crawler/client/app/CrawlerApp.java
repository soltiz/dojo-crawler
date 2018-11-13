package com.thalesgroup.concurrent_crawler.client.app;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.thalesgroup.concurrent_crawler.client.Page;

public class CrawlerApp {

	/**
	 * Retourne 10 mots d'une page donnée
	 * @param page la page en question
	 * @param depth TODO
	 * @return
	 */
	public List<Entry<String, List<URL>>> getWordsOccurences(Page page, URL url, int depth) {	
		List<String> words = new ArrayList<>();
		List<String> rawResult = Arrays.asList(page.getText().split(" "));
		Map<String, List<URL>> occurencesRef = new HashMap<>(); 

		rawResult.stream().forEach(s -> {
			List<URL> refList = occurencesRef.get(s);
			if(refList == null) {
				refList = new ArrayList<URL>();
			}
			refList.add(url);
			occurencesRef.put(s, refList);

		});
		
		return sortMapValues(occurencesRef.entrySet())
				.subList(0, Integer.min(10, occurencesRef.size()));
	}
	
	public List<String> getWords(Page page) {
		 return this.getWordsOccurences(page, null, 1)
			.stream()
			.map(s -> s.getKey())
			.collect(Collectors.toList());
	}
	
	

	
	
	private List<Entry<String, List<URL>>> sortMapValues(Set<Entry<String, List<URL>>> set) {
		
		ArrayList<Entry<String, List<URL>>> var = new ArrayList<>(set);
			
		var.sort(new Comparator<Entry<String, List<URL>>>() {
			@Override
			public int compare(Entry<String, List<URL>> o1, Entry<String, List<URL>> o2) {
				return o2.getValue().size() - o1.getValue().size();
			}
		});
		return var;
	}
}
