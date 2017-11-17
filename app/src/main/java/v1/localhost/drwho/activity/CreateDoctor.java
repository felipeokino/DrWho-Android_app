package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import v1.localhost.drwho.connection.iRetrofitAppointmentBook;
import v1.localhost.drwho.models.AppointmentBook;
import v1.localhost.drwho.models.Doctor;
import v1.localhost.drwho.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.connection.iRetrofit;


public class CreateDoctor extends AppCompatActivity implements Serializable{

    Button cancel, done;
    EditText name, cpf, crm, address, phone, birthday, specialization, email, passwd, passwd2;
    long lastId = 2;
    Doctor doctor;
    AppointmentBook appointmentBook;
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
        //ValidarCampos();
        try{
            if(ValidarSenha()){
                AppointmentBook appointmentBook = CreateAppointment();
                doctor = GetDoctorObject(appointmentBook);

                iRetrofit retrofitDoctors = iRetrofit.retrofit.create(iRetrofit.class);
                Call<Doctor> call = retrofitDoctors.addDoctors(doctor);
                call.enqueue(new Callback<Doctor>() {
                    @Override
                    public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                        //CreateAppointment();
                        int codeDoctor = response.code();

                        if (codeDoctor == 201){
                            Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso: " + String.valueOf(codeDoctor),
                                    Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(codeDoctor),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Doctor> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                });
            }


        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Cancel(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public boolean ValidarCampos(){
        if(name.getText().equals(null))
            if (cpf.getText().equals(null))
                if(crm.getText().equals(null))
                    if(address.getText().equals(null))
                        if(phone.getText().equals(null))
                            if(birthday.getText().equals(null))
                                if(specialization.getText().equals(null))
                                    if(email.getText().equals(null))
                                        if(passwd.getText().equals(null))
                                            if(passwd2.getText().equals(null))
                                                return true;

        return false;
    }

    public boolean ValidarSenha(){
        /*if(passwd.getText().equals(passwd2))
            return true;
        else{
            Toast.makeText(getBaseContext(), "Senhas nao conferem", Toast.LENGTH_LONG).show();
            return false;}*/
        return true;
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
        passwd2 = (EditText)findViewById(R.id.edtPasswd2);

        cancel = (Button) findViewById(R.id.btnCancel);
        done = (Button) findViewById(R.id.btnDone);
    }

    public Doctor GetDoctorObject(AppointmentBook appointmentBook){
        Doctor doctor = new Doctor( email.getText().toString(),
                                    name.getText().toString(),
                                    cpf.getText().toString(),
                                    crm.getText().toString(),
                                    address.getText().toString(),
                                    phone.getText().toString(),
                                    specialization.getText().toString(),
                                    appointmentBook,
                                    false);
        return doctor;
    }

    public AppointmentBook GetAppointmentBookObject(long idAB){
    AppointmentBook appointmentBook = new AppointmentBook( idAB, false, false, false, false, false, false,
                                                            false, null, null, null, null, false);

        return appointmentBook;
    }

    public AppointmentBook CreateAppointment(){

        appointmentBook = GetAppointmentBookObject(lastId);
        iRetrofit retrofitAppointmentBook = iRetrofit.retrofit.create(iRetrofit.class);
        Call<AppointmentBook> call = retrofitAppointmentBook.addAppointment(appointmentBook);
        call.enqueue(new Callback<AppointmentBook>() {
            @Override
            public void onResponse(Call<AppointmentBook> call, Response<AppointmentBook> response) {

                if(response.code() == 201){
                    Toast.makeText(getBaseContext(), "Schedule created", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), response.message(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<AppointmentBook> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });
        lastId += 1;
        return appointmentBook;
    }
}
