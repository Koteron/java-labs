package lab3;

public class Lynx extends Felidae
{
    public Lynx(String color, int weight) 
    {
        super(color, weight);
    }

    @Override
    public void voice() 
    {
        System.out.println("Rawr!");
    }
}
