package com.example.mynotes;

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

public class FragmentDel extends Fragment {

    private EditText etId;
    private Button btnDel;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public FragmentDel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_del, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etId = view.findViewById(R.id.et_id);
        btnDel = view.findViewById(R.id.btn_del);

        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString().trim();
                if (!id.isEmpty()) {
                    delNote(Integer.parseInt(id));
                    etId.setText("");
                    Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please enter a note id", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void delNote(int id) {
        db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
