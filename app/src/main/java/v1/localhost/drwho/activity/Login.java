package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import v1.localhost.drwho.R;
import v1.localhost.drwho.connection.iRetrofit;
import v1.localhost.drwho.login.SingletonUser;
import v1.localhost.drwho.models.Client;

public class Login extends AppCompatActivity {

    Button login;
    TextView newUser;
    EditText user, passwd;
    Client client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        login = (Button) findViewById(R.id.btnLogin);
        newUser = (TextView) findViewById(R.id.tvNewUser);
        user = (EditText) findViewById(R.id.edtUser);
        passwd = (EditText) findViewById(R.id.edtPasswd);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateUserLogin(Long.parseLong(user.getText().toString()));
            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextNew(v);
            }
        });


    }
    public void Next(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void NextNew(View view){
        Intent intent = new Intent(this, ClientDoctor.class);
        startActivity(intent);
    }

    public void CreateUserLogin(long id){
        try{
            if(!ValidateFields()){
                Toast.makeText(getBaseContext(), "Empty fields", Toast.LENGTH_LONG).show();
                return;
            }
            final iRetrofit clients = iRetrofit.retrofit.create(iRetrofit.class);
            final Call<Client> call = clients.getClientById(id);
            call.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    if (response.code() == 200){
                        client = response.body();
                        SingletonUser.getInstance().setUsuario(client);
                        Next();
                        Login.super.finish();
                    }else {
                        Toast.makeText(getBaseContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void SearchUser(){
        try {

            iRetrofit getClient = iRetrofit.retrofit.create(iRetrofit.class);
            Call<Client> call = getClient.getClientById(Long.parseLong(user.getText().toString()));
            call.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    if(response.code() == 200){

                    }
                }

                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                }
            });
        }catch (Exception e){

        }
    }
    public boolean ValidateFields(){
        if (user.getText().toString() != null)
            if(passwd.getText().toString() != null)
                return true;
        return false;
    }
}
