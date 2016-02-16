package com.lifeistech.android.tapamole;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int state = 0;

    int[] imageResourses = {
            R.id.imageButton, R.id.imageButton2, R.id.imageButton3,
            R.id.imageButton4, R.id.imageButton5, R.id.imageButton6,
            R.id.imageButton7, R.id.imageButton8, R.id.imageButton9,
            R.id.imageButton10, R.id.imageButton11, R.id.imageButton12
    };

    Mole[]moles;

    int time;
    int score;

    Timer timer;
    TimerTask timerTask;
    Handler h;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = (TextView)findViewById(R.id.scoreText);
        timeText = (TextView)findViewById(R.id.timeText);

        moles = new Mole[12];
        for(int i =0; i < 12; i++){
            ImageView imageView = (ImageView)findViewById(imageResourses[i]);
            moles[i] = new Mole(imageView);
        }

        h = new Handler();
    }


//    public void onClick(View v){
//        state =0;
//    }

    public void start(View v) {
        time = 60;
        score = 0;
        timeText.setText(String.valueOf(time));
        scoreText.setText(String.valueOf(score));

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {

                h.post(new Runnable() {

                    public void run() {

                        int r = random.nextInt(12);
                        moles[r].start();

                        time = time - 1;
                        timeText.setText(String.valueOf(time));

                        if (time <= 0) {
                            timer.cancel();
                        }
                        if(v.){
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);

    }

    public void tapMole(View v){
        String tag_str = (String)v.getTag();
        int tag_int = Integer.valueOf(tag_str);
        System.out.println("今日はいい天気です。");

        score += moles[tag_int].tapMole();

        scoreText.setText(String.valueOf(score));
    }

}
