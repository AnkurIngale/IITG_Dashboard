package com.ankuringale.iitgdashboard.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.ankuringale.iitgdashboard.R;
import com.ankuringale.iitgdashboard.adapter.EventHandler;
import com.ankuringale.iitgdashboard.adapter.PollHandler;
import com.ankuringale.iitgdashboard.data.BasicData;
import com.ankuringale.iitgdashboard.data.Poll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PollActivity extends AppCompatActivity {

    public List<Poll> myDataset;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("polls");

        myDataset = new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myDataset.clear();
                for(DataSnapshot s : dataSnapshot.getChildren())
                {
                    //Log.e(MainActivity.class.getSimpleName(), "Haa");
                    Poll post = s.getValue(Poll.class);
                    myDataset.add(post);
                    //Toast.makeText(MainActivity.this,post.getSubject(),Toast.LENGTH_LONG).show();
                }
                Collections.sort(myDataset);
                mRecyclerView = findViewById(R.id.mPollrv);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(PollActivity.this);
                mRecyclerView.setLayoutManager(mLayoutManager);

                // specify an adapter (see also next example)
                mAdapter = new PollHandler(myDataset, PollActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poll_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        switch(menuItem.getItemId())
        {
            case R.id.pollAdder:
                Intent i  = new Intent(this , AddPollActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

}
