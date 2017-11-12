package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import v1.localhost.drwho.R;

public class ChooseDay extends FragmentActivity {

    DatePicker datePicker;
    Button btnAccept, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_day);
        setTitle("Choose day");

        InitializeComponent();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GetDate();
                ChooseDoctor(v);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReturnOrCancel(v);
            }
        });
    }

    public void ChooseDoctor(View view){
        Intent intent = new Intent(this, SearchDoctor.class);
        startActivity(intent);

    }
    public void ReturnOrCancel(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void InitializeComponent(){
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        btnAccept = (Button)findViewById(R.id.btnAccept);
        btnCancel = (Button)findViewById(R.id.btnCancel);

    }
}
