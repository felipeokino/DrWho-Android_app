package v1.localhost.drwho.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.adapter.doctorAdapter.DoctorAdapter;
import v1.localhost.drwho.utils.DoctorResponse;
import v1.localhost.drwho.login.SingletonUser;
import v1.localhost.drwho.models.Doctor;
import v1.localhost.drwho.R;
import v1.localhost.drwho.connection.iRetrofit;

public class SearchDoctor extends AppCompatActivity {

    ArrayList<Doctor> doctors;
    EditText search;
    ImageButton image;
    RadioButton all;
    RadioButton specs;
    Spinner spinner;
    private DoctorAdapter doctorAdapter;
    RecyclerView recyclerView;
    Calendar calendar;
    String newDate, startHour, endHour;
    int _dayOfMonth, _month, _year;
    SimpleDateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        setTitle("Search");

        InitializeComponent();
        CreateDatePicker();
        TimePickerDialog();


        //TODO converter date format
        formatter = new SimpleDateFormat("yyyy-MM-dd");


        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDoctors);
        doctors = new ArrayList<>();

        all = (RadioButton) findViewById(R.id.rbAllDoctor);
        all.setChecked(true);
        spinner.setVisibility(View.INVISIBLE);
        specs = (RadioButton) findViewById(R.id.rbSpecs);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                specs.setChecked(false);
            }
        });
        specs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setChecked(false);
                spinner.setVisibility(View.VISIBLE);


            }
        });

        //Adapter utilizado pelo spinner de especializações
        String[] specs = new String[] {"mortologia", "nutricionista", "oncologista", "pediatra", "cardiologista", "anestesista", "dermatologista", "urologista", "proctologista", "ginecologista"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, specs);
        spinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();


        //Busca todos os doutores

        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(all.isChecked() || all.isSelected()){
                    Toast.makeText(getBaseContext(), SingletonUser.getInstance().getUsuario().getName(), Toast.LENGTH_LONG).show();
                    SearchAll();
                }else {
                    String specialization;

                    Toast.makeText(getBaseContext(), _year + "/" + _month + "/" +_dayOfMonth, Toast.LENGTH_LONG).show();
                    specialization = spinner.getSelectedItem().toString();
                    SearchBySpecialization(specialization);
                }
            }

        });
    }

    public void SearchAll(){
        try{
            final iRetrofit allDoctors = iRetrofit.retrofit.create(iRetrofit.class);
            final Call<DoctorResponse> call = allDoctors.getAllDoctors();

            call.enqueue(new Callback<DoctorResponse>() {
                @Override
                public void onResponse(Call<DoctorResponse> call, Response<DoctorResponse> response) {
                    doctors = response.body().getResults();
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchDoctor.this));
                    doctorAdapter = new DoctorAdapter(doctors, getApplicationContext(), newDate, startHour, endHour);
                    Toast.makeText(getBaseContext(), "Day: " + newDate + "Hour: " + startHour + "~" + endHour, Toast.LENGTH_LONG).show();
                    recyclerView.setAdapter(doctorAdapter);
                    doctorAdapter.notifyDataSetChanged();
                    Toast.makeText(getBaseContext(), "Encontrado: " + response.body().getSize(doctors) + " resultado(s)", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<DoctorResponse> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void SearchBySpecialization(String specialization){
        try{
            final iRetrofit specsDoctors = iRetrofit.retrofit.create(iRetrofit.class);
            final Call<DoctorResponse> call = specsDoctors.getBySpecialization(specialization, 0, 20);
            call.enqueue(new Callback<DoctorResponse>() {
                @Override
                public void onResponse(Call<DoctorResponse> call, Response<DoctorResponse> response) {
                    if(response.code() == 200) {
                        doctors = response.body().getResults();
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchDoctor.this));
                        doctorAdapter = new DoctorAdapter(doctors, getApplicationContext(), newDate, startHour, endHour);
                        recyclerView.setAdapter(doctorAdapter);
                        doctorAdapter.notifyDataSetChanged();
                        Toast.makeText(getBaseContext(), "Encontrado: " + response.body().getSize(doctors) + " resultado(s)", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<DoctorResponse> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void InitializeComponent(){
        search = (EditText) findViewById(R.id.edtSearch);
        image = (ImageButton)findViewById(R.id.imgBtnSearch);
        spinner = (Spinner) findViewById(R.id.spSpecs);
    }

    public void CreateDatePicker() {

        calendar = Calendar.getInstance();
        _year = calendar.get(Calendar.YEAR);
        _month =  calendar.get(Calendar.MONTH);
        _dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                newDate = year + "/" + month + "/" +dayOfMonth;
            }
        },_year, _month, _dayOfMonth);
        datePickerDialog.show();
    }

    private void TimePickerDialog() {

        TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                startHour = hour + ":" + minute;
                endHour = (hour+2) + ":" + minute;
            }
        };
        TimePickerDialog dialog = new TimePickerDialog(this, mTimeSetListener,
                Calendar.HOUR_OF_DAY,
                Calendar.MINUTE,
                true);

        dialog.show();
    }
}
