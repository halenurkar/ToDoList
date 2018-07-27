package com.example.halenurkar.todolist;

import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.halenurkar.todolist.adapters.NoteAdapter;
import com.example.halenurkar.todolist.interfaces.NoteItemListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteItemListener {
    Button btn;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    sqlite db = new sqlite(this);
    private list noteList;
    private List<list> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_new);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intocan = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intocan);
            }
        });


    }

    private void getNotes() {
        sqlite db = new sqlite(this);
        List<list> notes = db.getAllNotes();
        Log.d("notes", "notes loaded");

        listele(notes);
    }

    private void listele(List<list> notes) {
        this.notes = notes;
        NoteAdapter noteAdapter = new NoteAdapter(notes, this);
      /*  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);*/
        mRecyclerView.setAdapter(noteAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotes();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNoteItemClick(list note) {
        //    Toast.makeText(this, note.getHead(), Toast.LENGTH_SHORT).show();

        Intent detay = new Intent(this, DetailActivity.class);
        detay.putExtra("id", note.getId());
        startActivity(detay);
    }


    @Override
    public void onNoteDeleteClick(final list note) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Silmek istediğinizden emin misiniz?");
        builder.setCancelable(false);
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteNote(note);
                int index = notes.indexOf(note);
                notes.remove(index);
                mRecyclerView.getAdapter().notifyItemRemoved(index);
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();


    }




}

