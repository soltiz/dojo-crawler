/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thalesgroup.concurrent_crawler.client.advanced;

import com.thalesgroup.concurrent_crawler.client.Page;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author r3mi
 */
public class RandomPageTest extends TestCase {

    public RandomPageTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getText method, of class RandomPage.
     */
    public void testGetText() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        Page instance = new RandomPage(url);
        String expResult = "bunch pointman rutiles erudite rutiles bezazzes federals murkly player pointman erudite";
        String result = instance.getText();
        assertTrue(result, result.startsWith(expResult));
    }

    public void testRepeatableGetText() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        Page instance = new RandomPage(url);
        String result1 = instance.getText();
        String result2 = instance.getText();
        assertEquals(result1, result2);
    }

    /**
     * Test of getLinks method, of class RandomPage.
     */
    public void testGetLinks() throws MalformedURLException {
        URL url = new URL("http://www.google.com");
        Page instance = new RandomPage(url);
        List<URL> result = instance.getLinks();
        for (URL u : result) {
            assertTrue(u.toString(), u.toString().startsWith(url.toString()));
        }
    }

}
