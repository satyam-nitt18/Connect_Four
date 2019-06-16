package com.example.connectfour;

public class Cell {

    public boolean empty;
    public int player;

    public Cell(){
        empty=true;
    }

    public void setPlayer(int player){
        this.player=player;
        empty=false;
    }
}
