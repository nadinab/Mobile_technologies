package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import kotlin.text.MatchGroup;

public class MainActivity extends AppCompatActivity {
    private GridView mGridView;
    private Grid mGrid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text_view_id);
        Button btn = findViewById(R.id.btn_restart);

        mGridView = (GridView)findViewById(R.id.grid_field);
        mGridView.setNumColumns(4);
        mGridView.setEnabled(true);

        mGrid = new Grid(this, 4, 4);
        mGridView.setAdapter(mGrid);
        mGridView.setOnItemClickListener((parent, v, position, id) -> {

            mGrid.openCell (position);

            textView.setText("click count: " + Integer.toString(mGrid.getClickCount()));
            if (mGrid.checkGameOver())
                Toast.makeText (getApplicationContext(), "Игра закончена", Toast.LENGTH_SHORT).show();

        });
        btn.setOnClickListener(view -> {
            mGrid.restart();
            Toast.makeText(getApplicationContext(), "Игра перезапущена", Toast.LENGTH_SHORT).show();
        });
    }
}