package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GridView mGrid;
    private GridAdapter mAdapter;
    private Button restartButton;
    private Button startButton;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        startButton = findViewById(R.id.start_button);

        Spinner spinner_width = findViewById(R.id.spinner_width);
        Spinner spinner_height = findViewById(R.id.spinner_height);
        View.OnClickListener startGame = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String width = spinner_width.getSelectedItem().toString();
                String height = spinner_height.getSelectedItem().toString();
                startGame(Integer.parseInt(width),Integer.parseInt(height));
            }
        };
        startButton.setOnClickListener(startGame);
    }


    //Game

    public void startGame(int _width, int _height){
        setContentView(R.layout.activity_main);
        restartButton = findViewById(R.id.restart_button);

        View.OnClickListener restartGame = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        };



        restartButton.setOnClickListener(restartGame);

        mGrid = (GridView)findViewById(R.id.field);
        mGrid.setNumColumns(_width);
        mGrid.setEnabled(true);

        mAdapter = new GridAdapter(this, _width, _height);
        mGrid.setAdapter(mAdapter);

        mGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                mAdapter.checkOpenCells();
                mAdapter.openCell(position);

                if (mAdapter.checkGameOver()) {
                    mAdapter.openAllCells();
                    Toast.makeText(getApplicationContext(), "Игра окончена", Toast.LENGTH_SHORT).show();

                    restartButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
