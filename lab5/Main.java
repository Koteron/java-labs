import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main 
{
    public static void main(String[] args) 
    {
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        double averageValue = StreamMethods.getAverageValue(list1);
        System.out.println(averageValue);

        List<String> list2 = new ArrayList<String>();
        list2.add("One");
        list2.add("Two");
        list2.add("Three");
        list2.add("Four");
        list2.add("Five");
        list2 = StreamMethods.transformListOfStrings(list2);
        System.out.println(list2);

        list1 = StreamMethods.getListOfSquares(list1);
        System.out.println(list1);

        try
        {
            int lastelement = StreamMethods.getLastElement(list1);
            System.out.println(lastelement);
        }
        catch(EmptyCollectionException e)
        {
            System.out.println(e.toString());
        }

        List<String> list3 = new ArrayList<String>();
        list3.add("One");
        list3.add("Two");
        list3.add("Three");
        list3.add("Four");
        list3.add("Five");
        list3 = StreamMethods.getStringsStartingWithLetter(list3, 'T');
        System.out.println(list3);

        int[] arr = {1, 2, 3, 4, 5, 6};
        int evenSum = StreamMethods.getSumOfEvenElements(arr);
        System.out.println(evenSum);


        List<String> list4 = new ArrayList<String>();
        list4.add("One");
        list4.add("Three");
        list4.add("Four");
        list4.add("Six");
        try
        {
            Map<Character, String> map = StreamMethods.createMapFromList(list4);
            System.out.println(map);
        }
        catch (IllegalStateException e)
        {
            System.out.println("Duplicate keys!");
        }
        
    }
}
