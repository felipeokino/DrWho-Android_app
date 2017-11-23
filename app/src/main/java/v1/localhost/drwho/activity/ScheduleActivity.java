package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.R;
import v1.localhost.drwho.adapter.scheduleAdapter.ScheduleAdapter;
import v1.localhost.drwho.classes.ScheduleResponse;
import v1.localhost.drwho.connection.iRetrofit;
import v1.localhost.drwho.models.AppointmentSchedule;
public class ScheduleActivity extends AppCompatActivity {

    ScheduleAdapter scheduleAdapter;
    Button ok;
    RecyclerView recyclerView;
    ArrayList<AppointmentSchedule> appointments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setTitle("Search");

        //InitializeComponent();
        recyclerView = (RecyclerView) findViewById(R.id.lvSchedule);
        appointments = new ArrayList<>();
        ok = (Button) findViewById(R.id.btnOk);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadList();
            }
        });

    }

    public void LoadList() {
        try {
            final iRetrofit appointment = iRetrofit.retrofit.create(iRetrofit.class);
            Call<ScheduleResponse> _call = appointment.GetAllSchedules();
            _call.enqueue(new Callback<ScheduleResponse>() {
                @Override
                public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                    appointments = response.body().getResults();
                    recyclerView.setLayoutManager(new LinearLayoutManager(ScheduleActivity.this));
                    scheduleAdapter = new ScheduleAdapter(appointments, getApplicationContext());
                    recyclerView.setAdapter(scheduleAdapter);
                    scheduleAdapter.notifyDataSetChanged();
                    Toast.makeText(getBaseContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

    public void OkPressed(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void InitializeComponent(){
        ok = (Button) findViewById(R.id.btnOk);
        recyclerView = (RecyclerView) findViewById(R.id.lvSchedule);
    }
}
