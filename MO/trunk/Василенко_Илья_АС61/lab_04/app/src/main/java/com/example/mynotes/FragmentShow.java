package com.example.mynotes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentShow extends Fragment {

    private ListView listView;
    private NoteAdapter noteAdapter;
    private ArrayList<Note> notes;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public FragmentShow() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.list_view);
        notes = new ArrayList<Note>();
        noteAdapter = new NoteAdapter(getContext(), notes);
        listView.setAdapter(noteAdapter);

        dbHelper = new DBHelper(getContext());
        db = dbHelper.getReadableDatabase();

        loadNotes();
    }

    private void loadNotes() {
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                String description = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_DESCRIPTION));
                Note note = new Note(id, description);
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        noteAdapter.notifyDataSetChanged();
    }
}