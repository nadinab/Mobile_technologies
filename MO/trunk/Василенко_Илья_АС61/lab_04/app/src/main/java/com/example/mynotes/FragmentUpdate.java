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

public class FragmentUpdate extends Fragment {

    private EditText etId;
    private EditText etDescription;
    private Button btnUpdate;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public FragmentUpdate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etId = view.findViewById(R.id.et_id);
        etDescription = view.findViewById(R.id.et_description);
        btnUpdate = view.findViewById(R.id.btn_update);

        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (!id.isEmpty() && !description.isEmpty()) {
                    updateNote(Integer.parseInt(id), description);
                    etId.setText("");
                    etDescription.setText("");
                    Toast.makeText(getContext(), "Note updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please enter a note id and a new description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateNote(int id, String description) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DESCRIPTION, description);
        db.update(DBHelper.TABLE_NAME, values, DBHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
