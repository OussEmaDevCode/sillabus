package com.example.button.wordsandsillabus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    CountDownTimer cTimer = null;
    int scorep1;
    int scorep2;
    String yourname;
    String yourfriend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        yourname = intent.getStringExtra("yourname");
        yourfriend = intent.getStringExtra("yourfriend");
        final TextView yournametext = findViewById(R.id.yourname);
        yournametext.setText(yourname);
        final TextView yourfriendtext = findViewById(R.id.yourfriend);
        yourfriendtext.setText(yourfriend);
        final TextView countdown1 = findViewById(R.id.countdown1);
        final TextView countdown2 = findViewById(R.id.countdown2);
        final View player1 = findViewById(R.id.player1);
        final View player2 = findViewById(R.id.player2);
        player2.setClickable(false);
        final TextView score1 = findViewById(R.id.score1);
        final TextView score2 = findViewById(R.id.score2);
        startTimer1(countdown1);
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer1(countdown1);
                player1.setClickable(false);
                player2.setClickable(true);
                startTimer2(countdown2);
                scorep1++;
                score1.setText(String.valueOf(scorep1));
            }
        });
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer2(countdown2);
                player1.setClickable(true);
                player2.setClickable(false);
                startTimer1(countdown1);
                scorep2++;
                score2.setText(String.valueOf(scorep1));
            }
        });

    }

    void startTimer1(final TextView countdown1) {
        cTimer = new CountDownTimer(26000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdown1.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                ShowDialogBox(yourfriend,yourname);
            }
        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer1(TextView contdown1) {
        if(cTimer!=null)
            cTimer.cancel();
        contdown1.setText(String.valueOf(25));
    }
    void startTimer2(final TextView countdown2) {
        cTimer = new CountDownTimer(26000, 1000) {
            public void onTick(long millisUntilFinished) {
                countdown2.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                ShowDialogBox(yourname,yourfriend);
            }
        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer2(TextView contdown2) {
        if(cTimer!=null)
            cTimer.cancel();
        contdown2.setText(String.valueOf(25));


    }


    public void ShowDialogBox(String winner,String Loser){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        if(scorep1==scorep2){
            alertDialog.setTitle("it's a draw");
            alertDialog.setMessage("Better luck next time for both of you");
        }
        else {
            alertDialog.setTitle(winner + " won!");
            alertDialog.setMessage("Better luck next time "+Loser);

        }
        alertDialog.setIcon(R.drawable.ic_flare_black_24dp);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("replay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this,choose.class));
            }
        });
        AlertDialog alertDialog1 = alertDialog.create();

        try {
            alertDialog1.show();
        }
        catch (WindowManager.BadTokenException e) {
            Log.e("problem","activity");
        }
    }
}
