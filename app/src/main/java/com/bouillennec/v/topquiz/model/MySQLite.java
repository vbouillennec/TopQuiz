package com.bouillennec.v.topquiz.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by valbo on 23/09/2017.
 */

class MySQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db.sqlite";
    private static MySQLite sInstance;

    public static synchronized MySQLite getInstance(Context context) {
        if(sInstance == null){
            sInstance = new MySQLite(context);
        }
        return sInstance;
    }

    private MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserManager.CREATE_TABLE_USER);
        db.execSQL(QuestionManager.CREATE_TABLE_QUESTION);
        //db.execSQL(ReponseManager.CREATE_TABLE_REPONSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserManager.DROP_TABLE_USER);
        db.execSQL(QuestionManager.DROP_TABLE_QUESTION);
        //db.execSQL(ReponseManager.DROP_TABLE_REPONSE);
        onCreate(db);
    }
}
