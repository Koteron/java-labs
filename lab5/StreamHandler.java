package com.example.lab7.lab5;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class StreamHandler
{
    public static String start(String exampleNumber)
    {
        StringBuilder output = new StringBuilder();
        switch(exampleNumber)
        {
            case "Example №1":
                List<Integer> list1 = new ArrayList<>();
                list1.add(0);
                list1.add(1);
                list1.add(2);
                list1.add(3);
                list1.add(4);
                output.append(StreamMethods.getAverageValue(list1));
                break;

            case "Example №2":
                List<String> list2 = new ArrayList<>();
                list2.add("One");
                list2.add("Two");
                list2.add("Three");
                list2.add("Four");
                list2.add("Five");
                output.append(StreamMethods.transformListOfStrings(list2));
                break;

            case "Example №3":
                List<Integer> list3 = new ArrayList<>();
                list3.add(0);
                list3.add(1);
                list3.add(2);
                list3.add(3);
                list3.add(4);
                output.append(StreamMethods.getListOfSquares(list3));
                break;

            case "Example №4":
                List<Integer> list4 = new ArrayList<>();
                list4.add(0);
                list4.add(1);
                list4.add(2);
                list4.add(3);
                list4.add(4);
                try
                {
                    output.append(StreamMethods.getLastElement(list4));
                }
                catch(EmptyCollectionException e)
                {
                    output.append(e.toString());
                }
                break;

            case "Example №5":
                List<String> list5 = new ArrayList<>();
                list5.add("One");
                list5.add("Two");
                list5.add("Three");
                list5.add("Four");
                list5.add("Five");
                output.append(StreamMethods.getStringsStartingWithLetter(list5, 'T'));
                break;

            case "Example №6":
                int[] arr = {1, 2, 3, 4, 5, 6};
                output.append(StreamMethods.getSumOfEvenElements(arr));
                break;

            case "Example №7":
                List<String> list7 = new ArrayList<>();
                list7.add("One");
                list7.add("Three");
                list7.add("Four");
                list7.add("Six");
                try
                {
                    output.append(StreamMethods.createMapFromList(list7));
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
