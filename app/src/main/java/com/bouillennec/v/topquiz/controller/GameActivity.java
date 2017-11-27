package com.bouillennec.v.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bouillennec.v.topquiz.R;
import com.bouillennec.v.topquiz.model.Question;
import com.bouillennec.v.topquiz.model.QuestionBank;
import com.bouillennec.v.topquiz.model.QuestionManager;
import com.bouillennec.v.topquiz.model.Reponse;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_EXTRA_ID = "BUNDLE_EXTRA_ID";
    private static final String BUNDLE_STATE_SCORE = "currentScore";
    private static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    private static final int NB_QUESTIONS = 12;


    private TextView mQuestionTextView;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private Question mCurrentQuestion;
    private int mNumberOfQuestions;
    private  int mScore;
    private QuestionBank questionBank;
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity :: onCreate()");

        mQuestionTextView = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswerButton1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mEnableTouchEvents = true;

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        }
        else{
            mScore = 0;
            mNumberOfQuestions = NB_QUESTIONS;
        }

        questionBank = new QuestionBank();

        mCurrentQuestion = questionBank.getQuestion();
        displayQuestion(mCurrentQuestion);

    }

    public void displayQuestion(final Question question){
        mQuestionTextView.setText(question.getQuestion());
        ArrayList<Reponse> reponses = question.getReponses();
        mAnswerButton1.setText(reponses.get(0).getReponse());
        mAnswerButton2.setText(reponses.get(1).getReponse());
        mAnswerButton3.setText(reponses.get(2).getReponse());
        mAnswerButton4.setText(reponses.get(3).getReponse());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        mEnableTouchEvents = false;

        int responseIndex = (int) v.getTag();
        if(responseIndex == mCurrentQuestion.getReponseIndex()-1){
            // Good answer
            Toast.makeText(this, "Correct Answer !", Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else{
            Toast.makeText(this,"Wrong Answer !",Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
            mEnableTouchEvents = true;
                if (--mNumberOfQuestions == 0){
                    endGame();
                }
                else
                {
                    mCurrentQuestion = questionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well donne !")
                .setMessage("Your score is "+ mScore)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra("BUNDLE_EXTRA_SCORE", mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

/*    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }*/

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity :: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity :: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity :: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity :: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity :: onDestroy()");
    }
}
