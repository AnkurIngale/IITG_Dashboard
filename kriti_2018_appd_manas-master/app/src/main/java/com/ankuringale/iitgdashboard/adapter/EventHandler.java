package com.ankuringale.iitgdashboard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankuringale.iitgdashboard.R;
import com.ankuringale.iitgdashboard.data.BasicData;

import java.util.List;

public class EventHandler extends RecyclerView.Adapter<EventHandler.MyViewHolder> {
        private List<BasicData> mDataset;
        private Context context;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private TextView sender,subject,basic,date;
            public MyViewHolder(View v) {
                super(v);
                sender = v.findViewById(R.id.from);
                subject = v.findViewById(R.id.subject);
                basic = v.findViewById(R.id.basicContent);
                date = v.findViewById(R.id.date);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public EventHandler(List<BasicData> myDataset , Context context) {
            mDataset = myDataset;
            this.context = context;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public EventHandler.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
            // create a new vie
            View v =
                    LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_card, parent, false);

            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.sender.setText(mDataset.get(position).getSender());
            holder.subject.setText(mDataset.get(position).getSubject());
            holder.basic.setText(mDataset.get(position).getBasic());
            holder.date.setText(mDataset.get(position).getEventDate());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

