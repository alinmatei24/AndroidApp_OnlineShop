package com.example.myonlineshopp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<RowItem> singleRow;
    private LayoutInflater thisInflater;

    public CustomAdapter(Context context, ArrayList<RowItem> aRow) {

        this.singleRow = aRow;
        thisInflater = ( LayoutInflater.from(context) );

    }

    @Override
    public int getCount() {
        return singleRow.size( );
    }

    @Override
    public Object getItem(int position) {
        return singleRow.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = thisInflater.inflate( R.layout.textcenter, parent, false );

            TextView theHeading = (TextView) convertView.findViewById(R.id.textName);
            TextView theSubHeading = (TextView) convertView.findViewById(R.id.textPrice);
            ImageView theImage = (ImageView) convertView.findViewById(R.id.img);

            RowItem currentRow = (RowItem) getItem(position);

            theHeading.setText(currentRow.getName());
            theSubHeading.setText(currentRow.getPrice());
            theImage.setImageResource(currentRow.getImg());

        }
        return convertView;
    }
}
