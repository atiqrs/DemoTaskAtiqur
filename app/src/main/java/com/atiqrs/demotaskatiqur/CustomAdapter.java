package com.atiqrs.demotaskatiqur;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atiqrs.demotaskatiqur.Database.model.Information;

import java.util.ArrayList;
import java.util.List;

class CustomAdapter extends BaseAdapter {

    Context context;
    private List<Information> infoList;
    LayoutInflater inflater;

    public CustomAdapter(Context context, List<Information> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    @Override
    public int getCount() {
        return infoList.size();
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
        if(convertView==null){
        Log.d("last", "onCreate: ****");
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_view,parent,false);
        }
        Log.d("last", "onCreate: #");
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView mobileTextView = convertView.findViewById(R.id.mobileTextView);
        TextView emailTextView = convertView.findViewById(R.id.emailTextView);

        final Information info = infoList.get(position);
        String name = info.getName();
        String mobile = info.getMobile();
        String email = info.getEmail();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ppp", "onClick: "+info.getName());
            }
        });

        nameTextView.setText(name);
        mobileTextView.setText(mobile);
        emailTextView.setText(email);

        return convertView;
    }
}
