package com.thalesgroup.concurrent_crawler.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.sql.rowset.spi.SyncResolver;

import com.thalesgroup.concurrent_crawler.client.Page;

public class TopTen {

	private static final int RESULT_NUMBER = 10;

	private Map<String, Integer> wordsMap = new HashMap<>();

	public void addPage(Page page) {
		// Préconditions
		if (page == null) {
			return;
		}

		// body
		List<String> lstWords = getWordsFromText(page.getText());

		// Récupération des occurences des mots dans une map
		synchronized (wordsMap) {
			for (String word : lstWords) {
				int occurence = 1;
				if (wordsMap.containsKey(word)) {
					occurence = wordsMap.get(word) + 1;
				}
				wordsMap.put(word, occurence);
			}
		}

	}

	public List<Word> getTopTen() {

		// Passage de la Map en list de Word
		ArrayList<Word> words = new ArrayList<>();
		for (Entry<String, Integer> entry : wordsMap.entrySet()) {
			words.add(new Word(entry.getKey(), entry.getValue()));
		}

		// Extraction des 10 premières occurences
		List<Word> result = extractResult(words);

		return result;

	}

	/**
	 * Extraction des 10 dernières occurences
	 * 
	 * @param lstWord
	 * @return
	 */
	private List<Word> extractResult(List<Word> lstWord) {
		List<Word> workingList = new ArrayList<>(lstWord);

		Collections.sort(workingList);

		int size = workingList.size();
		if (size > RESULT_NUMBER) {
			workingList = workingList.subList(size - RESULT_NUMBER, size);
		}
		Collections.reverse(workingList);

		return workingList;
	}

	public static List<String> getWordsFromText(String text) {

		if (text != null) {
			String[] words = text.trim().split("\\s+");
			return Arrays.asList(words);
		} else {
			return new ArrayList<>();
		}

	}

}
