package com.example.lab7.lab3;

public class CommonHedgehog extends Hedgehog {

    public CommonHedgehog(String color, int weight) 
    {
        super(color, weight);
    }

    @Override
    public void voice() 
    {
        System.out.println("Shhhhh!");
    }

}
