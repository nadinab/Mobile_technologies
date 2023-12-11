package com.example.mynotes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentAdd extends Fragment {

    private EditText etDescription;
    private Button btnAdd;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public FragmentAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etDescription = view.findViewById(R.id.et_description);
        btnAdd = view.findViewById(R.id.btn_add);

        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString().trim();
                if (!description.isEmpty()) {
                    addNote(description);
                    etDescription.setText("");
                    Toast.makeText(getContext(), "Note added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please enter a description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addNote(String description) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DESCRIPTION, description);
        db.insert(DBHelper.TABLE_NAME, null, values);
    }
}
