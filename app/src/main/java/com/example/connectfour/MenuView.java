package com.example.connectfour;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class MenuView extends RelativeLayout {
    public MenuView(Context context) {
        super(context);
    }

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setListeners(GameMenuController gameMenuController) {

        findViewById(R.id.play).setOnClickListener(gameMenuController);
        ((RadioGroup) findViewById(R.id.play_with)).setOnCheckedChangeListener(gameMenuController);
        ((SeekBar) findViewById(R.id.difficulty)).setOnSeekBarChangeListener(gameMenuController);

    }

    public void setPlayWith(String  opponent){
        if(opponent == "AI"){
            ((RadioGroup) findViewById(R.id.play_with)).check(R.id.play_with_ai);
            findViewById(R.id.level).setVisibility(VISIBLE);

        }else {
            ((RadioGroup) findViewById(R.id.play_with)).check(R.id.play_with_friend);
            findViewById(R.id.level).setVisibility(INVISIBLE);
           // ((RadioButton) findViewById(R.id.first_turn_player2)).setText(getContext().getString(R.string.opponent_player));

        }
    }

    public void setDifficulty(int difficulty){
        ((SeekBar) findViewById(R.id.difficulty)).setProgress(difficulty);
    }

    public void setUpMenu() {
        setPlayWith("Player");
        setDifficulty(0);

    }
}