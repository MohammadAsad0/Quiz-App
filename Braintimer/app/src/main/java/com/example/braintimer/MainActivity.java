package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.braintimer.R.drawable.correct;
import static com.example.braintimer.R.drawable.wrong;

public class MainActivity extends AppCompatActivity {

    TextView time,question;
    TextView score,output;
    ImageView result;
    Button button0,button1,button2,button3,playButton;
    int number1;
    int number2;
    int answer,correctPos;
    int scoring=0,totalQues=0;
    ConstraintLayout game,playAgain;
    ArrayList<Integer> choicesArray=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time=findViewById(R.id.timeTextView);
        score=findViewById(R.id.scoreTextView);
        result=findViewById(R.id.imageView2);
        question=findViewById(R.id.quesTextView);
        button0=findViewById(R.id.button1);
        button1=findViewById(R.id.button2);
        button2=findViewById(R.id.button3);
        button3=findViewById(R.id.button4);
        playButton=findViewById(R.id.startButton);
        game=findViewById(R.id.game);
        playAgain=findViewById(R.id.playAgain);
        output=findViewById(R.id.output);

    }

    public void startGame(View view) {
        playButton.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);

        result.setImageResource(0);
        scoring=0;
        totalQues=0;
        score.setText(Integer.toString(scoring)+"/"+Integer.toString(totalQues));

        newQuestion();

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished/1000)+" S");
            }

            @Override
            public void onFinish() {
                game.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);

//                output.setText("Your IQ is "+checkIQ()+"/"+"5");
                score.setText(Integer.toString(scoring)+"/"+Integer.toString(totalQues));
            }
        }.start();

    }

//    int checkIQ() {
//        int percent=(scoring/totalQues)*100;
//        if (percent>0 && percent<=20 && totalQues>=6) {
//           return 1;
//        } else if(percent>20 && percent<=40 && totalQues>=6) {
//            return 2;
//        } else if(percent>40 && percent<=60 && totalQues>=6) {
//            return 3;
//        } else if(percent>60 && percent<=80 && totalQues>=6) {
//            return 4;
//        } else if(percent>80 && totalQues>=6) {
//            return 5;
//        } 3eelse if(totalQues<6){
//            return 1;
//        } else
//            return 0;
//    }


    public void onClickButton(View view) {
        if(view.getTag().toString().equals(Integer.toString(correctPos))) {
            result.setImageResource(correct);
            scoring++;
        } else {
            result.setImageResource(wrong);
        }
        score.setText(scoring+" / "+totalQues);
        totalQues++;

        newQuestion();
    }

    void newQuestion() {
        choicesArray.clear();
        Random random=new Random();
        number1=random.nextInt(51);
        number2=random.nextInt(51);
        answer=number1+number2;

        question.setText(number1 + " + " + number2 + " ?");
        correctPos= random.nextInt(4);

        for(int i=0;i<4;i++) {
            if(i==correctPos) {
                choicesArray.add(answer);
            } else {
                int wrongAns=random.nextInt(101);
                while (wrongAns==answer) {
                    wrongAns=random.nextInt(101);
                }
                choicesArray.add(wrongAns);
            }
        }
        button0.setText(Integer.toString(choicesArray.get(0)));
        button1.setText(Integer.toString(choicesArray.get(1)));
        button2.setText(Integer.toString(choicesArray.get(2)));
        button3.setText(Integer.toString(choicesArray.get(3)));
    }
}