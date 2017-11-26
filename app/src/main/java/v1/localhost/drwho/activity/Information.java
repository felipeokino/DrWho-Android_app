package v1.localhost.drwho.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import v1.localhost.drwho.R;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        setTitle("DR. WHO \n Version 0.1");
    }
}
