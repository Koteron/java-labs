package lab3;

public abstract class Chordate 
{
    protected String color_;
    protected int weight_;

    public Chordate(String color, int weight) 
    {
        this.color_ = color;
        this.weight_ = weight;
    }

    public int getWeight() 
    {
        return weight_;
    }

    public String getColor() 
    {
        return color_;
    }

    public abstract void voice();

    @Override
    public String toString() 
    {
        return color_ + " " + weight_;
    }
}
