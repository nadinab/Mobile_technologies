package com.example.lab_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        mGridView = (GridView)findViewById(R.id.grid_field);
        mGridView.setNumColumns(6);
        mGridView.setEnabled(true);

        mGrid = new Grid(this, 6, 6);
        mGridView.setAdapter(mGrid);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id){
                mGrid.checkCells();
                mGrid.openCell (position);
                textView.setText("click count: " + Integer.toString(mGrid.getClickCount()));
                if (mGrid.checkGameOver())
                    Toast.makeText (getApplicationContext(), "Игра закончена", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
