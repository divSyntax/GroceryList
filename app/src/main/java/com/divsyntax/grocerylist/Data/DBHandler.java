package com.divsyntax.grocerylist.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.divsyntax.grocerylist.Model.Grocery;
import com.divsyntax.grocerylist.Util.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    private Context ctx;
    public DBHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + Constants.TABLE_NAME +"(" +
                Constants.KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + Constants.KEY_GROCERY_ITEM + " text,"
                + Constants.KEY_QTY_NUMBER + " text,"
                + Constants.KEY_DATE_NAME + " long);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }


    public void addGrocery(Grocery grocery)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());
        values.put(Constants.KEY_ID, grocery.getId());

        db.insert(Constants.TABLE_NAME,null,values);
        Log.d("Saved: ","Saved");

    }

    public Grocery getGrocery(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.KEY_GROCERY_ITEM, 
        Constants.KEY_QTY_NUMBER, Constants.KEY_DATE_NAME},
                Constants.KEY_ID + "=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor != null)

            cursor.moveToFirst();
            Grocery grocery = new Grocery();
            grocery.setId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
            grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
            grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

            java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
            String format = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());
            grocery.setDateAdded(format);


        return grocery;
    }

    public List<Grocery> getAllGrocery()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Grocery> groceryList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME , new String[]{Constants.KEY_ID , Constants.KEY_GROCERY_ITEM , Constants.KEY_QTY_NUMBER , Constants.KEY_DATE_NAME} ,
                null,null,null,null,Constants.KEY_DATE_NAME + " DESC");

        if(cursor.moveToFirst())
        {
            do{
                Grocery grocery = new Grocery();
                grocery.setId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
                grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
                grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

                java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
                String format = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());
                grocery.setDateAdded(format);

                groceryList.add(grocery);

            }while (cursor.moveToNext());
        }

        return groceryList;
    }

    public int upDateGrocery(Grocery grocery)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());
        values.put(Constants.KEY_ID, grocery.getId());

       return db.update(Constants.TABLE_NAME, values,Constants.KEY_ID+ "=?" , new String[]{String.valueOf(grocery.getId())});
    }

    public void deleteGrocery(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
//        Grocery grocery = new Grocery();
//        grocery.setId(id);
        db.delete(Constants.TABLE_NAME , Constants.KEY_ID + "=?" , new String[]{String.valueOf(id)});
        //db.execSQL("delete from " + Constants.TABLE_NAME + " where " + id + " ");
    }

    public int getCount()
    {

        String count = "select * from " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(count,null);



        return cursor.getCount();
    }
}



















