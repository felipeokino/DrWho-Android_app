package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import v1.localhost.drwho.R;


public class ClientDoctor extends AppCompatActivity {

    TextView doctor, patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_doctor);
        this.setTitle("You Are?");

        doctor = (TextView)findViewById(R.id.tvDoctor);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IfDoctor(v);
            }
        });
        patient = (TextView) findViewById(R.id.tvUser);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IfPatient(v);
            }
        });
    }

    public void IfDoctor(View view){
        Intent intent = new Intent(this, CreateDoctor.class);
        startActivity(intent);
    }
    public void IfPatient(View view) {
        Intent intent = new Intent(this, CreateUser.class);
        startActivity(intent);
    }
}
