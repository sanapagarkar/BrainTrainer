package com.sana.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    int s=0;
    TextView result;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView questions;
    TextView score;
    TextView timer;
    Button startButton;
    CountDownTimer countDownTimer;
    int locationOfCorrectAnswer, noOfQuestions=0;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button playAgainButton;
    RelativeLayout relativeLayout;

    public void playAgain(View view){
        s=0;
        result.setVisibility(View.INVISIBLE);
        noOfQuestions=0;
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgainButton.setVisibility(View.INVISIBLE);
        score.setText("0/0");
        timer.setText("30s");
        countDownTimer= new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                String s1=Integer.toString(s)+"/"+Integer.toString(noOfQuestions);
                result.setText("You scored "+s1);
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();
    }

    public void generateQues(){
        Random rand = new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        int incorrect;
        questions.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++) {
            if (i == locationOfCorrectAnswer)
                answers.add(a + b);
            else {
                incorrect=rand.nextInt(41);
                while(incorrect==a+b)
                    incorrect=rand.nextInt(41);

                answers.add(incorrect);

            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void choose(View view){

        noOfQuestions++;
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            result.setText("Correct");
            result.setVisibility(View.VISIBLE);
            s++;
        }
        else {
            result.setText("Wrong");
            result.setVisibility(View.VISIBLE);
        }
        score.setText(Integer.toString(s)+"/"+Integer.toString(noOfQuestions));
        generateQues();
    }

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button) findViewById(R.id.startButton);
        button0 =(Button) findViewById(R.id.button0);
        button1 =(Button) findViewById(R.id.button1);
        button2 =(Button) findViewById(R.id.button2);
        button3 =(Button) findViewById(R.id.button3);
        relativeLayout=(RelativeLayout) findViewById(R.id.rellayout);
        playAgainButton=(Button) findViewById(R.id.playAgain);
        questions=(TextView)findViewById(R.id.questions);
        score=(TextView) findViewById(R.id.score);
        timer=(TextView) findViewById(R.id.timer);
        result=(TextView) findViewById(R.id.result);
        result.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        playAgain(findViewById(R.id.playAgain));
        generateQues();

    }
}
