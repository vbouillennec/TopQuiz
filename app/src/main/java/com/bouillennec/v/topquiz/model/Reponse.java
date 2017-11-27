package com.bouillennec.v.topquiz.model;

/**
 * Created by valbo on 25/09/2017.
 */

public class Reponse {

    private int mIndexReponse;
    private String mReponse;

    public Reponse(int indexReponse, String reponse) {
        mIndexReponse = indexReponse;
        mReponse = reponse;
    }

    public int getIndexReponse() {
        return mIndexReponse;
    }

    public void setIndexReponse(int indexReponse) {
        mIndexReponse = indexReponse;
    }

    public String getReponse() {
        return mReponse;
    }

    public void setReponse(String reponse) {
        mReponse = reponse;
    }
}
