package com.thalesgroup.concurrent_crawler.app;

public class Word implements Comparable<Word> {

	private String word;
	private int occurence;

	public Word(String word, int occurence) {
		super();
		this.word = word;
		this.occurence = occurence;
	}

	protected String getWord() {
		return word;
	}

	protected void setWord(String word) {
		this.word = word;
	}

	protected int getOccurence() {
		return occurence;
	}

	protected void setOccurence(int occurence) {
		this.occurence = occurence;
	}

	@Override
	public int compareTo(Word that) {
		return Integer.compare(this.getOccurence(), that.getOccurence());
	}

	@Override
	public String toString() {
		return "Word [word=" + word + ", occurence=" + occurence + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + occurence;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (occurence != other.occurence)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

}
