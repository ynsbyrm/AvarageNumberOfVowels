package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        //Read the file
        File inputTxt = new File("INPUT.TXT");
        if(!inputTxt.exists()){
            throw new RuntimeException("File not found at:" + inputTxt.getAbsolutePath());
        }

        //Call getFileContent method for reading file content and split it to an array
        String[] words = getFileContent(inputTxt).split(" ");
        List<ResultObject> resultObjectList = new ArrayList<>();

            //Iterate all over the words
            for(String word:words){

                //Call foundVowels for getting the count of each evolwes as HashMap<Vowels,Count>
                HashMap<String, Integer> foundedVowelsForNewWord = foundVowels(word.toLowerCase());
                //Check for is there any match?
                int instantMatch = 0;
                //Iterate all over the result object
                for(ResultObject resultObject:resultObjectList){
                    //Check is there any other result object which created before with the same vowels and wordlength
                    if(resultObject.getVowels().keySet().equals(foundedVowelsForNewWord.keySet()) &&
                        resultObject.getLengthOfWord() == word.length()){

                        instantMatch++;
                        //Iterate all over the keyset for assigning new counts
                        for(String key:foundedVowelsForNewWord.keySet()){
                            int oldNumberOfVowels = resultObject.getVowels().get(key);
                            int newNumberOfVowels = oldNumberOfVowels + foundedVowelsForNewWord.get(key);
                            resultObject.getVowels().put(key, newNumberOfVowels);
                        }
                        resultObject.setWordCount(resultObject.getWordCount()+1);
                        //Breake the loop if there is any match
                        break;
                    }
                }

                //If there is no match then add a new result object to the list
                if(instantMatch == 0){
                    resultObjectList.add(new ResultObject(foundedVowelsForNewWord, word.length(), 1));
                }
            }

        File resultFile = new File("OUTPUT.txt");
        resultFile.createNewFile();
        try(FileWriter fileWriter = new FileWriter(resultFile)){
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for(ResultObject resultObject : resultObjectList) {
                printWriter.println(resultObject);
            }

            printWriter.close();
            System.out.println("OUTPUT.txt created:" + resultFile.getAbsolutePath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String getFileContent(File inputTxt){
        try(FileReader fileReader = new FileReader(inputTxt)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line=bufferedReader.readLine()) != null){
                stringBuffer.append(line.replaceAll("\\p{Punct}", ""));
            }
            return stringBuffer.toString();
        }catch (IOException ioe){
            ioe.printStackTrace();
            return "";
        }
    }

    private static HashMap<String, Integer> foundVowels(String lowerCase){
        HashMap<String, Integer> hmap = new HashMap<>();
        for(int i=0; i<lowerCase.length(); i++){

            String foundedVowel = "";
            switch (lowerCase.charAt(i)){
                case 'a':
                    foundedVowel = "a";
                    break;
                case 'e':
                    foundedVowel = "e";
                    break;
                case 'i':
                    foundedVowel = "i";
                    break;
                case 'o':
                    foundedVowel = "o";
                    break;
                case 'u':
                    foundedVowel = "u";
                    break;
                default:
                    break;
            }

            if (foundedVowel != ""){
                int vowelCount = !hmap.containsKey(foundedVowel) ? 1:hmap.get(foundedVowel) + 1;
                hmap.put(foundedVowel, vowelCount);
            }
        }
        return hmap;
    }
}