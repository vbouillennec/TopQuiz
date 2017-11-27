package com.bouillennec.v.topquiz.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by valbo on 22/09/2017.
 */

public class UserManager {

    private static final String TABLE_NAME = "user";
    public static final String KEY_ID_USER = "id_user";
    public static final String KEY_FIRSTNAME_USER = "firstname_user";
    public static final String KEY_SCORE_USER = "score_user";
    public static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "
            +KEY_ID_USER+" INTEGER PRIMARY KEY,"
            +KEY_FIRSTNAME_USER+" TEXT,"
            +KEY_SCORE_USER+" INTEGER );";
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    private MySQLite myBaseSQLite;
    private SQLiteDatabase db;

    public UserManager(Context context) {
        myBaseSQLite = MySQLite.getInstance(context);
    }

    public void open(){
        db = myBaseSQLite.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long addUser (User user){
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME_USER, user.getFirstName());

        return db.insert(TABLE_NAME,null,values);
    }

    public int editUser (User user){
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME_USER,user.getFirstName());
        values.put(KEY_SCORE_USER,user.getLastScore());

        String where = KEY_ID_USER+" = ?";
        String[] whereArgs = {user.getId()+""};

        return db.update(TABLE_NAME,values,where,whereArgs);
    }

    public int delUser (User user){
        String where = KEY_ID_USER+" = ?";
        String[] whereArgs = {user.getId()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public void delAllUser (){
        db.execSQL("DROP DATABASE db.sqlite;");
    }

    public User getUser (int id){

        User user = new User(0,"",0);
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_USER+" = "+id, null);
        if (c.moveToFirst()){
            user.setId(c.getInt(c.getColumnIndex(KEY_ID_USER)));
            user.setFirstName(c.getString(c.getColumnIndex(KEY_FIRSTNAME_USER)));
            user.setScore(c.getInt(c.getColumnIndex(KEY_SCORE_USER)));
        }
        c.close();
        return user;
    }

    public User getLastUser(){
        return getUser(getUsers().size()-1);
    }

    public ArrayList<User> getUsers (){
        ArrayList<User> usersList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if (c.moveToFirst()){
            do {
                usersList.add(this.getUser(c.getInt(c.getColumnIndex(KEY_ID_USER))));
            }
            while (c.moveToNext());
        }
        c.close();
        return usersList;
    }
}
