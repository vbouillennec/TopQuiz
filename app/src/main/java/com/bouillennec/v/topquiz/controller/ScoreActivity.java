package com.bouillennec.v.topquiz.controller;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bouillennec.v.topquiz.R;
import com.bouillennec.v.topquiz.model.User;
import com.bouillennec.v.topquiz.model.UserManager;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        UserManager mUserManager = new UserManager(this);
        mUserManager.open();

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_score_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<User> mUsers = mUserManager.getUsers();
        for (User user : mUsers){
            System.out.println(user.getId()+", "+user.getFirstName()+", "+user.getLastScore());
        }

        MyClickAdapter clickAdapter = new MyClickAdapter(mUsers);
        clickAdapter.setOnEntryClickListener(new MyClickAdapter.OnEntryClickListener(){
            @Override
            public void onEntryClick(View v, final int position) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Delete")
                        .setMessage("Do you want to delete this user ?")
                        .setNegativeButton("NO", null)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserManager userManager = new UserManager(ScoreActivity.this);
                                userManager.open();
                                int targetID = userManager.getUsers().get(position).getId();
                                userManager.delUser(userManager.getUser(targetID));
                                System.out.println("position :"+position);
                                System.out.println("targetID :"+targetID);
                                recreate();
                                userManager.close();
                            }
                        })
                        .show();
            }
        });
        mUserManager.close();
        mRecyclerView.setAdapter(clickAdapter);
    }
}
