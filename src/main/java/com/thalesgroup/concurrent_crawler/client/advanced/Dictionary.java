package com.thalesgroup.concurrent_crawler.client.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Dictionary {

    private static class DictionaryLoader {

        private static final Dictionary INSTANCE = new Dictionary();
    }

    private final List<String> dict = new ArrayList<>();

    private Dictionary() {
        try (InputStream fis = this.getClass().getResourceAsStream("/dictionary.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line;
            while ((line = br.readLine()) != null) {
                dict.add(line);
            }
        } catch (IOException ex) {
            System.err.println("can't load dictionary" + ex);
            throw new RuntimeException("can't load dictionary", ex);
        }
    }

    static public Dictionary getInstance() {
        return DictionaryLoader.INSTANCE;
    }

    public String getRandomWord(Random generator) {
        return dict.get(generator.nextInt(dict.size()));
    }
}
