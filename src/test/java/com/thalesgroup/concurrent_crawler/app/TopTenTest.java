package com.thalesgroup.concurrent_crawler.app;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.simple.SimplePageClient;

public class TopTenTest {

	private static final List<Word> RESULT_PAGE_MOCK = Arrays.asList(new Word(
			"a", 16), new Word("z", 12), new Word("e", 10), new Word("r", 9),
			new Word("t", 7), new Word("y", 6), new Word("u", 5), new Word("i",
					4), new Word("o", 3), new Word("p", 2));
	private static final List<Word> RESULT_SIMPLE_PAGE = Arrays.asList(
			new Word("titi", 8), new Word("tata", 4), new Word("toto", 4));
	private static final List<Word> RESULT_SIMPLE_PAGE_AND_PAGE_MOCK = Arrays
			.asList(new Word("a", 16), new Word("z", 12), new Word("e", 10),
					new Word("r", 9), new Word("titi", 8),new Word("t", 7), new Word("y", 6),
					new Word("u", 5), new Word("tata", 4), new Word("toto", 4));

	private Page getPageMock() {
		return new Page() {

			@Override
			public String getText() {
				return "a a a a a a a a a a a a a a a a"
						+ " z z z z z z z z z z z z" + " e e e e e e e e e e"
						+ " r r r r r r r r r" + " t t t t t t t"
						+ " y y y y y y" + " u u u u u" + " i i i i"
						+ " o o o " + " p p" + " q s d f g h j k l m";
			}

			@Override
			public List<URL> getLinks() {
				return null;
			}
		};
	}

	@Test
	public void testGetTopTen() throws Exception {
		// given
		Page page = new SimplePageClient()
				.getPage(new URL("http://codingdojo"));
		TopTen topten = new TopTen();

		// when
		topten.addPage(page);
		List<Word> words = topten.getTopTen();

		// then
		assertEquals(RESULT_SIMPLE_PAGE, words);
	}

	@Test
	public void testGetTopTen2() throws Exception {
		// given
		TopTen topten = new TopTen();
		Page page = getPageMock();

		// when
		topten.addPage(page);
		List<Word> words = topten.getTopTen();

		// then
		assertEquals(RESULT_PAGE_MOCK, words);
	}

	@Test
	public void testGetTopTen3() throws Exception {

		TopTen topTen = new TopTen();

		// given
		Page page = new SimplePageClient()
				.getPage(new URL("http://codingdojo"));
		topTen.addPage(getPageMock());

		assertEquals(RESULT_PAGE_MOCK, topTen.getTopTen());

		// when
		topTen.addPage(page);
		List<Word> words = topTen.getTopTen();

		// then
		assertEquals(RESULT_SIMPLE_PAGE_AND_PAGE_MOCK, words);

	}

	@Test
	public void testgetWordsFromText() throws Exception {
		// given
		String text = "titi toto tata";

		// when
		List<String> lstWord = TopTen.getWordsFromText(text);

		// then
		assertEquals(Arrays.asList("titi", "toto", "tata"), lstWord);
	}

	@Test
	public void testgetWordsFromText2() throws Exception {
		// given
		String text = " titi toto \n	\n \t	tata                                ";

		// when
		List<String> lstWord = TopTen.getWordsFromText(text);

		// then
		assertEquals(Arrays.asList("titi", "toto", "tata"), lstWord);
	}

}
