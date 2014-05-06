/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thalesgroup.concurrent_crawler.client.advanced;

import java.util.Random;
import junit.framework.TestCase;

/**
 *
 * @author r3mi
 */
public class DictionaryTest extends TestCase {

    public DictionaryTest(String testName) {
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
     * Test of getInstance method, of class Dictionary.
     */
    public void testGetInstance() {
        Dictionary result = Dictionary.getInstance();
        assertNotNull("getInstance", result);
    }

    /**
     * Test of getRandomWord method, of class Dictionary.
     */
    public void testGetRandomWord() {
        Dictionary instance = Dictionary.getInstance();
        Random generator = new Random(0x1234);
        assertEquals("forborne", instance.getRandomWord(generator));
        assertEquals("lunacy", instance.getRandomWord(generator));
        assertEquals("frae", instance.getRandomWord(generator));
    }

    public void testRepeatableGetRandomWord() {
        Dictionary instance = Dictionary.getInstance();
        assertEquals("chicos", instance.getRandomWord(new Random(0x123456)));
        assertEquals("chicos", instance.getRandomWord(new Random(0x123456)));
        assertEquals("vinified", instance.getRandomWord(new Random(0x12345678)));
    }

}
