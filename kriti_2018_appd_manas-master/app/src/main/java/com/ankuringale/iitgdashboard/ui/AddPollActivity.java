package com.ankuringale.iitgdashboard.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ankuringale.iitgdashboard.R;
import com.ankuringale.iitgdashboard.data.BasicData;
import com.ankuringale.iitgdashboard.data.Poll;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poll);

        final FirebaseDatabase db = FirebaseDatabase.getInstance();

        Button b = findViewById(R.id.pollSubmit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText questn;
                questn = findViewById(R.id.addQuestion);
                Date date1 = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = dateFormat.format(date1);
                Log.e(AddPollActivity.class.getSimpleName() , date);
                Poll bd = new Poll(questn.getText().toString(),0,0,date);
                DatabaseReference ref = db.getReference("polls");
                ref.child(questn.getText().toString()).setValue(bd);
            }
        });
    }
}
