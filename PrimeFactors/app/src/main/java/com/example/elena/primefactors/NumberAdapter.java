package com.example.elena.primefactors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elena on 8/27/2017.
 */

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {
    private List<Integer> numbersList = new ArrayList<>();

    public void setNumbersList(List<Integer> numbersList){
        this.numbersList = numbersList;
        notifyDataSetChanged();
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        holder.textView.setText(numbersList.get(position)+"");
    }

    @Override
    public int getItemCount() {
        if (numbersList == null)
            return 0;
        return numbersList.size();
    }

    class NumberViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        NumberViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view_item);

        }
    }
}
