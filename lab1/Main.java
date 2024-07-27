import java.util.Scanner;
import java.util.InputMismatchException;

interface IMove
{
  public void move(int xOffset, int yOffset, Hero hero);
}

class Walk implements IMove
{  
  @Override
  public void move(int xOffset, int yOffset, Hero hero)
  {
    Main.doMove(xOffset, yOffset, hero, 1, 2, "Hero walked for a day. Distance left: ",
     "Hero walked to position");
  }
}

class Ride implements IMove
{
  @Override
  public void move(int xOffset, int yOffset, Hero hero)
  {
    Main.doMove(xOffset, yOffset, hero, 5, 4, "Hero rode horseback for a day. Distance left: ",
     "Hero rode to position");
  }
}

class Teleport implements IMove
{
  @Override
  public void move(int xOffset, int yOffset, Hero hero)
  {
    if (hero.getHeroMana() >= 30)
    {
      hero.setHeroPositionX(hero.getHeroPositionX() + xOffset);
      hero.setHeroPositionY(hero.getHeroPositionY() + yOffset);
      hero.setHeroMana(hero.getHeroMana() - 30);
      System.out.println("Hero spent 30 mana and teleported to position (" + hero.getHeroPositionX() + ";" + hero.getHeroPositionY() + ")\n");
    }
    else
    {
      System.out.println("Hero doesn't have enough mana to teleport!\n");
    }
  }
}

class Hero
{
  private int heroPositionX = 0;
  private int heroPositionY = 0; 
  private int heroMana = 100;

  public int getHeroPositionX()
  {
    return this.heroPositionX;
  }

  public int getHeroPositionY()
  {
    return this.heroPositionY;
  }

  public int getHeroMana()
  {
    return this.heroMana;
  }

  public void setHeroPositionX(int x)
  {
    this.heroPositionX = x;
  }

  public void setHeroPositionY(int y)
  {
    this.heroPositionY = y;
  }

  public void setHeroMana(int mana)
  {
    this.heroMana = mana;
  }

  public void move(IMove moveMethod, int destX, int destY)
  {
    moveMethod.move(destX, destY, this);
  }
  public void mana()
  {
    System.out.println("Hero now has "+ heroMana + " mana\n");
  }
  public void rest()
  {
    if (heroMana < 100)
        {
          System.out.println("Hero rested for a day and refilled 20 mana\n");
          heroMana += 20;
        }
        else
        {
          System.out.println("Hero rested for a day, but didn't refill mana since it was full\n");
        }
  }
}

public class Main 
{
  public static void doMove(int xOffset, int yOffset, Hero hero, int manaRefill, int speed, String moveMessage, String endMessage)
  {
    double distance = Math.sqrt(xOffset * xOffset + yOffset * yOffset);
    distance -= speed;
    hero.setHeroMana(hero.getHeroMana() + manaRefill);
    while (distance > 0)
    {
      System.out.println(moveMessage + distance);
      if (hero.getHeroMana() < 100)
      {
        hero.setHeroMana(hero.getHeroMana() + speed);
      }
      Main.Sleep(1000);
      distance -= speed;
    }
    hero.setHeroPositionX(hero.getHeroPositionX() + xOffset);
    hero.setHeroPositionY(hero.getHeroPositionY() + yOffset);
    System.out.println(endMessage + " (" + hero.getHeroPositionX() + ";" + hero.getHeroPositionY() + ")\n");
  }

  public static void Sleep(int milliseconds)
  {
    try
    {
      Thread.sleep(milliseconds);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public static void main(String[] args) 
  {
    Scanner scan = new Scanner(System.in);
    var hero = new Hero();
    String command = new String();

    System.out.println("Hero is at (0;0) now\nEnter a command(Walk/Ride/Teleport/Rest/Mana/Exit).\n Move commnads require offsets. Example:\nWalk 1 2\n");

    while (!command.equals("Exit"))
    {
      command = scan.next();
      try 
      {
        switch (command)
        {
          case "Walk":
            hero.move(new Walk(), scan.nextInt(), scan.nextInt());
            break;

          case "Ride":
            hero.move(new Ride(), scan.nextInt(), scan.nextInt());
            break;

          case "Teleport":
            hero.move(new Teleport(), scan.nextInt(), scan.nextInt());
            break;

          case "Mana":
            hero.mana();
            break;

          case "Rest":
            hero.rest();
            break;

          case "Exit":
            break;

          default:
            System.out.println("Unexpected input!\n");
            break;
        }
      }
      catch (InputMismatchException e)
      {
          System.out.println("Unexpected arguments!\n");
          scan.nextLine();
      }
    }
  scan.close();
  }
}