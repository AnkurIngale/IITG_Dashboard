package com.ankuringale.iitgdashboard.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ankuringale.iitgdashboard.R;
import com.ankuringale.iitgdashboard.data.BasicData;
import com.ankuringale.iitgdashboard.data.Poll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PollHandler extends RecyclerView.Adapter<PollHandler.MyViewHolder> {
    private List<Poll> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView ques,date,percent;
        private RadioButton yes,no;
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref;
        public MyViewHolder(View v) {
            super(v);
            ques = v.findViewById(R.id.question);
            yes = v.findViewById(R.id.yes);
            no = v.findViewById(R.id.no);
            date = v.findViewById(R.id.pollDateHai);
            percent = v.findViewById(R.id.percent);
             ref = db.getReference("polls");
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PollHandler(List<Poll> myDataset , Context context) {
        mDataset = myDataset;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PollHandler.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new vie
        View v =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.poll_card, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.ques.setText(mDataset.get(position).getQuestion());
        holder.date.setText(mDataset.get(position).getDateOfCreation());
        Poll e = mDataset.get(position);

        int percent;
        if(e.getNumberYes() == 0 && e.getNumberNo() == 0)percent = 0;
        else
         percent = (e.getNumberYes() * 100)/(e.getNumberYes() + e.getNumberNo());
        holder.percent.setText(percent+"% people voted positive.");


        holder.ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 String ye = "",n="";
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    ye = ds.child("numberYes").getValue().toString();
                    n = ds.child("numberNo").getValue().toString();
                    Log.v(PollHandler.class.getSimpleName(),ye);
                    Log.v(PollHandler.class.getSimpleName(),n);
                }
                final String YE = ye,N = n;
                holder.yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.ref.child(mDataset.get(position).getQuestion()).child("numberYes").setValue(Integer.parseInt(YE)+1);
                    }
                });

                holder.no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.ref.child(mDataset.get(position).getQuestion()).child("numberNo").setValue(Integer.parseInt(N)+1);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

