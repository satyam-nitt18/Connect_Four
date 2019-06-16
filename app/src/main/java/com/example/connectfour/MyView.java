package com.example.connectfour;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyView extends View {


         public Paint mPaint, fillPaint, circlePaint;
        private activity_game obj_main;
        private int numRows, numCols, screenWidth, screenHeight;
        private float radius;
        private Board board;



        public MyView(Context context){
            super(context);

            DisplayMetrics displayMetrics=new DisplayMetrics();
            ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            screenWidth=displayMetrics.widthPixels;
            screenHeight=displayMetrics.heightPixels;

            mPaint=new Paint();
            mPaint.setAntiAlias(true);

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(5);

            fillPaint=new Paint();
            fillPaint.setAntiAlias(true);
            fillPaint.setColor(Color.BLUE);
            fillPaint.setStyle(Paint.Style.FILL);

            circlePaint=new Paint();
            circlePaint.setStyle(Paint.Style.FILL);
            circlePaint.setAntiAlias(true);
            circlePaint.setColor(Color.WHITE);
        }
        public void setGrid(int rows, int cols){
            this.numCols=cols;
            this.numRows=rows;
        }
    public void showToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void draw(Canvas canvas, float x, float y, int player)
    {
        if(player==1)
            mPaint.setColor(Color.RED);
        else
            mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(x, y, radius, mPaint);
        invalidate();

    }
   
    @Override
    public boolean performClick() {
        super.performClick();

        showToast("Next Turn");


        return true;
    }

    @Override
        public void onDraw(final Canvas canvas){
            super.onDraw(canvas);
            obj_main=new activity_game();
            board=new Board(obj_main.numRows, obj_main.numCols);
            canvas.drawRect(getLeft(), 500, getRight(), getBottom(), fillPaint);

            float cx=(getRight()-getLeft())/(numCols*2);
            float cy=(getBottom()-getTop()-500)/(numRows*2);
            radius=(float)(0.9*cx);
            float xPadding=(float)(0.1*cx);
            float yPadding=(float)((2*cy-2*radius)/2);

            for(int r=1; r<=numRows;r++)
            {
                for(int c=1;c<=numCols;c++)
                {
                    canvas.drawCircle(cx, getBottom()-cy, radius, circlePaint);

                    cx +=(getRight()-getLeft())/(numCols*2) +xPadding+radius;
                }
                cx=(getRight()-getLeft())/(numCols*2);
                cy += (getBottom()-getTop()-500)/(numRows*2)+radius+yPadding;

            }

            //showToast("getRight ="+getRight()+"getLeft"+getLeft());
            showToast("getTop ="+getTop()+"getBottom ="+getBottom());

        }




    }


