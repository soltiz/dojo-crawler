/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thalesgroup.concurrent_crawler.client.simple;

import com.thalesgroup.concurrent_crawler.client.Page;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author r3mi
 */
public class SelfReferencingPageTest extends TestCase {

    public SelfReferencingPageTest(String testName) {
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
     * Test of getText method, of class SelfReferencingPage.
     */
    public void testGetText() throws MalformedURLException {
        URL url = new URL("file://toto");
        Page instance = new SelfReferencingPage(url);
        String result = instance.getText();
        assertTrue(result.contains("toto"));
        assertTrue(result.contains("titi"));
        assertTrue(result.contains("tata"));
    }

    /**
     * Test of getLinks method, of class SelfReferencingPage.
     */
    public void testGetLinks() throws MalformedURLException {
        URL url = new URL("file://toto");
        Page instance = new SelfReferencingPage(url);
        List<URL> result = instance.getLinks();
        for (URL u : result) {
            assertEquals(url, u);
        }
    }

}
