package com.example.halenurkar.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;

/**
 * Created by halenur kar on 12.7.2018.
 */

public class data {
    SQLiteDatabase db;
    sqlite bdb;

    public data(Context c) {
        bdb = new sqlite(c);
    }

    public void open() {
        db = bdb.getWritableDatabase();
    }

    public void close() {
        bdb.close();
    }

    public void dolist(list list) {

        ContentValues val = new ContentValues();
        val.put("head", list.getHead());
        db.insert("list", null, null);
    }


    public List<list> listele() {
        String kolon[] = {"id", "head", "detail"};
        List<list>liste =new ArrayList<list>();
        Cursor c=db.query("list",kolon,null,null,null,null,null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            int id=c.getInt(0);
            String head=c.getString(1);
            String detail=c.getString(2);
            String eleman=""+id+"-"+head+"-"+detail;
            list list=new list(id,head,detail);
            list.setId(id);
            list.setHead(head);
            list.setDetail(detail);
            liste.add(list);
        }
        return liste;
    }

}
