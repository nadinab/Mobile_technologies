package com.example.lab1;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

class GridAdapter extends BaseAdapter
{
    private Context mContext;
    private Integer mCols, mRows;
    private ArrayList<String> arrPict; // массив картинок
    private String PictureCollection; // Префикс набора картинок
    private Resources mRes; // Ресурсы приложения
    private static enum Status {CELL_OPEN, CELL_CLOSE, CELL_DELETE};
    private ArrayList<Status> arrStatus; // состояние ячеек
    private int countPict[];

    public GridAdapter(Context context, int cols, int rows)
    {
        mContext = context;
        mCols = cols;
        mRows = rows;
        arrPict = new ArrayList<String>();
        arrStatus = new ArrayList<Status>();

        // Префикс картинок
        PictureCollection = "fruit";

        // Получаем все ресурсы приложения
        mRes = mContext.getResources();

        // Метод заполняющий массив vecPict
        makePictArray ();

        // Метод устанавливающий всем ячейкам статус CELL_CLOSE
        closeAllCells();
    }

    private void makePictArray () {
        // очищаем вектор
        arrPict.clear();
        boolean flag;
        int count = mCols * mRows;
        countPict = new int[count/2];
        // добавляем
        int i=0;
        while (count != 0)
        {
            flag = Math.random()<0.5;
            if (flag) {
                arrPict.add (PictureCollection + Integer.toString (i));
                arrPict.add (PictureCollection + Integer.toString (i));
                arrPict.add (PictureCollection + Integer.toString (i));
                countPict[i]=3;
                ++i;
                count -=3;
            }else {
                arrPict.add (PictureCollection + Integer.toString (i));
                arrPict.add (PictureCollection + Integer.toString (i));
                countPict[i]=2;
                ++i;
                count -=2;
            }
            if (count == 1){
                arrPict.add (PictureCollection + Integer.toString (i));
                ++countPict[i];
                --count;
            }
            if (count == 2){
                arrPict.add (PictureCollection + Integer.toString (i));
                arrPict.add (PictureCollection + Integer.toString (i));
                countPict[i]=2;
                ++i;
                count -=2;
            }
        }
        Collections.shuffle(arrPict);
    }


    private void closeAllCells () {
        arrStatus.clear();
        for (int i = 0; i < mCols * mRows; i++)
            arrStatus.add(Status.CELL_CLOSE);
    }

    @Override
    public int getCount() {
        return mCols*mRows;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView view; // выводиться у нас будет картинка

        if (convertView == null)
            view = new ImageView(mContext);
        else
            view = (ImageView)convertView;

        switch (arrStatus.get(position))
        {
            case CELL_OPEN:
                // Получаем идентификатор ресурса для картинки, которая находится в векторе vecPict на позиции position
                Integer drawableId = mRes.getIdentifier(arrPict.get(position), "drawable", mContext.getPackageName());
                view.setImageResource(drawableId);
                break;
            case CELL_CLOSE:
                view.setImageResource(R.drawable.close);
                break;
            default:
                view.setImageResource(R.drawable.none);

        }

        return view;
    }

    public void checkOpenCells() {
        int first = arrStatus.indexOf(Status.CELL_OPEN);
        int second = arrStatus.lastIndexOf(Status.CELL_OPEN);
        if (first == second)
            return;
        if (arrPict.get(first).equals (arrPict.get(second)))
        {
            arrStatus.set(first, Status.CELL_DELETE);
            arrStatus.set(second, Status.CELL_DELETE);
            String PicId = arrPict.get(first).substring(PictureCollection.length(),arrPict.get(first).length());
            countPict[Integer.parseInt(PicId)] -= 2;
        }
        else
        {
            arrStatus.set(first, Status.CELL_CLOSE);
            arrStatus.set(second, Status.CELL_CLOSE);
        }
        return;
    }

    public void openCell(int position) {
        if (arrStatus.get(position) != Status.CELL_DELETE)
            arrStatus.set(position, Status.CELL_OPEN);

        notifyDataSetChanged();
        return;
    }

    public void openAllCells(){
            for (int i = 0; i < mCols * mRows; i++)
                if (arrStatus.get(i)== Status.CELL_CLOSE)
                arrStatus.set(i,Status.CELL_OPEN);
    }

    public boolean checkGameOver() {
        if (!arrStatus.contains(Status.CELL_CLOSE))
            return true;
        else {
            boolean noPairs = false;
             for (int i = 0; i < countPict.length; ++i)
             {
                 if (countPict[i] > 1){
                     noPairs = false;
                     break;
                 }else noPairs = true;
             }
            if (this.getCount()%2 == 1 && noPairs){
                return true;
            }
        }
        return false;
    }
}
