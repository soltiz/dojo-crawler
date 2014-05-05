package com.thalesgroup.concurrent_crawler.client.advanced;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.thalesgroup.concurrent_crawler.client.Page;

public class RandomPage extends Page {

	private final int MAX_WORDS = 2000;
	private final int MAX_EXTERNAL_LINKS = 100;
	private final int MAX_SELF_LINKS = 5;

	private final URL url;

	public RandomPage(URL url) {
		this.url = url;
	}

	private Random getNewRandom() {
		return new Random(url.hashCode());
	}

	@Override
	public String getContent() {
		Random r = getNewRandom();
		StringBuilder sb = new StringBuilder();
		sb.append(Dictionary.getInstance().getRandomWord(r));
		final int wordCount = r.nextInt(MAX_WORDS);
		for (int i = 1; i < wordCount; i++) {
			final String word = Dictionary.getInstance().getRandomWord(r);
			sb.append(" ").append(word);
		}
		return sb.toString();
	}

	@Override
	public List<URL> getLinks() {
		Random r = getNewRandom();
		final List<URL> result = new LinkedList<>();
		int linksCount = r.nextInt(MAX_EXTERNAL_LINKS);
		for (int i = 1; i < linksCount; i++) {
			try {
				final String word = Dictionary.getInstance().getRandomWord(r);
				result.add(new URL("file://" + word));
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		linksCount = r.nextInt(MAX_SELF_LINKS);
		for (int i = 1; i < linksCount; i++) {
			result.add(url);
		}
		return result;
	}

}
