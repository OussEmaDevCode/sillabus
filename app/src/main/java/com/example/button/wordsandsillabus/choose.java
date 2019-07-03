package com.example.button.wordsandsillabus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class choose extends AppCompatActivity {
   final Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        TextView letter  =  findViewById(R.id.two);
        TextView number = findViewById(R.id.four);
        final EditText yourname = findViewById(R.id.yourname);
        final EditText yourfriend = findViewById(R.id.yourfriend);
        String[] letterarray = {"b","c","d","e","f","g","j","l","m","p","r","s","t","v"};
        int rannum = (int)(Math.random()*((8-3)+1))+3;
        int rannletter = (int)(Math.random()*((letterarray.length)+1));
        number.setText(String.valueOf(rannum));
        letter.setText(letterarray[rannletter]);
        Button lets = findViewById(R.id.lets);
        Button regenerate = findViewById(R.id.regenerate);
        Button howto = findViewById(R.id.how_to);
        howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showDialogBox();
            }
        });
        lets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(choose.this,MainActivity.class);
                i.putExtra("yourname",yourname.getText().toString());
                i.putExtra("yourfriend",yourfriend.getText().toString());
                startActivity(i);
            }
        });
        regenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

    }

    public void showDialogBox(){
        AlertDialog.Builder alertbuilder = new AlertDialog.Builder(this);
        alertbuilder.setTitle("How to play ?");
        alertbuilder.setMessage("Every time you open the app or click \"regenerate\" there is a letter and a number shown. " +
                "your goal is to find a world that starts with the indicated letter and has the indicated number of letters before the " +
                "timer ends and when you find it just click your side button. The loser is the player to not find a world in that certain amout of time");
        alertbuilder.setIcon(R.drawable.ic_question_mark_button);
        alertbuilder.setPositiveButton("got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alertbuilder.create();
        try {
            alertDialog.show();
        }
        catch (WindowManager.BadTokenException e) {
            Log.e("problem","activity");
        }
    }
}
