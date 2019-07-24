package com.example.connectfour;
import com.example.connectfour.MenuView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class GameMenuController implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener {
    private final MenuView mMenuView;
    private final MenuControllerListener mListener;

    public GameMenuController(MenuControllerListener mListener, MenuView mMenuView) {
        this.mMenuView = mMenuView;
        this.mListener = mListener;
        this.mMenuView.setUpMenu();
    }


    @Override
    public void onClick(View v) {
        mListener.onPlay();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.play_with_ai:
               // mGameRules.setRule(GameRules.OPPONENT, Opponent.AI);
                mMenuView.setPlayWith("AI");
              //  mMenuView.setDifficulty(mGameRules.getRule(GameRules.LEVEL));
                break;
            case R.id.play_with_friend:
              //  mGameRules.setRule(GameRules.OPPONENT, Opponent.PLAYER);
                mMenuView.setPlayWith("Friend");
                //mMenuView.setDifficulty(mGameRules.getRule(GameRules.LEVEL));
                break;

        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //mGameRules.setRule(GameRules.LEVEL, progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public interface MenuControllerListener {
        void onPlay();
    }
}
