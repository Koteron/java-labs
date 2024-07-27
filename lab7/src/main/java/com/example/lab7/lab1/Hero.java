package com.example.lab7.lab1;

public class Hero {
    private int heroPositionX = 0;
    private int heroPositionY = 0;
    private int heroMana = 100;

    public int getHeroPositionX() {
        return this.heroPositionX;
    }

    public int getHeroPositionY() {
        return this.heroPositionY;
    }

    public int getHeroMana() {
        return this.heroMana;
    }

    public void setHeroPositionX(int x) {
        this.heroPositionX = x;
    }

    public void setHeroPositionY(int y) {
        this.heroPositionY = y;
    }

    public void setHeroMana(int mana) {
        this.heroMana = mana;
    }

    public String move(IMove moveMethod, int destX, int destY) {
        return moveMethod.move(destX, destY, this);
    }

    public String mana()
    {
        return "Hero now has " + heroMana + " mana\n";
    }

    public String rest() {
        if (heroMana < 100)
        {
            heroMana += 20;
            return "Hero rested for a day and refilled 20 mana\n";
        }
        else
        {
            return "Hero rested for a day, but didn't refill mana since it was full\n";
        }
    }
}
