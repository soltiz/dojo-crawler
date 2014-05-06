package com.thalesgroup.concurrent_crawler.client;

import java.io.IOException;
import java.net.URL;

public abstract class PageClient {

    /**
     * Download a page : this may takes some time
     * 
     * @param url - the page location
     * @return the page
     * @throws java.io.IOException if page can not be loaded
     */
    abstract public Page getPage(URL url) throws IOException;

}
