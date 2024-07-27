package com.example.lab7.lab4;

import com.example.lab7.lab4.exceptions.FileReadException;
import com.example.lab7.lab4.exceptions.InvalidDataException;
import com.example.lab7.lab4.exceptions.InvalidFileFormatException;

import java.io.File;

public class DictionaryHandler {

    public static String start(String dictPath, String textPath) {
        StringBuilder output = new StringBuilder();
        try {
            Dictionary myDictionary = new Dictionary(new File(dictPath));
            Translator myTranslator = new Translator(myDictionary, new File(textPath));
            for (var el: myTranslator.getTranslatedText()) {
                output.append(el + "\n");
            }
        } catch (InvalidFileFormatException | FileReadException | InvalidDataException e) {
            output.append(e.getMessage());
        }
        return output.toString();
    }
}