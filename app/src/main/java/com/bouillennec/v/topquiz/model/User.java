package com.bouillennec.v.topquiz.model;

import java.util.ArrayList;

/**
 * Created by valbo on 18/09/2017.
 */

public class User {
    private int mId;
    private String mFirstName;
    private ArrayList<Integer> mScore = new ArrayList<>();

    public User(int id, String firstName, int score) {
        mId = id;
        mFirstName = firstName;
        mScore.add(score);
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }


    public int getScore(int index) {
        return mScore.get(index);
    }

    public int getLastScore() {
        return mScore.get(mScore.size()-1);
    }

    public ArrayList<Integer> getAllScore(){
        return mScore;
    }

    public void setScore(int score) {
        mScore.add(score);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }
}
