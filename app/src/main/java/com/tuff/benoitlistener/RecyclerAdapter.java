package com.tuff.benoitlistener;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tuffery on 23/08/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.BenoitHolder> {
    public List<BenoitModel> benoits;

    public RecyclerAdapter(List<BenoitModel> benoits) {
        this.benoits = benoits;
    }


    @Override
    public BenoitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.benoit_item, parent, false);

        return new BenoitHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BenoitHolder holder, int position) {
        holder.benoitName.setText(benoits.get(position).name);
        if (benoits.get(position).isMoved) {
            holder.benoitName.setX(40);
        }
    }

    @Override
    public int getItemCount() {
        if (benoits == null) {
            return 0;
        } else {
            return benoits.size();
        }
    }

    public static class BenoitHolder extends RecyclerView.ViewHolder {
        public TextView benoitName;

        public BenoitHolder(View itemView) {
            super(itemView);
            benoitName = (TextView) itemView.findViewById(R.id.benoitName);
        }
    }
}
