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

    private Random getRepeatableRandom() {
        return new Random(url.toString().hashCode());
    }

    @Override
    public String getText() {
        Random r = getRepeatableRandom();
        StringBuilder sb = new StringBuilder();
        sb.append(Dictionary.getInstance().getRandomWord(r));
        final int wordCount = r.nextInt(MAX_WORDS);
        for (int i = 0; i < wordCount; i++) {
            final String word = Dictionary.getInstance().getRandomWord(r);
            sb.append(" ").append(word);
        }
        return sb.toString();
    }

    @Override
    public List<URL> getLinks() {
        Random r = getRepeatableRandom();
        final List<URL> result = new LinkedList<>();
        int linksCount = r.nextInt(MAX_EXTERNAL_LINKS);
        for (int i = 0; i < linksCount; i++) {
            try {
                final String word = Dictionary.getInstance().getRandomWord(r);
                result.add(new URL(url + "/" + word));
            } catch (MalformedURLException ex) {
                System.err.println("ignore bad url" + ex);
                throw new RuntimeException("ignore bad url", ex);
            }
        }
        linksCount = r.nextInt(MAX_SELF_LINKS);
        for (int i = 0; i < linksCount; i++) {
            result.add(url);
        }
        return result;
    }

}
