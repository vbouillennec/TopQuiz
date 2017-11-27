package com.bouillennec.v.topquiz.model;

import java.util.ArrayList;

/**
 * Created by valbo on 18/09/2017.
 */

public class Question {
    private int mIdQuestion;
    private String mQuestion;
    private ArrayList<Reponse> mReponses;
    private int mReponseIndex;

    public Question(int idQuestion, String question, ArrayList<Reponse> reponses, int reponseIndex) {
        mIdQuestion = idQuestion;
        mQuestion = question;
        mReponses = reponses;
        mReponseIndex = reponseIndex;
    }

    public Question(){}

    public int getIdQuestion() {
        return mIdQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        mIdQuestion = idQuestion;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public ArrayList<Reponse> getReponses() {
        return mReponses;
    }

    public void setReponses(ArrayList<Reponse> reponses) {
        mReponses = reponses;
    }

    public int getReponseIndex() {
        return mReponseIndex;
    }

    public void setReponseIndex(int reponseIndex) {
        mReponseIndex = reponseIndex;
    }
}
