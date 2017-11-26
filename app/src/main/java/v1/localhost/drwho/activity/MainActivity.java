package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

import v1.localhost.drwho.R;
import v1.localhost.drwho.login.SingletonDoctor;
import v1.localhost.drwho.login.SingletonUser;

public class MainActivity extends AppCompatActivity {
    String type;

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
                intent.putExtra("extras", type);
                startActivity(intent);
            }
        });

        // Marcar uma nova consulta
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!SingletonUser.isClient()){
                    Intent intent = new Intent(getBaseContext(), InConstruction.class);
                    startActivity(intent);
                }if(SingletonUser.isClient()){
                    Intent intent = new Intent(getBaseContext(), SearchDoctor.class);
                    startActivity(intent);
                }
            }
        });

        // Exibir perfil do usuario
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SingletonUser.isClient()){
                    Intent intent = new Intent(getBaseContext(), CreateUser.class);
                    intent.putExtra("extras", type);
                    startActivity(intent);
                }else if (SingletonDoctor.IsDoctor()){
                    Intent intent = new Intent(getBaseContext(), CreateDoctor.class);
                    intent.putExtra("extras", type);
                    startActivity(intent);
                }
            }
        });

        // Exibir informações acerca do aplicativo
        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Information.class);
                startActivity(intent);
            }
        });
    }
}
