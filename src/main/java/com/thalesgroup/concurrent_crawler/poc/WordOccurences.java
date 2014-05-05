package com.thalesgroup.concurrent_crawler.poc;

import java.net.URL;
import java.util.List;

public class WordOccurences implements Comparable<WordOccurences> {

	public final String word;
	public final int occurences;
	
	public WordOccurences(String word, List<URL> pages) {
		this.word = word;
		this.occurences = pages.size();
	}
	
	@Override
	public int compareTo(WordOccurences o) {
		return occurences - o.occurences;
	}

	
	
}
