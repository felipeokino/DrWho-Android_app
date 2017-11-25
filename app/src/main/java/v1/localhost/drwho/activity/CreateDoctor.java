package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import v1.localhost.drwho.models.AppointmentBook;
import v1.localhost.drwho.models.Doctor;
import v1.localhost.drwho.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.connection.iRetrofit;


public class CreateDoctor extends AppCompatActivity implements Serializable{

    private Button cancel, done;
    private EditText name, cpf, crm, address, phone, birthday, specialization, email, passwd;
    //private long lastId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor);
        setTitle("New Doctor");
        InitializeComponents();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel(v);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Done(v);
            }
        });
    }

    public void Done(View view) {
        ValidateFields();
        try{
                AppointmentBook appointmentBook = CreateAppointment();
                Doctor doctor = GetDoctorObject(appointmentBook);

                iRetrofit retrofitDoctors = iRetrofit.retrofit.create(iRetrofit.class);
                Call<Doctor> call = retrofitDoctors.addDoctors(doctor);
                call.enqueue(new Callback<Doctor>() {
                    @Override
                    public void onResponse(@NonNull Call<Doctor> call, @NonNull Response<Doctor> response) {
                        int codeDoctor = response.code();

                        if (codeDoctor == 201){
                            Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso: " + String.valueOf(codeDoctor),
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(codeDoctor) + response.message(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Doctor> call, @NonNull Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Cancel(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public boolean ValidateFields(){
        if(name.getText() == null)
            if (cpf.getText() == null)
                if(crm.getText() == null)
                    if(address.getText() == null)
                        if(phone.getText() == null)
                            if(birthday.getText() == null)
                                if(specialization.getText() == null)
                                    if(email.getText() == null)
                                        if(passwd.getText() == null)
                                                return true;

        return false;
    }

    public void InitializeComponents(){
        name = (EditText)findViewById(R.id.edtName);
        cpf = (EditText)findViewById(R.id.edtCpf);
        crm = (EditText)findViewById(R.id.edtCrm);
        address = (EditText)findViewById(R.id.edtAddress);
        phone = (EditText)findViewById(R.id.edtPhone);
        birthday = (EditText)findViewById(R.id.edtBirthday);
        specialization = (EditText)findViewById(R.id.edtSpecs);
        email = (EditText)findViewById(R.id.edtEmail);
        passwd = (EditText)findViewById(R.id.edtPasswd);

        cancel = (Button) findViewById(R.id.btnCancel);
        done = (Button) findViewById(R.id.btnDone);
    }

    public Doctor GetDoctorObject(AppointmentBook appointmentBook){
        return new Doctor( email.getText().toString(),
                                    name.getText().toString(),
                                    cpf.getText().toString(),
                                    crm.getText().toString(),
                                    address.getText().toString(),
                                    phone.getText().toString(),
                                    specialization.getText().toString(),
                                    appointmentBook,
                                    false);
    }

    public AppointmentBook GetAppointmentBookObject(){

        return new AppointmentBook(false, false, false, false, false, false,
                                                                false, null, null, null, null, false);
    }

    public AppointmentBook CreateAppointment(){
        //GetLastBookId();
        AppointmentBook appointmentBook = GetAppointmentBookObject(/*lastId*/);
        iRetrofit retrofitAppointmentBook = iRetrofit.retrofit.create(iRetrofit.class);
        Call<AppointmentBook> call = retrofitAppointmentBook.addAppointment(appointmentBook);
        call.enqueue(new Callback<AppointmentBook>() {
            @Override
            public void onResponse(@NonNull Call<AppointmentBook> call, @NonNull Response<AppointmentBook> response) {
                if(response.code() == 201){
                    Toast.makeText(getBaseContext(), "Schedule created", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(@NonNull Call<AppointmentBook> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        return appointmentBook;
    }

//    public void GetLastBookId() {
//        try {
//            iRetrofit getBook = iRetrofit.retrofit.create(iRetrofit.class);
//            Call<BookResponse> call = getBook.getBooks();
//            call.enqueue(new Callback<BookResponse>() {
//                @Override
//                public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
//                    if (response.code() == 200) {
//                        if(response.body() == null){
//                            lastId = 1;
//                        }else{
//                            ArrayList<AppointmentBook> books = response.body().getResults();
//                            lastId = response.body().getLastId(books);
//                        }
//                    }
//                }
//                @Override
//                public void onFailure(Call<BookResponse> call, Throwable t) {
//                    Toast.makeText(getBaseContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//        }catch (Exception t){
//            Toast.makeText(getBaseContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
}
