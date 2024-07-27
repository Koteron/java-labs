package com.example.lab7.lab3;

public class Manul extends Felidae 
{
    public Manul(String color, int weight) 
    {
        super(color, weight);
    }

    @Override
    public void voice() 
    {
        System.out.println("Meow!");

    }
}
