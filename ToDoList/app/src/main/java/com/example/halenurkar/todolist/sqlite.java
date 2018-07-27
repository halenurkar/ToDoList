package com.example.halenurkar.todolist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by halenur kar on 12.7.2018.
 */

public class sqlite extends SQLiteOpenHelper {
    private static final int DatabaseVersion = 2;
    private static final String DatabaseName = "notes_db";

    public sqlite(Context c) {
        super(c, DatabaseName, null, DatabaseVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table list(id integer primary key AUTOINCREMENT" + ",head text not null" + ",detail text)";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int news) {
        db.execSQL("drop table if exists list");
        onCreate(db);
    }

    public void insertNote(list list) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("head", list.getHead());
        values.put("detail", list.getDetail());
        long id = database.insert("list", null, values);

        database.close();
    }
      public void deleteNote(list list){
          SQLiteDatabase db = this.getWritableDatabase();
          db.delete("list", "id"+ " = ?",
                  new String[]{String.valueOf(list.getId())});
          db.close();
      }
      public int updateNote(list list)
      {
          SQLiteDatabase db = this.getWritableDatabase();

          ContentValues values = new ContentValues();
          values.put("head", list.getHead());
          values.put("detail", list.getDetail());
          return db.update("list", values, "id" + " = ?",
                  new String[]{String.valueOf(list.getId())});

      }

    public list getNote(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("list", new String[]{"id", "head", "detail"}, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        list list = null;
        if (cursor != null) {
            cursor.moveToFirst();

            list = new list(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("head")),
                    cursor.getString(cursor.getColumnIndex("detail"))

            );

        }
        cursor.close();
        return list;
    }

    public List<list> getAllNotes() {
        List<list> lists = new ArrayList<>();
        String selectQuery = "SELECT * FROM list";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list list = new list();
                list.setId(cursor.getInt(cursor.getColumnIndex("id")));
                list.setHead(cursor.getString(cursor.getColumnIndex("head")));
                list.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
                lists.add(list);
            } while (cursor.moveToNext());
        }
        db.close();
        return lists;
    }


}
