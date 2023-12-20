package com.example.lab4;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<JSONObject> {
    int listLayout;
    ArrayList<JSONObject> usersList;
    Context context;

    //конструктор класса
    public ListViewAdapter(Context context, int listLayout , int field, ArrayList<JSONObject> usersList) {
        super(context, listLayout, field, usersList);
        this.context = context;
        this.listLayout = listLayout;
        this.usersList = usersList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Создаем новый экземпляр LayoutInflater, связанный с определенным контекстом
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View inflate (int resource, ViewGroup root)
        View listViewItem = inflater.inflate(listLayout, null);
        TextView name = listViewItem.findViewById(R.id.textViewName);
        TextView email = listViewItem.findViewById(R.id.textViewEmail);
        TextView id = listViewItem.findViewById(R.id.textViewId);
        try {
            name.setText(usersList.get(position).getString("name"));
            email.setText(usersList.get(position).getString("email"));
            id.setText(usersList.get(position).getString("id"));
        } catch (JSONException je){
            je.printStackTrace();
        }
        return listViewItem;
    }
}