package com.thalesgroup.concurrent_crawler.poc;

public class WordOccurences implements Comparable<WordOccurences> {

    public final String word;
    public final int occurences;

    public WordOccurences(String word, int occurences) {
        this.word = word;
        this.occurences = occurences;
    }

    @Override
    public int compareTo(WordOccurences o) {
        return o.occurences - this.occurences;
    }

    @Override
    public String toString() {
        return word + ":" + occurences;
    }

}
