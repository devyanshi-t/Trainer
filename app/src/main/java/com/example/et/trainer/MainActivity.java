package com.example.et.trainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView, sumTextView, scoreTextView, resultTextView;
    Button button0, button1, button2, button3, playAgain;
    RelativeLayout game;
    ArrayList<Integer> ans = new ArrayList<Integer>();
    ImageView b;
    int score = 0;
    int noOfQuestions = 0;
    int locationOfCorrect;

    public void start(View view) {
        b = (ImageView) findViewById(R.id.b);
        b.setVisibility(view.INVISIBLE);
        game.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgain));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        game = (RelativeLayout) findViewById(R.id.game);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        playAgain = (Button) findViewById(R.id.playAgain);
        playAgain(findViewById(R.id.playAgain));
    }


    public void generateQuestions() {
        Random rand = new Random();
        int a = rand.nextInt(101);
        int b = rand.nextInt(101);
        int incorrect;
        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        ans.clear();
        locationOfCorrect=rand.nextInt(4);
        for(int i=0;i<4;i++) {
            if (locationOfCorrect == i) {
                ans.add(a + b);
            } else {
                incorrect = rand.nextInt(201);
                while (incorrect == a + b) {
                    incorrect = rand.nextInt(201);
                }
                ans.add(incorrect);
            }
        }
            button0.setText(Integer.toString(ans.get(0)));
            button1.setText(Integer.toString(ans.get(1)));
            button2.setText(Integer.toString(ans.get(2)));
            button3.setText(Integer.toString(ans.get(3)));




    }
    public void chooseAns(View view)
    {
     String s1=Integer.toString(locationOfCorrect);
        String s2=String.valueOf(view.getTag());
        if(s1.equals(s2))
        {
            score++;
            resultTextView.setText("CORRECT!");
        }
        else
        {
            resultTextView.setText("INCORRECT!");
        }
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        generateQuestions();
    }


    public void playAgain( View view)

    { /* button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);*/
       resultTextView.setText("");
        playAgain.setVisibility(view.INVISIBLE);
        scoreTextView.setText("0/0");
        timerTextView.setText("0s");
        generateQuestions();


        new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish()
            {
               /* button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);*/
             playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("FINISHED\n" +"You got "+Integer.toString(score)+" out of "+Integer.toString(noOfQuestions)+ "questions correct.");

            }
        }.start();


    }
}