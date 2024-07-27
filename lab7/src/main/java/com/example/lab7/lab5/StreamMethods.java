package com.example.lab7.lab5;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMethods 
{
    public static double getAverageValue(List<Integer> list) 
    {
        return list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public static List<String> transformListOfStrings(List<String> list) 
    {
        return list.stream().map(str -> "_new_" + str.toUpperCase()).collect(Collectors.toList());
    }

    public static List<Integer> getListOfSquares(List<Integer> list) 
    {
       return list.stream().mapToInt(Integer::intValue).map(i -> i * i).mapToObj(Integer::valueOf).collect(Collectors.toList());
    }

    public static <T> T getLastElement(Collection<T> collection) throws EmptyCollectionException 
    {
        return collection.stream().reduce((i, j) -> j).orElseThrow(EmptyCollectionException::new);
    }

    public static List<String> getStringsStartingWithLetter(Collection<String> collection, char letter) 
    {
        return collection.stream().filter(str -> !str.isEmpty() && str.charAt(0) == letter)
                .sorted()
                .collect(Collectors.toList());
    }


    public static int getSumOfEvenElements(int [] arr) 
    {
        return Arrays.stream(arr).filter(i -> i % 2 == 0).reduce(0, Integer::sum);
    }

    public static Map<Character, String> createMapFromList(List<String> list) throws IllegalStateException
    {
        return list.stream()
        .filter(str -> !str.isEmpty())
        .collect(Collectors.toMap(k -> k.charAt(0), v -> v.substring(1)));
    }
}
