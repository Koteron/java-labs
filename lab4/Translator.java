package com.example.lab7.lab4;

import com.example.lab7.lab4.exceptions.FileReadException;
import com.example.lab7.lab4.exceptions.InvalidDataException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Translator {
    private static final String FILE_READ_EXCEPTION = "Can't read data from file";
    private static final String INCORRECT_DATA_EXCEPTION = "Can't read data from file";

    private final Dictionary dictionary;
    private List<String> text = new ArrayList<>();

    public Translator(final Dictionary dictionary, final File textPath) throws FileReadException {
        this.dictionary = dictionary;
        setText(textPath);
    }

    public void setText(final File textPath) throws FileReadException {
        text = getTextFromFile(textPath);
    }

    public List<String> getTranslatedText() throws InvalidDataException {
        if (text.isEmpty()) {
            throw new InvalidDataException(INCORRECT_DATA_EXCEPTION);
        }
        final List<String> result = new ArrayList<>();
        int textSize = text.size();
        StringBuilder currentWordToTranslate = new StringBuilder();
        String previousEnglishWordFromDict = "";
        for (int i = 0; i < textSize; i++) {
            if (text.get(i).contains(".")) {
                if (!currentWordToTranslate.isEmpty())
                {
                    result.add(dictionary.getDictionary().getOrDefault(previousEnglishWordFromDict, text.get(i-1)));
                    previousEnglishWordFromDict = "";
                }
                result.add(dictionary.getDictionary().getOrDefault(previousEnglishWordFromDict, text.get(i)));
                currentWordToTranslate.delete(0, currentWordToTranslate.length());
                continue;
            }
            if (!currentWordToTranslate.isEmpty()) {
                currentWordToTranslate.append(" ");
            }
            currentWordToTranslate.append(text.get(i));
            String currentEnglishWordFromDict = getEnglishWordFromDictionary(currentWordToTranslate.toString());
            if (!containsWordInDictionary(currentWordToTranslate.toString()) || !previousEnglishWordFromDict.isEmpty() && currentEnglishWordFromDict.length() != currentWordToTranslate.toString().length()) {
                if (!previousEnglishWordFromDict.isEmpty()) {
                    i--;
                }
                result.add(dictionary.getDictionary().getOrDefault(previousEnglishWordFromDict, text.get(i)));
                currentWordToTranslate.delete(0, currentWordToTranslate.length());
                previousEnglishWordFromDict = "";
                continue;
            }
            if (currentEnglishWordFromDict.length() > previousEnglishWordFromDict.length()) {
                previousEnglishWordFromDict = currentEnglishWordFromDict;

            }
        }
        result.add(dictionary.getDictionary().getOrDefault(previousEnglishWordFromDict, text.get(textSize - 1)));
        return result;
    }

    private String getEnglishWordFromDictionary(final String word) {
        String currentWord = "";
        for (var el : dictionary.getDictionary().keySet()) {
            if (el.equals(word)) {
                return el;
            }
            if (word.contains(el) && el.length() > currentWord.length()) {
                currentWord = el;
            }
        }
        return currentWord;
    }

    private boolean containsWordInDictionary(final String word) {
        for (var el : dictionary.getDictionary().keySet()) {
            if (el.contains(word)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getTextFromFile(final File textPath) throws FileReadException {
        final List<String> text = new ArrayList<>();
        try (FileReader reader = new FileReader(textPath)) {
            try (Scanner in = new Scanner(reader)) {
                while (in.hasNextLine()) {
                    String[] currentWords = in.nextLine().toLowerCase(Locale.ROOT).split(" ");
                    Collections.addAll(text, currentWords);
                }
            }
        } catch (IOException e) {
            throw new FileReadException(FILE_READ_EXCEPTION);
        }
        return text;
    }
}
