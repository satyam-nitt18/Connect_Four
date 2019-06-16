package com.example.connectfour;

public class Board {

    private int numCols, numRows;
    public boolean Won;
    private Cell[][] cells;
    public int turn;

    public int players[]={1, 2};

    public Board(int cols, int rows)
    {
        numCols=cols;
        numRows=rows;
        cells=new Cell[numRows][numCols];
        reset();
    }

    public void reset()
    {
        Won=false;
        turn=players[0];
        for(int row=0;row<numRows;row++)
        {
            for (int col=0;col<numCols;col++)
            {
                cells[row][col]=new Cell();
            }
        }
    }

    public int lastAvailableRow(int col)
    {
        for(int row=numRows-1;row>=0;row--)
        {
            if(cells[row][col].empty)
                return row;
        }
        return -1;
    }

    public void occupyCell(int row, int col, int player)
    {
        cells[row][col].setPlayer(player);
    }

    public void changePlayer()
    {
        if(turn==players[0])
            turn=players[1];
        else
            turn=players[0];
    }

    public boolean checkForWin() {
        for (int col = 0; col < numCols; col++) {
            if (isConnected(turn, 0, 1, col, 0, 0) || isConnected(turn, 1, 1, col, 0, 0) || isConnected(turn, -1, 1, col, 0, 0)) {
                Won = true;
                return true;
            }
        }
        for (int row = 0; row < numRows; row++) {
            if (isConnected(turn, 1, 0, 0, row, 0) || isConnected(turn, 1, 1, 0, row, 0) || isConnected(turn, -1, 1, numCols - 1, row, 0)) {
                Won = true;
                return true;
            }
        }
        return false;
    }

    public boolean isConnected(int player, int dirX, int dirY, int col, int row, int count) {
        if (count >= 4) {
            return true;
        }
        if (col < 0 || col >= numCols || row < 0 || row >= numRows) {
            return false;
        }
        if (cells[row][col].player == player) {
            return isConnected(player, dirX, dirY, col + dirX, row + dirY, count + 1);
        } else {
            return isConnected(player, dirX, dirY, col + dirX, row + dirY, 0);
        }
    }

}
