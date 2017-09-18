package com.javahelps.tictactoe;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Sneha on 28-08-2017.
 */

public class MyButton extends Button{

    int player ;
    public MyButton(Context context) {
        super(context);
        player = MainActivity.NO_PLAYER ;
    }
    int getPlayer(){
        return  player;
    }

}
