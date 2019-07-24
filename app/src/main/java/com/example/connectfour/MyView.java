package com.example.connectfour;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MyView extends View {


    public Paint mPaint, fillPaint, circlePaint;
    private activity_game obj_main;
    private int numRows, numCols, screenWidth, screenHeight;
    private float radius, x, y, translate;
    private Board board;
    public Canvas canvas;
    private static final String TAG = "MyView";
    public static float cellWidth, cellHeight;
    private float[] arrcy, arrcx;
    private int[][] cells;
    public boolean canDraw;
    public float coin_x, coin_y, translateY;


    public MyView(Context context) {
        super(context);
        obj_main = new activity_game();
        board = new Board(numRows, numCols);

        canvas = new Canvas();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

        fillPaint = new Paint();
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(Color.BLUE);
        fillPaint.setStyle(Paint.Style.FILL);

        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setAntiAlias(true);

    }

    public void setGrid(int rows, int cols) {
        this.numCols = cols;
        this.numRows = rows;
        cellHeight = (screenHeight - 850) / (float) (numRows);
        cellWidth = (screenWidth) / (float) (numCols);
        showToast("cellwidth =" + cellWidth);
        arrcy = new float[numRows];
        arrcx = new float[numCols];
    }

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public float getCoordinateX(int row, int col) {

        x = (cellWidth * col) + (cellWidth / 2);
        return x;
    }
    public float getTranslateY(int row){
        float y=cellHeight*row;
        return getCoordinateY()+y+radius;
    }
    public float getCoordinateY(){
        y = screenHeight - 900;
        return y;
    }

    public int colSelected(float x) {
        int col = (int) (x / cellWidth);
        if (col < 0 || col > numCols)
            return -1;
        return col;
    }

    public void initialize(int[][] cells) {
        this.cells = new int[numRows][numCols];
        cells = new int[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++)
                this.cells[r][c] = cells[r][c];
        }

    }

    @Override
    public boolean performClick() {
        super.performClick();

        showToast("Next Turn");


        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(getLeft(), screenHeight - 850, getRight(), getBottom(), fillPaint);

        float cx = (getRight() - getLeft()) / (float) (numCols * 2);
        float cy = (getBottom() - getTop() - 500) / (float) (numRows * 2);
        radius = (float) (0.9 * cx);
        float xPadding = (float) (0.1 * cx);
        float yPadding = (float) ((2 * cy - 2 * radius) / 2);

        for (int r = 1; r <= numRows; r++) {
            for (int c = 1; c <= numCols; c++) {
                //showToast("row ="+r+"cy ="+(getBottom()-cy));
                circlePaint.setColor(Color.WHITE);


                canvas.drawCircle(cx, getBottom() - cy, radius, circlePaint);
                arrcy[r - 1] = getBottom() - cy;
                arrcx[c - 1] = cx;

                cx += (getRight() - getLeft()) / (float) (numCols * 2) + xPadding + radius;
            }
            cx = (getRight() - getLeft()) / (float) (numCols * 2);
            cy += (getBottom() - getTop() - 500) / (float) (numRows * 2) + radius + yPadding;

        }

        while (canDraw){
            coin_y=getCoordinateY();
            showToast(" coin x= "+ coin_x+"coin y = "+coin_y+"trans = "+translateY);
            motion();
            canvas.drawCircle(coin_x, coin_y, radius, fillPaint);
        }
        invalidate();

    }
    public void motion(){

        if(coin_y<= translateY) {
            coin_y = coin_y+ 20;
        }
        if(coin_y>translateY)
            canDraw=false;
    }

}



