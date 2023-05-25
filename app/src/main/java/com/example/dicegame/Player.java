package com.example.dicegame;

public class Player {

    private int points;
    private int amountPot;
    private int chancesPlayed;
    private int amountWon;


    public Player() {

        //Starts at 0 points
        points=0;
        //Default pot $50
        amountPot=50;
        //Number of chances played
        chancesPlayed=0;
        //Amount won, it starts with -50 because they paid the pot
        amountWon=-50;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points += points;
    }

    public int getAmountPot() {
        return amountPot;
    }

    public void setAmountPot(int amountPot) {
        this.amountPot = amountPot;
    }

    public int getChancesPlayed() {
        return chancesPlayed;
    }

    public void setChancesPlayed(int chancesPlayed) {
        this.chancesPlayed = chancesPlayed;
    }

    public int getAmountWon() {
        return amountWon;
    }

    public void setAmountWon(int amountWon) {
        this.amountWon = amountWon;
    }

    public void resetGame(){
        points=0;
        chancesPlayed=0;

    }
}
