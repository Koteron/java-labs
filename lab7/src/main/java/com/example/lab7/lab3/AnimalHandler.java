package com.example.lab7.lab3;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class AnimalHandler
{
    public static void segregate(Collection<? extends Mammal> srcCollection,
                                 Collection<? super CommonHedgehog> col1, 
                                 Collection<? super Manul> col2,
                                 Collection<? super Lynx> col3) 
    {
        for (Mammal animal : srcCollection) 
        {
            if (animal instanceof CommonHedgehog) 
            {
                col1.add((CommonHedgehog) animal);
            } 
            else if (animal instanceof Manul) 
            {
                col2.add((Manul) animal);
            } 
            else 
            {
                col3.add((Lynx)animal);
            }
        }
    }
    public static String start(String exampleNumber)
    {
        StringBuilder output = new StringBuilder();
        switch (exampleNumber) {
            case "Example №1":

                List<Mammal> srcCollection1 = new ArrayList<>();
                srcCollection1.add(new CommonHedgehog("Dark Gray", 10));
                srcCollection1.add(new Lynx("Orange", 30));
                srcCollection1.add(new Manul("White", 35));

                List<Hedgehog> col11 = new ArrayList<>();
                List<Felidae> col12 = new ArrayList<>();
                List<Predator> col13 = new ArrayList<>();
                segregate(srcCollection1, col11, col12, col13);

                output.append("Hedgehog: " + col11 + "\n");
                output.append("Felidae: " + col12 + "\n");
                output.append("Predator: " + col13);

                break;


            case "Example №2":

                List<Predator> srcCollection2 = new ArrayList<>();
                srcCollection2.add(new Manul("Light Gray", 29));
                srcCollection2.add(new Lynx("Brown", 36));
                srcCollection2.add(new Manul("White", 38));

                List<Chordate> col21 = new ArrayList<>();
                List<Manul> col22 = new ArrayList<>();
                List<Felidae> col23 = new ArrayList<>();
                segregate(srcCollection2, col21, col22, col23);

                output.append("Chordate: " + col21 + "\n");
                output.append("Manul: " + col22 + "\n");
                output.append("Felidae: " + col23);

                break;


            case "Example №3":

                List<Hedgehog> srcCollection3 = new ArrayList<>();
                srcCollection3.add(new CommonHedgehog("Gray", 24));
                srcCollection3.add(new CommonHedgehog("Dark Gray", 21));
                srcCollection3.add(new CommonHedgehog("Brown", 17));

                List<Insectivore> col31 = new ArrayList<>();
                List<Predator> col32 = new ArrayList<>();
                List<Predator> col33 = new ArrayList<>();
                segregate(srcCollection3, col31, col32, col33);

                output.append("Insectivore: " + col31 + "\n");
                output.append("Predator: " + col32 + "\n");
                output.append("Predator: " + col33);

                break;
        }
        return output.toString();
    }
}


