package com.example.lab_1;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

class Grid extends ArrayAdapter<String>{
    private int mCols, mRows;
    private Context mContext;
    private ArrayList<String> arrPict;
    private Resources mRes;
    private String pictureCollection ;
    private ArrayList<Status> arrStatus;
    private int countClick;
    private static enum Status {CELL_OPEN, CELL_CLOSE, CELL_DELETE};
    public Grid(Context context,int col, int row){
        super(context, col, row);
        mContext = context;
        mCols = col;
        mRows = row;
        pictureCollection = "p";
        arrPict = new ArrayList<String>();
        arrStatus = new ArrayList<Status>();
        mRes = mContext.getResources();
        makePictArr();
        closeAllCells();
    }

    private void closeAllCells () {
        arrStatus.clear();
        for (int i = 0; i < mCols * mRows; i++)
            arrStatus.add(Status.CELL_CLOSE);
    }
    private void makePictArr(){
        arrPict.clear();

        for (int i = 0; i < ((mCols * mRows)/2); i++){
            arrPict.add(pictureCollection + Integer.toString (i));
            arrPict.add(pictureCollection + Integer.toString (i));
        }
        Collections.shuffle(arrPict);
    }
    private void restart(){
        countClick = 0;
        makePictArr();
        closeAllCells();
    }
    public void checkCells(){
        countClick++;
        int first = arrStatus.indexOf(Status.CELL_OPEN);
        int second = arrStatus.lastIndexOf(Status.CELL_OPEN);
        if(first == second)
            return;
        if (arrPict.get(first).equals (arrPict.get(second)))
        {
            arrStatus.set(first, Status.CELL_DELETE);
            arrStatus.set(second, Status.CELL_DELETE);
        }
        else
        {
            arrStatus.set(first, Status.CELL_CLOSE);
            arrStatus.set(second, Status.CELL_CLOSE);
        }
    }
    public boolean checkGameOver() {
        if (!arrStatus.contains(Status.CELL_CLOSE)){
            restart();
            return true;
        }
        return false;
    }
    public void openCell(int pos){
        if(arrStatus.get(pos) != Status.CELL_DELETE)
            arrStatus.set(pos, Status.CELL_OPEN);
        notifyDataSetChanged();
    }
    public int getClickCount(){
        return countClick;
    }
    public int getCount() {
        return mCols * mRows;
    }

    public View getView(int pos, View convertView, ViewGroup parent){
        ImageView view;

        if(convertView == null) {
            view = new ImageView(mContext);
            view.setLayoutParams(new GridView.LayoutParams(150,150));
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setPadding(8, 8, 8, 8);
        }else {
            view = (ImageView) convertView;
        }
        switch (arrStatus.get(pos)){
            case CELL_OPEN:
                int drawableId = mRes.getIdentifier(arrPict.get(pos), "drawable", mContext.getPackageName());
                view.setImageResource(drawableId);
                break;
            case CELL_CLOSE:
                view.setImageResource(R.drawable.close);
                break;
            case CELL_DELETE:
                view.setImageResource(R.drawable.none);
                break;
        }
        return view;
    }

}
