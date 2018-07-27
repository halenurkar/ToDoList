package com.example.halenurkar.todolist.interfaces;

import com.example.halenurkar.todolist.list;

public interface NoteItemListener {
    void onNoteItemClick(list note);
    void onNoteDeleteClick(list note);
}

