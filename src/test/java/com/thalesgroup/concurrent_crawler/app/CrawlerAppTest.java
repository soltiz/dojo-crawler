package com.thalesgroup.concurrent_crawler.app;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.thalesgroup.concurrent_crawler.client.Page;
import com.thalesgroup.concurrent_crawler.client.advanced.RandomPage;
import com.thalesgroup.concurrent_crawler.client.app.CrawlerApp;
import com.thalesgroup.concurrent_crawler.client.simple.SelfReferencingPage;

public class CrawlerAppTest {

	@Test
	public void testDisplayTenMostFrequencedWordsFromPage() throws MalformedURLException {
		// GIVEN
		// une page web qui contient 8 mots différents (certains répétés)
		URL url = new URL("file://toto");
		SelfReferencingPage page = new SelfReferencingPage(url);
		System.out.println(page.getText());

		// WHEN
		// le crawler est appellé
		CrawlerApp crawlerApp = new CrawlerApp();
		List<String> result = crawlerApp.getWords(page);

		// THEN
		// il retourne les 3 mots
		System.out.println(result);
		Assert.assertEquals(3, result.size());
		Assert.assertTrue(result.contains("titi"));
		Assert.assertTrue(result.contains("toto"));
		Assert.assertTrue(result.contains("tata"));
	}

	@Test
	public void testDisplayOccurencesMostFrequencedWordsFromPage() throws MalformedURLException {
		// GIVEN
		// une page web qui contient 3 mots différents (certains répétés)
		URL url = new URL("file://toto");
		SelfReferencingPage page = new SelfReferencingPage(url);
		System.out.println(page.getText());

		// WHEN
		// le crawler est appellé
		CrawlerApp crawlerApp = new CrawlerApp();
		List<Entry<String, List<URL>>> result = crawlerApp.getWordsOccurences(page, url, 1);

		// THEN
		// il retourne les 3 mots
		System.out.println(result);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(8, result.get(0).getValue().size());
	}

	@Test
	public void testDisplayTenUniqueWordsFromPage() throws MalformedURLException {
		// GIVEN
		// une page web
		URL url = new URL("file://toto");
		Page page = new RandomPage(url);
		System.out.println(page.getText());

		// WHEN
		// le crawler est appellé
		CrawlerApp crawlerApp = new CrawlerApp();
		List<String> result = crawlerApp.getWords(page);

		// THEN
		// il retourne 10 mots unique

		System.out.println(result);
		Set<String> setWord = new HashSet<>(result);
		Assert.assertEquals(10, result.size());
		Assert.assertEquals(10, setWord.size());
	}

	@Test
	public void testDisplayWithThreeWordsFromPage() throws MalformedURLException {
		// GIVEN
		// une page web
		URL url = new URL("file://toto");
		Page page = new SelfReferencingPage(url);
		System.out.println(page.getText());

		// WHEN
		// le crawler est appellé
		CrawlerApp crawlerApp = new CrawlerApp();
		List<String> result = crawlerApp.getWords(page);

		// THEN
		// il retourne 10 mots unique

		System.out.println(result);
		Set<String> setWord = new HashSet<>(result);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(3, setWord.size());
	}
	
	@Test
	public void testDisplayPageTreeStatistics() throws MalformedURLException {
		// GIVEN
		// une page 
		URL url = new URL("file://toto");
		Page page = new RandomPage(url);
		// WHEN
		// le crawler parcourt récursivement ses liens --> on les indexe
		CrawlerApp crawlerApp = new CrawlerApp();
		List<Entry<String, List<URL>>> result = crawlerApp.getWordsOccurences(page, url, 3);
		
		System.out.println(result);
		
		// THEN
		// On s'attend à avoir les statistiques0 (10 meilleurs mots) de toutes les pages (3 niveaux de prof.)
		Assert.assertNotEquals(1, new HashSet<>(result.get(0).getValue()).size());
	}

}
