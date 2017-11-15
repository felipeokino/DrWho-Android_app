package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.R;
import v1.localhost.drwho.classes.ScheduleResponse;
import v1.localhost.drwho.connection.iRetrofitSchedule;
import v1.localhost.drwho.models.AppointmentSchedule;
import v1.localhost.drwho.adapter.*;
public class ScheduleActivity extends AppCompatActivity {

    ScheduleAdapter scheduleAdapter;
    Button ok;
    RecyclerView recyclerView;
    List<AppointmentSchedule> appointments;
    private ArrayList<AppointmentSchedule> schedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setTitle("Search");

        InitializeComponent();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDoctors);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        schedule = new ArrayList<>();
        scheduleAdapter = new ScheduleAdapter(schedule, getApplicationContext());
        recyclerView.setAdapter(scheduleAdapter);
        scheduleAdapter.notifyDataSetChanged();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkPressed(v);
            }
        });

        LoadList();
    }

    public void LoadList(){
        try{
            final iRetrofitSchedule schedules = iRetrofitSchedule.retrofit.create(iRetrofitSchedule.class);
            final Call<ScheduleResponse> call = schedules.GetAllSchedules();
            call.enqueue(new Callback<ScheduleResponse>() {
                @Override
                public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                    appointments = response.body().getResults();

                    if(response.code() == 200){
                        scheduleAdapter.notifyDataSetChanged();
                    }else {
                        Toast.makeText(getBaseContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    }

                }
                @Override
                public void onFailure(Call<ScheduleResponse> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
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
