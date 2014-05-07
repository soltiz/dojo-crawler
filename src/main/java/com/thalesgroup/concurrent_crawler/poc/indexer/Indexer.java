package com.thalesgroup.concurrent_crawler.poc.indexer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Indexer {

	final private Map<String, List<URL>> index;

	protected Indexer(Map<String, List<URL>> index) {
		super();
		this.index = index;
	}

	protected Map<String, List<URL>> getIndex() {
		return index;
	}

	public abstract void addWord(String word, URL pageURL);

	public void addText(String pageContent, URL pageURL) {
	    String[] wordList = pageContent.split("\\s");
	    for (String word : wordList) {
	        addWord(word, pageURL);
	    }
	}

	/**
	 * Retourne les mots les plus utilisés, et les URL qui les contiennent
	 *
	 * @return
	 */
	public Map<String, List<URL>> getTopWords(int wordCount) {
	    // Compute top words
	    List<WordOccurences> wordsOccurences = new ArrayList<>();
	    for (Map.Entry<String, List<URL>> e : index.entrySet()) {
	        wordsOccurences.add(new WordOccurences(e.getKey(), e.getValue()
	                .size()));
	    }
	    Collections.sort(wordsOccurences);
	    final int nbResults = Math.min(wordCount, wordsOccurences.size());
	    // Build real result
	    Map<String, List<URL>> result = new HashMap<>();
	    for (WordOccurences wo : wordsOccurences.subList(0, nbResults)) {
	        result.put(wo.word, index.get(wo.word));
	    }
	    return result;
	}

	public Map<String, List<URL>> getTopTenWords() {
	    return getTopWords(10);
	}

}