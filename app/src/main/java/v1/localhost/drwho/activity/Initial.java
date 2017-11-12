package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import v1.localhost.drwho.R;

public class Initial extends AppCompatActivity {
    boolean icon = true;
    ImageButton more, add, myAppointments, editProfile;
    Button search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inital);
        setTitle("Bem-Vindo: ");
        InitializerComponents();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDoctor(v);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(icon) {
                    more.setImageResource(R.drawable.minus);
                    add.setVisibility(View.VISIBLE);
                    myAppointments.setVisibility(View.VISIBLE);
                    editProfile.setVisibility(View.VISIBLE);
                    icon = false;

                }else {
                    more.setImageResource(R.drawable.more);
                    icon = true;
                    add.setVisibility(View.INVISIBLE);
                    myAppointments.setVisibility(View.INVISIBLE);
                    editProfile.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void InitializerComponents(){
        more = (ImageButton)findViewById(R.id.btnMore);
        add = (ImageButton)findViewById(R.id.btnAddAppointment);
        myAppointments = (ImageButton)findViewById(R.id.btnMyAppointments);
        editProfile = (ImageButton)findViewById(R.id.btnProfile);
        search = (Button)findViewById(R.id.btnSearch);
    }

    public void SearchDoctor(View v){
        Intent intent = new Intent(this, SearchDoctor.class);
        startActivity(intent);
    }
}
