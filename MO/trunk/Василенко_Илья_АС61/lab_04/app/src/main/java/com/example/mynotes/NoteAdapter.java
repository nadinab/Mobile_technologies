// В файле NoteAdapter.java
package com.example.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Note> notes;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        }
        TextView tvId = view.findViewById(R.id.tv_id);
        TextView tvDescription = view.findViewById(R.id.tv_description);

        Note note = notes.get(position);
        tvId.setText(String.valueOf(note.getId()));
        tvDescription.setText(note.getDescription());

        return view;
    }
}
