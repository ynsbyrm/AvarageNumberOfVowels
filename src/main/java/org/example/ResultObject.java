package org.example;

import java.util.HashMap;

public class ResultObject {

    private HashMap<String,Integer> vowels;
    private int lengthOfWord;
    private int wordCount;

    public ResultObject(HashMap<String,Integer> hashMap, int lengthOfWord, int wordCount){
        this.vowels = hashMap;
        this.lengthOfWord = lengthOfWord;
        this.wordCount = wordCount;
    }

    public HashMap<String, Integer> getVowels() {
        return vowels;
    }

    public void setVowels(HashMap<String, Integer> vowels) {
        this.vowels = vowels;
    }

    public int getLengthOfWord() {
        return lengthOfWord;
    }

    public void setLengthOfWord(int lengthOfWord) {
        this.lengthOfWord = lengthOfWord;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    private int getVowelsCount(){
        int countOfWowels=0;
        for(String key:vowels.keySet()){
            countOfWowels += vowels.get(key);
        }
        return countOfWowels;
    }

    @Override
    public String toString(){
        return "(" + vowels.keySet().toString().replaceAll("\\[", "{")
                .replaceAll("]", "}, ")
                + lengthOfWord + ") -> " + (float)getVowelsCount()/wordCount;
    }
}
