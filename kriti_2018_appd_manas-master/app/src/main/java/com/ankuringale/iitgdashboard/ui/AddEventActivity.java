package com.ankuringale.iitgdashboard.ui;

import android.os.FileUriExposedException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ankuringale.iitgdashboard.R;
import com.ankuringale.iitgdashboard.data.BasicData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Button  b = findViewById(R.id.submit);

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText from,con,sub,date;
                from = findViewById(R.id.username);
                con = findViewById(R.id.coninput);
                sub = findViewById(R.id.subinput);
                date = findViewById(R.id.dateInput);

                BasicData bd = new BasicData(from.getText().toString(),
                        sub.getText().toString(),
                        con.getText().toString(),
                        date.getText().toString());

                DatabaseReference ref = db.getReference("events");
                ref.child(from.getText().toString()+sub.getText().toString()).setValue(bd);
            }
        });
    }
}
