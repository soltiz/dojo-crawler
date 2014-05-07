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

	@Test
	public void testCrawlPageOne() throws Exception{
		 //given
		URL url = new URL("http://codingdojo");
		
		//when
		Crawler crawler = new Crawler();
		List<Word> lstWord = crawler.crawlPageOne(url, new SimplePageClient());
		
		// then
		assertEquals(RESULT_SIMPLE_PAGE, lstWord);
		
	}

	@Test(timeout = 10000)
	public void testCrawlPageOne2() throws Exception{
		 //given
		URL url = new URL("http://codingdojo");
		
		//when
		Crawler crawler = new Crawler();
		List<Word> lstWord = crawler.crawlPageOne(url, new AdvancedPageClient());
		
		System.out.println(lstWord);
		
		// TODO assert
	}
}
