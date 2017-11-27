package com.bouillennec.v.topquiz.controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bouillennec.v.topquiz.R;
import com.bouillennec.v.topquiz.model.User;
import com.bouillennec.v.topquiz.model.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by valbo on 23/09/2017.
 */

public class MyClickAdapter extends RecyclerView.Adapter<MyClickAdapter.MyClickViewHolder> {

    public class MyClickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mFirstName, mScore;
        public MyClickViewHolder(final View itemView) {
            super(itemView);
            mFirstName = ((TextView) itemView.findViewById(R.id.list_cell_firstname));
            mScore = ((TextView) itemView.findViewById(R.id.list_cell_score));

            itemView.setOnClickListener(this);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("Delete")
                            .setMessage("Do you want to delete this user ?")
                            .setNegativeButton("NO", null)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            });*/


        }

        @Override
        public void onClick(View v) {
            if(mOnEntryClickListener != null){
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }

    private ArrayList<User> users = new ArrayList<>();

    public MyClickAdapter(ArrayList<User> arrayList) {
        users.addAll(arrayList);
    }

    @Override
    public MyClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell,parent,false);
        return new MyClickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyClickViewHolder holder, int position) {
        User user = users.get(position);
        String firstname = user.getFirstName();
        int score = user.getLastScore();

        holder.mFirstName.setText(firstname);
        holder.mScore.setText(score+"");
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener){
        mOnEntryClickListener = onEntryClickListener;
    }
}
