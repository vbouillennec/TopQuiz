package com.bouillennec.v.topquiz.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bouillennec.v.topquiz.R;
import com.bouillennec.v.topquiz.model.Question;
import com.bouillennec.v.topquiz.model.QuestionManager;
import com.bouillennec.v.topquiz.model.User;
import com.bouillennec.v.topquiz.model.UserManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mScoreButton;

    private User lastUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity :: onCreate()");

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        mScoreButton = (Button) findViewById(R.id.activity_main_score_btn);

        UserManager mUserManager = new UserManager(this);
        mUserManager.open();
        try {
            ArrayList<User> users = mUserManager.getUsers();
            lastUser = users.get(mUserManager.getUsers().size()-1);
        }
        catch (Exception e){

        }
        mUserManager.close();

        mPlayButton.setEnabled(false);

        greetUser();

/*        // Execute once
        QuestionManager mQuestionManager = new QuestionManager(MainActivity.this);
        mQuestionManager.open();
        ArrayList<Question> mQuestions = mQuestionManager.getQuestions();
        for (Question question : mQuestions){
            System.out.println(question.getIdQuestion()+", "+question.getQuestion()+", "+question.getReponseIndex());
        }
        mQuestionManager.close();*/


        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager mUserManager = new UserManager(MainActivity.this);
                mUserManager.open();
                mUserManager.addUser(new User(999 ,mNameInput.getText().toString(), 0));
                mUserManager.close();

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent scoreActivityIntent = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(scoreActivityIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            UserManager mUserManager = new UserManager(this);
            mUserManager.open();

            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            try {
                ArrayList<User> users = mUserManager.getUsers();
                lastUser = users.get(mUserManager.getUsers().size()-1);
                lastUser.setScore(score);
                mUserManager.editUser(lastUser);

                greetUser();
            }catch (Exception e){

            }

            mUserManager.close();
        }
    }

    protected void greetUser(){
        UserManager mUserManager = new UserManager(this);
        mUserManager.open();

        String firstName = lastUser.getFirstName();
            if(firstName != null){
                int score = lastUser.getLastScore();
                mGreetingText.setText("Welcome back, "+firstName+" !\nYour last score was : "+score+"\nWil you do better this time ?");
                mNameInput.setText(firstName);
                //mNameInput.setSelection(1,firstName.length());
                mNameInput.selectAll();
                mPlayButton.setEnabled(true);

            }

        mUserManager.close();
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity :: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity :: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity :: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity :: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity :: onDestroy()");
    }
}


