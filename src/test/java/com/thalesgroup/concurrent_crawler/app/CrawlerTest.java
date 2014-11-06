package com.thalesgroup.concurrent_crawler.app;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.thalesgroup.concurrent_crawler.client.advanced.AdvancedPageClient;
import com.thalesgroup.concurrent_crawler.client.simple.SimplePageClient;

public class CrawlerTest {
	
	
	private static final List<Word> RESULT_SIMPLE_PAGE = Arrays.asList(
			new Word("titi", 8*10001), new Word("tata", 4*10001), new Word("toto", 4*10001));
	
	private static final List<Word> RESULT_SIMPLE_PAGE_2 = Arrays.asList(
			new Word("titi", 8*10001*10000), new Word("tata", 4*10001*10000), new Word("toto", 4*10001*10000));

	private static final List<Word> RESULT_ADVANCED_PAGE = Arrays.asList(
			new Word("player", 51012), 
			new Word("curches", 49641), 
			new Word("rutiles", 45695), 
			new Word("federals", 45456), 
			new Word("erudite", 42556), 
			new Word("physis", 39489), 
			new Word("murkly", 37951), 
			new Word("evolve", 33862), 
			new Word("pointman", 28006), 
			new Word("bunch", 26440));


	@Test
	public void testCrawlSimplePageClient() throws Exception{
		 //given
		URL url = new URL("http://codingdojo");
		
		//when
		Crawler crawler = new Crawler();
		List<Word> lstWord = crawler.crawlPageOne(url, new SimplePageClient());
		
		// then
		assertEquals(RESULT_SIMPLE_PAGE, lstWord);
		
	}

	@Test
	public void testCrawlAdvancedPageClient() throws Exception{
		 //given
		URL url = new URL("http://codingdojo");
		
		//when
		Crawler crawler = new Crawler();
		List<Word> lstWord = crawler.crawlPageOne(url, new AdvancedPageClient());
		
		
		assertEquals(RESULT_ADVANCED_PAGE, lstWord);
	}
	
	@Test(timeout=10000)
	public void testCrawlAdvancedPageClient_LessThanTenSeconds() throws Exception{
		 //given
		URL url = new URL("http://codingdojo");
		
		//when
		Crawler crawler = new Crawler();
		List<Word> lstWord = crawler.crawlPageOne(url, new AdvancedPageClient());
		
		assertEquals(RESULT_ADVANCED_PAGE, lstWord);
	}

	@Test
	public void testCrawlSimplePageClientRecursiveDepth1() throws Exception{
		 //given
		URL url = new URL("http://codingdojo");
		
		//when
		Crawler crawler = new Crawler();
		List<Word> lstWord = crawler.crawlPages(url, new SimplePageClient(), 1);
		
		assertEquals(RESULT_SIMPLE_PAGE, lstWord);
	}
	
	@Test
	public void testCrawlSimplePageClientRecursiveDepth2() throws Exception{
		 //given
		URL url = new URL("http://codingdojo");
		
		//when
		Crawler crawler = new Crawler();
		List<Word> lstWord = crawler.crawlPages(url, new SimplePageClient(), 2);
		
		assertEquals(RESULT_SIMPLE_PAGE_2, lstWord);
	}
	
}
