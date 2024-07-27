package com.example.lab7.lab5;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamHandler
{
    public static String start(String functionName, String input) throws NumberFormatException, EmptyCollectionException
    {
        StringBuilder output = new StringBuilder();
            switch (functionName)
            {
                case "GetAverageValue":
                    output.append(StreamMethods.getAverageValue(
                            Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList())));
                    break;

                case "TransformListOfStrings":
                    output.append(StreamMethods.transformListOfStrings(
                            Arrays.stream(input.split(" ")).collect(Collectors.toList())));
                    break;

                case "GetListOfSquares":
                    output.append(StreamMethods.getListOfSquares(
                            Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList())));
                    break;

                case "GetLastElement":
                    output.append(StreamMethods.getLastElement(
                            Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList())));
                    break;

                case "GetStringsStartingWithLetter":
                    List<String> list1 = Arrays.stream(input.split(" ")).collect(Collectors.toList());
                    output.append(StreamMethods.getStringsStartingWithLetter(list1.subList(0, list1.size() - 1),
                            input.charAt(input.length() - 1)));
                    break;

                case "GetSumOfEvenElements":
                    output.append(StreamMethods.getSumOfEvenElements(
                            Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray()));
                    break;

                case "CreateMapFromList":
                    try
                    {
                        output.append(StreamMethods.createMapFromList(
                                Arrays.stream(input.split(" ")).collect(Collectors.toList())));
                    }
                    catch (IllegalStateException e)
                    {
                        output.append("Duplicate keys!");
                    }
                    break;
            }
        return output.toString();
    }
}
