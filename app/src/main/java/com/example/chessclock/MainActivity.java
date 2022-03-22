package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long  START_TIME1=600000;
    private static final long  START_TIME2=600000;


    private TextView txtCountDown;
    private TextView txtCountDown2;
    private Button btnChangeClock;
    private Button btnReset;
    private Button btnPause;

    private RelativeLayout relativeLayout;
    private RelativeLayout relativeLayout2;

    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimer2;


    private boolean timerRun;
    private boolean timerRun2;

     private long timeLeft=START_TIME1;
     private long timeLeft2=START_TIME2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCountDown=findViewById(R.id.text_view_countdown1);
        txtCountDown2=findViewById(R.id.text_view_countdown2);

        relativeLayout=findViewById(R.id.layout_1);
        relativeLayout2=findViewById(R.id.layout_2);

        btnChangeClock=findViewById(R.id.btn_Changeclock);
        btnReset=findViewById(R.id.btn_Reset);
        btnPause=findViewById(R.id.btn_Pause);

        timerRun=false;
        timerRun2=false;

        relativeLayout2.setBackgroundColor(Color.GRAY);
        relativeLayout.setBackgroundColor(Color.GRAY);

        txtCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout2.setBackgroundColor(Color.YELLOW);
                relativeLayout.setBackgroundColor(Color.GRAY);
                if (!timerRun2){
                    startTimerForTxt2();
                }

                if (timerRun){
                    Pause1();
                }
            }
        });

        txtCountDown2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.setBackgroundColor(Color.YELLOW);
                relativeLayout2.setBackgroundColor(Color.GRAY);
                if (!timerRun){
                    startTimerForTxt1();
                }
                if (timerRun2){
                    Pause2();

                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRun2==true){Pause2();}
                if (timerRun==true){Pause1();}
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRun){
                    Pause1();
                }if (timerRun2){
                    Pause2();
                }
                if (timerRun == false && timerRun2==false){
                    resetTimer();
                }

            }
        });
    }

    private void startTimerForTxt1(){
        countDownTimer = new CountDownTimer(timeLeft, 1000)
        {
            @Override
            public void onTick(long l) {
                timeLeft=l;
                updateCount();
            }

            @Override
            public void onFinish() {
                timerRun=false;
            }
        }.start();
        timerRun=true;
    }
    private  void updateCount(){
        int minutes=(int) (timeLeft / 1000)/60;
        int seconds=(int) (timeLeft / 1000)%60;

        String timeLeftformat=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        txtCountDown.setText(timeLeftformat);

    }
    private void Pause1(){
        countDownTimer.cancel();
        timerRun=false;
    }

    private void startTimerForTxt2(){
        countDownTimer2 = new CountDownTimer(timeLeft2, 1000)
        {
            @Override
            public void onTick(long l) {
                timeLeft2=l;
                updateCount2();
            }

            @Override
            public void onFinish() {
                timerRun2=false;
            }
        }.start();
        timerRun2=true;
    }
    private  void updateCount2(){
        int minutes=(int) (timeLeft2 / 1000)/60;
        int seconds=(int) (timeLeft2 / 1000)%60;

        String timeLeftformat=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        txtCountDown2.setText(timeLeftformat);

    }
    private void Pause2(){
        countDownTimer2.cancel();
        timerRun2=false;
    }

    private void resetTimer(){
        timeLeft=START_TIME1;
        timeLeft2=START_TIME2;
        relativeLayout2.setBackgroundColor(Color.GRAY);
        relativeLayout.setBackgroundColor(Color.GRAY);
        updateCount();
        updateCount2();
    }

}