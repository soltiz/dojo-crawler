package com.thalesgroup.concurrent_crawler.client.simple;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.thalesgroup.concurrent_crawler.client.Page;

public class SelfReferencingPage extends Page {
	

    private final int NB_LINKS = 10000;

    private final URL url;
    private String textContent ="titi toto tata titi titi toto tata titi titi toto tata titi titi toto tata titi";

    public SelfReferencingPage(URL url) {
        this.url = url;
    }

    @Override
    public String getText() {
        return textContent;
    }
    
  
    public String addText(String extension) {
        return textContent+extension;
    }
   

    @Override
    public List<URL> getLinks() {
        ArrayList<URL> result = new ArrayList<URL>();
        for (int i = 0; i < NB_LINKS; i++) {
            result.add(url);
        }
        return result;
    }

}
