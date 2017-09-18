package com.javahelps.tictactoe;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener, View.OnClickListener {

    public final static int NO_PLAYER = 0;
    public final static int PLAYER_1 = 1;
    public final static int PLAYER_2 = 2;
    public final static int INCOMPLETE = 0;
    public final static int  DRAW = 3;
    public final static int PLAYER_1_WINS = 1;
    public final static int PLAYER_2_WINS = 2;
    static LinearLayout rootLayout ;
    static LinearLayout rowLayout[] ;
    static MyButton button[][] ;
    public static int size = 3 ;
    boolean player1Turn = true;
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = (LinearLayout)findViewById(R.id.rootLayout);
        setUpBoard();
    }

    public void setUpBoard(){
        button = new MyButton[size][size];
        rowLayout = new LinearLayout[size];
        rootLayout.removeAllViews();
        for (int i=0 ; i<size ; i++){
            rowLayout[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , 0 , 1f);
            params.setMargins(5,5,5,5);
            rowLayout[i].setLayoutParams(params);
            rowLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            rootLayout.addView(rowLayout[i]);
        }

        for (int i=0 ; i<size ; i++){
            for (int j=0 ; j<size ; j++){
                button[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0 ,ViewGroup.LayoutParams.MATCH_PARENT , 1f);
                params.setMargins(5,5,5,5);
                button[i][j].setLayoutParams(params);
                button[i][j].setTextSize(50);
                button[i][j].setOnClickListener(this);
                button[i][j].setTextColor(ContextCompat.getColor(this , R.color.colorPrimary));
                rowLayout[i].addView(button[i][j]);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.newGame){
            resetBoard();
        }else if(id == R.id.size3){
            resetBoard();
            size = 3 ;
            setUpBoard();
        }
        else if (id == R.id.size4){
            resetBoard();
            size = 4;
            setUpBoard();
        }
        else if (id == R.id.size5){
            resetBoard();
            size = 5;
            setUpBoard();
        }
        return true;
    }

    public void resetBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j].player = NO_PLAYER;
                button[i][j].setText("");
            }
        }
        player1Turn = true;
        gameOver = false;
    }

    @Override
    public void onClick(View v) {
        MyButton button = (MyButton)v;
        if (gameOver){
            Toast.makeText(this , "GAME OVER" , Toast.LENGTH_LONG).show();
            return;
        }
        if (button.player != NO_PLAYER){
            Toast.makeText(this , "INVALID MOVE" , Toast.LENGTH_SHORT).show();
            return;
        }
        if (player1Turn){
            button.player = PLAYER_1;
            button.setText("0");
        }
        else {
            button.player = PLAYER_2;
            button.setText("X");
        }
        int status = checkStatus();
        if(status == DRAW){
            Toast.makeText(this, "Draw ", Toast.LENGTH_SHORT).show();
            gameOver = true;
        }
        else if(status == PLAYER_1_WINS){
            Toast.makeText(this, " O WINS ", Toast.LENGTH_SHORT).show();
            gameOver = true;

        }
        else if(status == PLAYER_2_WINS){
            Toast.makeText(this, "X Wins ", Toast.LENGTH_SHORT).show();
            gameOver = true;
        }
        player1Turn = !player1Turn ;
    }
    public int checkStatus(){
        for (int i=0 ; i<size ; i++){
            boolean flag=true;
            for (int j=0 ; j<size ; j++) {
                if (button[i][j].player == NO_PLAYER || button[i][0].getPlayer() != button[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (button[i][0].getPlayer() == PLAYER_1)
                    return PLAYER_1_WINS;
                else
                    return PLAYER_2_WINS;
            }
        }
        for (int i=0 ; i<size ; i++){
            boolean flag=true;
            for (int j=0 ; j<size ; j++) {
                if (button[j][i].player == NO_PLAYER || button[0][i].getPlayer() != button[j][i].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (button[0][i].getPlayer() == PLAYER_1)
                    return PLAYER_1_WINS;
                else
                    return PLAYER_2_WINS;
            }
        }
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            if (button[i][i].getPlayer() == NO_PLAYER || button[0][0].getPlayer() != button[i][i].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (button[0][0].getPlayer() == PLAYER_1) {
                return PLAYER_1_WINS;
            } else {
                return PLAYER_2_WINS;
            }
        }
        flag = true;
        for (int i = size - 1; i >= 0; i--) {
            int col = size - 1 - i;
            if (button[i][col].getPlayer() == NO_PLAYER ||
                    button[size - 1][0].getPlayer() != button[i][col].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            if (button[size - 1][0].getPlayer() == PLAYER_1) {
                return PLAYER_1_WINS;
            } else {
                return PLAYER_2_WINS;
            }
        }
        for (int i=0 ; i<size ; i++){
            for (int j=0 ; j<size ; j++){
                if (button[i][j].getPlayer() == NO_PLAYER)
                    return INCOMPLETE;
            }
        }
        return DRAW ;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
