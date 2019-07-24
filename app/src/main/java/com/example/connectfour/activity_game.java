package com.example.connectfour;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class activity_game extends AppCompatActivity {

    public Board board;
    private Paint fillPaint;
    public MyView customView;
    public ViewHolder viewHolder;
    public int[][] cells;
    public static int numCols=7;
    public static int numRows=6;
    public Button resetButton, undoButton;
    LinearLayout linearLayout;
    public Canvas actCanvas;
    public float coin_x, coin_y, translateY;
    public Bitmap coin1, coin2;

    private static final String TAG = "activity_game";

    public class ViewHolder{

        public TextView winText;
        public ImageView turnIndicatorImage;
    }
    public void showToast(String text){
        Toast.makeText(activity_game.this, text, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        actCanvas=new Canvas();
        board=new Board(numCols, numRows);


        linearLayout=(LinearLayout)findViewById(R.id.canvasLayout);
        resetButton=findViewById(R.id.reset_button);
        undoButton=findViewById(R.id.undo_button);
        constructCells();


        customView=new MyView(this);
        linearLayout.addView(customView);

        customView.setGrid(numRows, numCols);

        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    int col=customView.colSelected(event.getX());
                    if(col != -1)
                        drawCoin(col);
                    }
                if(event.getAction()==MotionEvent.ACTION_UP) {
                    droppedCoin();
                }
                return true;
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        viewHolder=new ViewHolder();
        viewHolder.turnIndicatorImage=findViewById(R.id.turn_indicator_image);
        viewHolder.turnIndicatorImage.setImageResource(turnResource());
        viewHolder.winText=(TextView)findViewById(R.id.winner_text);
        viewHolder.winText.setVisibility(View.INVISIBLE);
    }
    public void drawCoin(int col) {
        customView.initialize(cells);
        
        if (board.Won)
            return;
        int row = board.lastAvailableRow(col);
        if (row == -1)
            return;

        board.occupyCell(row, col, board.turn);
        cells[row][col]=board.turn;

        customView.coin_x=customView.getCoordinateX(row, col);
        customView.translateY=customView.getTranslateY(row);
        /*if(board.turn==1)
            coin1 = BitmapFactory.decodeResource(getResources(), R.drawable.red_coin);
         else
             coin2=BitmapFactory.decodeResource(getResources(), R.drawable.yellow_coin);*/

        customView.canDraw=true;

        //actCanvas.drawBitmap(coin, coin_x, coin_y, null);
         Log.d(TAG, "Bitmap drawn");

    }


    public void droppedCoin(){
       /* while(coin_y!=translateY)
        {
            coin_y=coin_y+10;
            actCanvas.drawBitmap(coin, coin_x, coin_y, null);
        }*/


       // undoButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
            //    actCanvas.restore();
          //  }
        //});

        if(board.checkForWin())
        {
            int color;
            if(board.turn==1)
                color= Color.RED;
            else
                color=Color.YELLOW;
            viewHolder.winText.setTextColor(color);
            viewHolder.winText.setVisibility(View.VISIBLE);
        }
        else
        {
            board.changePlayer();
            viewHolder.turnIndicatorImage.setImageResource(turnResource());
        }
        customView.initialize(cells);

    }
    public void constructCells()
    {
        cells = new int[numRows][numCols];
        for (int r=0; r<numRows; r++)
                       for (int c = 0; c < numCols; c++)
                           cells[r][c] = 0;
    }

    public int turnResource()
    {
        if(board.turn==2)
            return R.drawable.yellow;
        else
            return R.drawable.red;
    }
    public void reset()
    {
        board.reset();
        viewHolder.winText.setVisibility(View.INVISIBLE);
        viewHolder.turnIndicatorImage.setImageResource(turnResource());
        for(int r=0;r<numRows;r++)
            for(int c=0;c<numCols;c++)
            cells[r][c]=0;
    }
}
