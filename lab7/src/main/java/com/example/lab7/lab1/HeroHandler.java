package com.example.lab7.lab1;

import java.util.Scanner;
import java.util.InputMismatchException;

interface IMove
{
  String move(int xOffset, int yOffset, Hero hero);
}

class Walk implements IMove
{  
  @Override
  public String move(int xOffset, int yOffset, Hero hero)
  {
    return HeroHandler.doMove(xOffset, yOffset, hero, 1, 2, "Hero walked for a day. Distance left: ",
     "Hero walked to position");
  }
}

class Ride implements IMove
{
  @Override
  public String move(int xOffset, int yOffset, Hero hero)
  {
    return HeroHandler.doMove(xOffset, yOffset, hero, 5, 4, "Hero rode horseback for a day. Distance left: ",
     "Hero rode to position");
  }
}

class Teleport implements IMove
{
  @Override
  public String move(int xOffset, int yOffset, Hero hero)
  {
    if (hero.getHeroMana() >= 30)
    {
      hero.setHeroPositionX(hero.getHeroPositionX() + xOffset);
      hero.setHeroPositionY(hero.getHeroPositionY() + yOffset);
      hero.setHeroMana(hero.getHeroMana() - 30);
      return "Hero spent 30 mana and teleported to position (" + hero.getHeroPositionX() + ";" + hero.getHeroPositionY() + ")\n";
    }
    else
    {
      return "Hero doesn't have enough mana to teleport!\n";
    }
  }
}

public class HeroHandler
{
  public static String doMove(int xOffset, int yOffset, Hero hero, int manaRefill, int speed, String moveMessage, String endMessage)
  {
    StringBuilder doMoveOutput = new StringBuilder();
    double distance = Math.sqrt(xOffset * xOffset + yOffset * yOffset);
    distance -= speed;
    hero.setHeroMana(hero.getHeroMana() + manaRefill);
    while (distance > 0)
    {
      doMoveOutput.append(moveMessage + distance + "\n");
      if (hero.getHeroMana() < 100)
      {
        hero.setHeroMana(hero.getHeroMana() + speed);
      }
      HeroHandler.Sleep(1000);
      distance -= speed;
    }
    hero.setHeroPositionX(hero.getHeroPositionX() + xOffset);
    hero.setHeroPositionY(hero.getHeroPositionY() + yOffset);
    doMoveOutput.append(endMessage + " (" + hero.getHeroPositionX() + ";" + hero.getHeroPositionY() + ")\n");
    return doMoveOutput.toString();
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

  public static String start(String args) {
    StringBuilder output = new StringBuilder();
    Scanner scan = new Scanner(args);
    var hero = new Hero();
    String command = new String();

    //System.out.println("Hero is at (0;0) now\nEnter a command(Walk/Ride/Teleport/Rest/Mana/Exit).\n Move commnads require offsets. Example:\nWalk 1 2\n");

    while (!command.equals("Exit")) {
      command = scan.next();
      try {
        switch (command) {
          case "Walk":
            output.append(hero.move(new Walk(), scan.nextInt(), scan.nextInt()));
            break;

          case "Ride":
            output.append(hero.move(new Ride(), scan.nextInt(), scan.nextInt()));
            break;

          case "Teleport":
            output.append(hero.move(new Teleport(), scan.nextInt(), scan.nextInt()));
            break;

          case "Mana":
            output.append(hero.mana());
            break;

          case "Rest":
            output.append(hero.rest());
            break;

          case "Exit":
            break;

          default:
            output.append("Unexpected input!\n");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Unexpected arguments!\n");
        scan.nextLine();
      }
    }
    scan.close();
    return output.toString();
  }
}