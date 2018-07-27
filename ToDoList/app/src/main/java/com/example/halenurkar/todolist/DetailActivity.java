package com.example.halenurkar.todolist;

import android.content.Intent;
import android.graphics.pdf.PdfRenderer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.halenurkar.todolist.adapters.NoteAdapter;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    Button btnSave;
    EditText txtHead, txtDetail;
    private sqlite db;
    private int id = -1;
    private List<list> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        btnSave = (Button) findViewById(R.id.btn_save);
        txtHead = (EditText) findViewById(R.id.txt_head);
        txtDetail = findViewById(R.id.editText);
        if (getIntent().getExtras() != null) {
            id = getIntent().getExtras().getInt("id", -1);
            if (id != -1) {
                getNoteFromDb(id);
            }
        }
        db = new sqlite(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!txtHead.getText().toString().isEmpty()) {
                        if (id >= 0) {
                            updateList(txtHead.getText().toString(), txtDetail.getText().toString());
                        } else {
                            kayitEkleme(txtHead.getText().toString(), txtDetail.getText().toString());

                        }
                    }
                    Intent main = new Intent(DetailActivity.this, MainActivity.class);
                    startActivity(main);
                } finally {
                    db.close();
                }
            }


        });

    }

    private void getNoteFromDb(int id) {
        sqlite sqlite = new sqlite(this);
        list list = sqlite.getNote(id);
        showNote(list);
    }

    private void showNote(list list) {
        txtHead.setText(list.getHead());
        txtDetail.setText(list.getDetail());
    }

    private void kayitEkleme(String head, String detail) {
        list list = new list(0, head, detail);
        db.insertNote(list);
    }

    private void updateList(String head, String detail) {
        list list = new list(id, head, detail);
        list.setId(id);
        db.updateNote(list);


    }

}
