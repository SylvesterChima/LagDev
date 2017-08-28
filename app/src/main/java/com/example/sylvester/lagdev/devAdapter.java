package com.example.sylvester.lagdev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sylvester.lagdev.model.developers;

import java.util.List;

/**
 * Created by Sylvester on 28/08/2017.
 */

public class devAdapter extends ArrayAdapter<developers> {
    public devAdapter(Context context, int resource){
        super(context, resource);
    }
    public devAdapter(Context context, int resource, List<developers> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.java_developers, null);
        }

        developers p = getItem(position);

        if (p != null) {
            final ImageView picUser = (ImageView) v.findViewById(R.id.picUser);
            TextView lblName = (TextView) v.findViewById(R.id.lblListName);
            ProgressBar imgLoading = (ProgressBar) v.findViewById(R.id.imgLoading);

            if(picUser != null){
                if(p.getAvatar_url() != null){
                    Glide.with(getContext())
                            .load(p.getAvatar_url())
                            .placeholder(R.drawable.ic_person_black_24dp)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(picUser);
                    imgLoading.setVisibility(View.INVISIBLE);
                }
                else {
                    imgLoading.setVisibility(View.INVISIBLE);
                    picUser.setImageResource(R.drawable.ic_person_black_24dp);
                }

            }
            if (lblName != null) {
                lblName.setText(p.getLogin());
            }
        }

        return v;
    }
}
