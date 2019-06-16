package com.example.connectfour;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
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

import static java.security.AccessController.getContext;


public class activity_game extends AppCompatActivity {

    private Board board;
    private View boardView;
    private MyView customView;
    private ViewHolder viewHolder;
    private int[][] cells;
    public static int numCols=7;
    public static int numRows=6;
    private Button resetButton, undoButton;
    LinearLayout linearLayout;
    public static float cellWidth, cellHeight;
    private Canvas actCanvas;
    private Paint fillPaint;
    public boolean action=false;


    private class ViewHolder{

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
        cellHeight=1182/numRows;
        cellWidth=720/numCols;

        customView=new MyView(this);
        linearLayout.addView(customView);

        customView.setGrid(numRows, numCols);

        constructCells();
        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    int col=colSelected(event.getX());
                    if(col != -1)
                        dropCoin(col);
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
    public void dropCoin(int col)
    {
        if(board.Won==true)
            return;
        int row=board.lastAvailableRow(col);
        if(row == -1)
            return;

        board.occupyCell(row, col, board.turn);
        float x=(cellWidth*(col+1))/2;
        float y=1182-((cellHeight*(row+1))/2 + 50);
        customView.draw(actCanvas, x, y, board.turn);
        showToast("x ="+x+"y ="+y);


         action=true;
         customView=new MyView(this);
         action=false;
       actCanvas.save();
        actCanvas.translate(0, y);
        customView.draw(actCanvas, x, y, board.turn);

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actCanvas.restore();
            }
        });

        if(board.checkForWin()==true)
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
    }
    private void constructCells()
    {
        cells = new int[numRows][numCols];
        for (int r=0; r<numRows; r++)
                       for (int c = 0; c < numCols; c++)
                           cells[r][c] = 0;
    }
    public int colSelected(float x)
    {
        int col = (int) x / (int) cellWidth;
        if(col < 0 || col > numCols)
            return -1;
        return col;
    }
    private int turnResource()
    {
        if(board.turn==2)
            return R.drawable.yellow;
        else
            return R.drawable.red;
    }
    private void reset()
    {
        board.reset();
        viewHolder.winText.setVisibility(View.INVISIBLE);
        viewHolder.turnIndicatorImage.setImageResource(turnResource());
        for(int r=0;r<numRows;r++)
            for(int c=0;c<numCols;c++)
            cells[r][c]=0;
    }
}
