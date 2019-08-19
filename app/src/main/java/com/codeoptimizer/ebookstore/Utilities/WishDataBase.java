package com.codeoptimizer.ebookstore.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.codeoptimizer.ebookstore.Model.BookData;

import java.util.ArrayList;
import java.util.List;

public class WishDataBase {

    public static final String DB_Name="Wish.db";
    public static final int DB_Ver=2;
    public static final String DB_Table="Book_Data";
    public static final String BookName="Book_name";
    public static final String AuhtorName="Author_name";
    public static final String BookDecs="Book_desc";
    public static final String BookUrl="Book_url";
    public static final String BookCategory="Book_category";
    public static final String KEY_ID="id";


    //Query to create table

    public static final String Q_Create=
            "CREATE TABLE "+DB_Table+"("+KEY_ID+" INTEGER PRIMARY KEY  AUTOINCREMENT,"+BookName+" TEXT, "+AuhtorName+" TEXT, "+BookDecs+" TEXT, "+BookUrl+" TEXT, "+BookCategory+" TEXT)";

    Context c;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    //all database operation coded here
    public WishDataBase(Context context){
        c = context;

    }

    public WishDataBase open() {

        dbHelper=new DBHelper(c);

        database=dbHelper.getWritableDatabase();

        return this;
    }

    public void save(String name, String authorName,String desc,String url,String category) {

        ContentValues cv= new ContentValues();
        cv.put(BookName,name);
        cv.put(AuhtorName,authorName);
        cv.put(BookDecs,desc);
        cv.put(BookUrl,url);
        cv.put(BookCategory,category);
        database.insert(DB_Table,null,cv);
    }

    public void close() {
        database.close();
    }

    public List<BookData> getBookData()
    {
        List<BookData> data= new ArrayList<>();
        String[] columns={KEY_ID,BookName,AuhtorName,BookDecs,BookUrl,BookCategory};
        Cursor cursor=database.query(DB_Table,columns,null,null,null,null,null);

        int iName=cursor.getColumnIndex(BookName);
        int iAuthor=cursor.getColumnIndex(AuhtorName);
        int iDesc=cursor.getColumnIndex(BookDecs);
        int iUrl=cursor.getColumnIndex(BookUrl);
        int iCat=cursor.getColumnIndex(BookCategory);
        int iId=cursor.getColumnIndex(KEY_ID);

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            BookData book = new BookData();
            book.setBookName(cursor.getString(iName));
            book.setBookAuthor(cursor.getString(iAuthor));
            book.setBookDesc(cursor.getString(iDesc));
            book.setBookUrl(cursor.getString(iUrl));
            book.setBookCategory(cursor.getString(iCat));
            book.setBookId(cursor.getString(iId));

            data.add(book);
        }
        return data;
    }
    public List<BookData> getBookDataByCategory(String category)
    {

        String whereQuery = "SELECT  * FROM " + DB_Table+" WHERE "+BookCategory+" = '"+category+"'";
        Cursor cursor = database.rawQuery(whereQuery, null);
        List<BookData> data= new ArrayList<>();
        // String[] columns={KEY_ID,BookName,AuhtorName,BookDecs,BookUrl,BookCategory};
        // Cursor cursor=database.query(DB_Table,columns,null,null,null,null,null);

        int iName=cursor.getColumnIndex(BookName);
        int iAuthor=cursor.getColumnIndex(AuhtorName);
        int iDesc=cursor.getColumnIndex(BookDecs);
        int iUrl=cursor.getColumnIndex(BookUrl);
        int iCat=cursor.getColumnIndex(BookCategory);
        int iId=cursor.getColumnIndex(KEY_ID);

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            BookData book = new BookData();
            book.setBookName(cursor.getString(iName));
            book.setBookAuthor(cursor.getString(iAuthor));
            book.setBookDesc(cursor.getString(iDesc));
            book.setBookUrl(cursor.getString(iUrl));
            book.setBookCategory(cursor.getString(iCat));
            book.setBookId(cursor.getString(iId));

            data.add(book);
        }
        return data;
    }
    public void delete(String id) {
        database.delete(DB_Table, KEY_ID+"="+id, null);
    }

    public void deleteWithoutId() {
        database.delete(DB_Table, null, null);
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

