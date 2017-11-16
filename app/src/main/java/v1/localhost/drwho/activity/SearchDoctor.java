package v1.localhost.drwho.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.adapter.doctorAdapter.DoctorAdapter;
import v1.localhost.drwho.classes.DoctorResponse;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_doctor);
        setTitle("Search");

        InitializeComponent();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDoctors);
        doctors = new ArrayList<>();


        all = (RadioButton) findViewById(R.id.rbAllDoctor);
        all.setChecked(true);
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
            }
        });


        // Busca todos os doutores

        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(all.isSelected()){
                    SearchAll();
                }else {

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
                    doctorAdapter = new DoctorAdapter(doctors, getApplicationContext());
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

        //noinspection SimplifiableIfStatement
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
}
