package com.example.sina.sinta;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sina on 3/8/18.
 */

public class CustomListAdapter extends ArrayAdapter<String>
{
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgID;
    private final boolean[] descryp;
    private final String[] paths;

    public CustomListAdapter(Activity context,String[] itemname , String[] itempath , Integer[] imgid , boolean[] descryption)
    {
        super(context,R.layout.target_item,itemname);

        this.context = context;
        this.itemname = itemname;
        this.imgID = imgid;
        this.descryp = descryption;
        this.paths = itempath;
    }
    public View getView(int position, View v, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.target_item,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item_title);
        ImageView imgIcone = (ImageView)rowView.findViewById(R.id.item_icon);
        TextView txtDescryption = (TextView) rowView.findViewById(R.id.item_descryption);
        TextView txtPath = (TextView) rowView.findViewById(R.id.item_path);

        txtTitle.setText(itemname[position]);
        imgIcone.setImageResource(imgID[position]);
        txtPath.setText(paths[position]);
        if(descryp[position]){
            txtDescryption.setText("Encrypted");
            txtDescryption.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
        else {
            txtDescryption.setText("UN-Encrypted");
            txtDescryption.setTextColor(context.getResources().getColor(R.color.red));
        }

        return rowView;
    }
}
