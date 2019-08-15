package com.codeoptimizer.ebookstore.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codeoptimizer.ebookstore.Model.User;

import java.util.ArrayList;
import java.util.List;

public class MyDataBase {

    public static final String DB_Name="EBookStore.db";
    public static final int DB_Ver=2;
    public static final String DB_Table="User_Data";
    public static final String ID = "User_id";
    public static final String Email="User_email";
    public static final String Password="User_password";


    //Query to create table

    public static final String Q_Create=
            "CREATE TABLE "+DB_Table+"("+ID+" INTEGER PRIMARY KEY  AUTOINCREMENT,"+Email+" TEXT, "+Password+" TEXT)";

    Context c;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    //all database operation coded here
    public MyDataBase(Context context){
        c = context;

    }

    public MyDataBase open() {

        dbHelper=new DBHelper(c);

        database=dbHelper.getWritableDatabase();

        return this;
    }

    public void save( String email,String password) {

        ContentValues cv= new ContentValues();
        cv.put(Email,email);
        cv.put(Password,password);


        database.insert(DB_Table,null,cv);
    }

    public void close() {
        database.close();
    }



    public List<User> getUserData()
    {
        List<User> data= new ArrayList<>();
        String[] columns={ID,Email,Password};
        Cursor cursor=database.query(DB_Table,columns,null,null,null,null,null);

        int iId=cursor.getColumnIndex(ID);
        int iEmail=cursor.getColumnIndex(Email);
        int iPassword=cursor.getColumnIndex(Password);


        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            User user = new User();
            user.setUserId(cursor.getString(iId));
            user.setUserEmail(cursor.getString(iEmail));
            user.setUserPassword(cursor.getString(iPassword));


            data.add(user);
        }
        return data;
    }
    public int checkUserExist(String email,String password) {
        int count = 0;

        String whereQuery = "SELECT  * FROM " + DB_Table+" WHERE "+Email+" = '"+email+"' AND "+Password+ " = '" + password + "'";


        Cursor cursor = database.rawQuery(whereQuery, null);
        count = cursor.getCount();
        cursor.close();

        return count;
    }


    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + DB_Table;

        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_Name, null, DB_Ver);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            sqLiteDatabase.execSQL(Q_Create);////////////////////

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
