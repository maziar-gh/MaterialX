package com.material.components.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.material.components.R;
import com.material.components.model.CardViewImg;
import com.material.components.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class AdapterCardViewImg extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CardViewImg> items = new ArrayList<>();
    private Context ctx;

    public AdapterCardViewImg(Context context, List<CardViewImg> items) {
        this.items = items;
        ctx = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView image;
        public TextView title;
        public TextView subtitle;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            title = (TextView) v.findViewById(R.id.title);
            subtitle = (TextView) v.findViewById(R.id.subtitle);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view_img, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder view = (ViewHolder) holder;
            final CardViewImg o = items.get(position);
            view.title.setText(o.title);
            view.subtitle.setText(o.subtitle);
            Tools.displayImageOriginal(ctx, view.image, o.image);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}