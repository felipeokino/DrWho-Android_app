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
                //if(ValidateFields())
                    CreateUserLogin(user.getText().toString());

//                else
//                    Toast.makeText(getBaseContext(), "Empty fields", Toast.LENGTH_LONG).show();


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

    public void CreateUserLogin(String cpf){
        try{
                final iRetrofit clients = iRetrofit.retrofit.create(iRetrofit.class);
                final Call<Client> call = clients.getClientByCpf(cpf);
                call.enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(Call<Client> call, Response<Client> response) {
                        if (response.code() == 200){
                            client = response.body();
                            SingletonUser.getInstance().setUsuario(client);
                            Next();
                            Login.super.finish();
                        }else if(response.code() == 404) {
                            CreateDoctorLogin(user.getText().toString());
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

    public void CreateDoctorLogin(String cpf){
        try{
            final iRetrofit clients = iRetrofit.retrofit.create(iRetrofit.class);
            final Call<Client> call = clients.getClientByCpf(cpf);
            call.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    if (response.code() == 200){
                        client = response.body();
                        SingletonUser.getInstance().setUsuario(client);
                        Toast.makeText(getBaseContext(), client.getName(), Toast.LENGTH_LONG).show();
                        Next();
                        Login.super.finish();
                    }else if(response.code() == 404) {
                        Toast.makeText(getBaseContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
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

    public boolean ValidateFields(){
        if (user.getText().length() == 0)
            if(passwd.getText().length() == 0)
                return false;
        return true;
    }


}
