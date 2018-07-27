package com.example.halenurkar.todolist.adapters;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.halenurkar.todolist.MainActivity;
import com.example.halenurkar.todolist.R;
import com.example.halenurkar.todolist.interfaces.NoteItemListener;
import com.example.halenurkar.todolist.list;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private final NoteItemListener listener;
    private List<list> noteList;
     NoteAdapter context=this;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView header;
        public ImageButton btnDelete;

        public MyViewHolder(View view) {
            super(view);x
            header = view.findViewById(R.id.txt_item);
            btnDelete = view.findViewById(R.id.btn_delete);


        }


    }


    public NoteAdapter(List<list> noteList, NoteItemListener listener) {
        this.noteList = noteList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final list note = noteList.get(position);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNoteDeleteClick(note);
            }
        });
        holder.header.setText(note.getHead());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onNoteItemClick(note);
            }
        });


    }

    @Override
    public int getItemCount() {
        return noteList == null ? 0 : noteList.size();
    }
}

