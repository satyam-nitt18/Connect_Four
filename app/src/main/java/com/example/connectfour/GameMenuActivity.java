package com.example.connectfour;

import android.content.Intent;
import android.os.Bundle;
import com.example.connectfour.MenuView;

import android.support.v7.app.AppCompatActivity;

public class GameMenuActivity extends AppCompatActivity implements GameMenuController.MenuControllerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        MenuView menuView = (MenuView) findViewById(R.id.menuView);
        GameMenuController gameMenuController =new GameMenuController(this,  menuView);
        menuView.setListeners(gameMenuController);

    }

    @Override
    public void onPlay() {
        Intent intent = new Intent(this,activity_game.class);
        startActivity(intent);
    }
}
