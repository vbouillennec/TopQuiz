//package com.bouillennec.v.topquiz.model;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static com.bouillennec.v.topquiz.model.QuestionManager.KEY_INDEX_REPONSE_QUESTION;
//import static com.bouillennec.v.topquiz.model.QuestionManager.KEY_INTITULE_QUESTION;
//
//*
// * Created by valbo on 26/09/2017.
//
//
//
//public class ReponseManager {
//    private static final String TABLE_NAME = "reponse";
//    private static final String FOREIGN_TABLE_NAME = "question";
//    public static final String KEY_ID_REPONSE = "id_reponse";
//    public static final String KEY_INTITULE_REPONSE = "intitule_reponse";
//    public static final String KEY_ID_QUESTION = "id_question";
//    public static final String CREATE_TABLE_REPONSE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "
//            +KEY_ID_REPONSE+" INTEGER PRIMARY KEY,"
//            +KEY_INTITULE_REPONSE+" TEXT,"
//            +KEY_ID_QUESTION+" INTEGER, "
//            +"FOREIGN KEY ("+KEY_ID_QUESTION+") REFERENCES "+FOREIGN_TABLE_NAME+" ("+KEY_ID_QUESTION+"));";
//    public static final String DROP_TABLE_REPONSE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";
//
//    private MySQLite myBaseSQLite;
//    private SQLiteDatabase db;
//
//    public ReponseManager(Context context) {
//        myBaseSQLite = MySQLite.getInstance(context);
//    }
//
//    public void open(){
//        db = myBaseSQLite.getWritableDatabase();
//    }
//
//    public void close(){
//        db.close();
//    }
//
//    public long addReponse(Reponse reponse) {
//        ContentValues values = new ContentValues();
//        values.put(KEY_INTITULE_REPONSE,reponse.getIdReponse());
//        values.put(KEY_ID_QUESTION, reponse.getIdQuestion());
//
//        return db.insert(TABLE_NAME, null, values);
//    }
//
//    public int editReponse(Reponse reponse) {
//        ContentValues values = new ContentValues();
//        values.put(KEY_INTITULE_REPONSE,reponse.getReponse());
//        values.put(KEY_ID_QUESTION, reponse.getIdQuestion());
//
//        String where = KEY_ID_REPONSE+" = ?";
//        String[] whereArgs = {reponse.getIdReponse()+""};
//
//        return db.update(TABLE_NAME,values,where,whereArgs);
//    }
//
//    public int delReponse(Reponse reponse){
//        String where = KEY_ID_REPONSE+" = ?";
//        String[] whereArgs = {reponse.getIdReponse()+""};
//
//        return db.delete(TABLE_NAME, where, whereArgs);
//    }
//
//    public Reponse getReponse(int id){
//        Reponse reponse = new Reponse();
//        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_QUESTION+" = "+id, null);
//        if(c.moveToNext()){
//            reponse.setIdReponse(c.getInt(c.getColumnIndex(KEY_ID_REPONSE)));
//            reponse.setReponse(c.getString(c.getColumnIndex(KEY_INTITULE_REPONSE)));
//            reponse.setIdQuestion(c.getInt(c.getColumnIndex(KEY_ID_QUESTION)));
//        }
//        c.close();
//        return reponse;
//    }
//
//    public Set<Reponse> getReponses () {
//        Set<Reponse> reponseList = new HashSet<>();
//        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
//        if (c.moveToFirst()){
//            do {
//                reponseList.add(getReponse(c.getInt(c.getColumnIndex(KEY_ID_REPONSE))));
//            }
//            while (c.moveToNext());
//        }
//        c.close();
//        return reponseList;
//    }
//
//    public Set<Reponse> getReponsesQuestion (int idQuestion) {
//        Set<Reponse> reponseList = new HashSet<>();
//        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+KEY_ID_QUESTION+" = "+idQuestion,null);
//        if (c.moveToFirst()){
//            do {
//                reponseList.add(getReponse(c.getInt(c.getColumnIndex(KEY_ID_REPONSE))));
//            }
//            while (c.moveToNext());
//        }
//        c.close();
//        return reponseList;
//    }
//}
