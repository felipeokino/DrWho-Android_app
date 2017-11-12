package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.R;
import v1.localhost.drwho.models.Client;
import v1.localhost.drwho.connection.iRetrofit;

public class CreateUser extends AppCompatActivity {

    Button cancel, done;
    EditText name, cpf, crm, address, phone, birthday, specialization, email, passwd, passwd2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        setTitle("New User");

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
        Client client = GetClientObject();

        try{
            iRetrofit retrofit = iRetrofit.retrofit.create(iRetrofit.class);
            Call<Client> call = retrofit.addClient(client);
            call.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    int code = response.code();

                    if (code == 201){
                        Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso: " + String.valueOf(code),
                                Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(),
                            Toast.LENGTH_LONG).show();

                }
            });

        }catch (Exception e){

        }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
    public void InitializeComponents(){
        cancel = (Button) findViewById(R.id.btnCancel);
        done = (Button) findViewById(R.id.btnDone);
        name = (EditText)findViewById(R.id.edtName);
        cpf = (EditText)findViewById(R.id.edtCpf);
        address = (EditText)findViewById(R.id.edtAddress);
        phone = (EditText)findViewById(R.id.edtPhone);
        birthday = (EditText)findViewById(R.id.edtBirthday);
        email = (EditText)findViewById(R.id.edtEmail);
        passwd = (EditText)findViewById(R.id.edtPasswd);
        passwd2 = (EditText)findViewById(R.id.edtPasswd2);

        cancel = (Button) findViewById(R.id.btnCancel);
        done = (Button) findViewById(R.id.btnDone);
    }

    //String email, String senha, String name, String cpf, String address, String phoneNumber
    public Client GetClientObject(){
        Client client = new Client(  email.getText().toString(),
                                    name.getText().toString(),
                                    cpf.getText().toString(),
                                    address.getText().toString(),
                                    phone.getText().toString(),
                                    false);
        return client;
    }
}
