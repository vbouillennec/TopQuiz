package com.bouillennec.v.topquiz.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by valbo on 25/09/2017.
 */

public class QuestionManager {
    private static final String TABLE_NAME = "question";
    private static final String FOREIGN_TABLE_NAME = "reponse";
    public static final String KEY_ID_QUESTION = "id_question";
    public static final String KEY_INTITULE_QUESTION = "intitule_question";
    public static final String KEY_INDEX_REPONSE_QUESTION = "index_reponse_question";
    public static final String CREATE_TABLE_QUESTION = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
            + KEY_ID_QUESTION + " INTEGER PRIMARY KEY,"
            + KEY_INTITULE_QUESTION + " TEXT,"
            + KEY_INDEX_REPONSE_QUESTION + " INTEGER,"
            + "FOREIGN KEY (" + KEY_ID_QUESTION + ") REFERENCES " + FOREIGN_TABLE_NAME + " (" + KEY_ID_QUESTION + "));";
    public static final String DROP_TABLE_QUESTION = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    private MySQLite myBaseSQLite;
    private SQLiteDatabase db;

    public QuestionManager(Context context) {
        myBaseSQLite = MySQLite.getInstance(context);
    }

    public void open() {
        db = myBaseSQLite.getWritableDatabase();
        myBaseSQLite.onCreate(db);
    }

    public void close() {
        db.close();
    }

    public long addQuestion(Question question) {
        ContentValues values = new ContentValues();
        values.put(KEY_INTITULE_QUESTION, question.getQuestion());
        values.put(KEY_INDEX_REPONSE_QUESTION, question.getReponseIndex());

        /*ContentValues values2 = new ContentValues();

        for (Reponse reponse : question.getReponses()){
            values2.put(KEY_INTITULE_REPONSE,reponse.getReponse());
            values2.put(KEY_INDEX_REPONSE_QUESTION, reponse.getIdReponse());

            db.insert(FOREIGN_TABLE_NAME,null,values2);
        }*/


        return db.insert(TABLE_NAME, null, values);
    }

    public int editQuestion(Question question) {
        ContentValues values = new ContentValues();
        values.put(KEY_INTITULE_QUESTION, question.getQuestion());
        values.put(KEY_INDEX_REPONSE_QUESTION, question.getReponseIndex());

        String where = KEY_ID_QUESTION + " = ?";
        String[] whereArgs = {question.getIdQuestion() + ""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public int delQuestion(Question question) {
        String where = KEY_ID_QUESTION + " = ?";
        String[] whereArgs = {question.getIdQuestion() + ""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public Question getQuestion(int id) {
        Question question = new Question();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID_QUESTION + " = " + id, null);
        if (c.moveToNext()) {
            question.setIdQuestion(c.getInt(c.getColumnIndex(KEY_ID_QUESTION)));
            question.setQuestion(c.getString(c.getColumnIndex(KEY_INTITULE_QUESTION)));
            question.setReponseIndex(c.getInt(c.getColumnIndex(KEY_INDEX_REPONSE_QUESTION)));
        }
        c.close();
        return question;
    }

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                questionList.add(getQuestion(c.getInt(c.getColumnIndex(KEY_ID_QUESTION))));
            }
            while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public void addQuestions(ArrayList<Question> questions) {
        for (Question question : questions) {
            addQuestion(question);
        }
    }
}
