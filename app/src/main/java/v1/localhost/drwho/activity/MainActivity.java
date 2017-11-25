package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import v1.localhost.drwho.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton schedule = (ImageButton) findViewById(R.id.btnSchedule);
        ImageButton add = (ImageButton) findViewById(R.id.btnAddAppointment);
        ImageButton profile = (ImageButton) findViewById(R.id.btnProfile);
        ImageButton infos = (ImageButton) findViewById(R.id.btnInfos);

        // Visualizar minhas consultas
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ScheduleActivity.class);
                startActivity(intent);
            }
        });
        // Marcar uma nova consulta
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchDoctor.class);
                startActivity(intent);
            }
        });

        // Exibir perfil do usuario
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Exibir informações acerca do aplicativo
        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
