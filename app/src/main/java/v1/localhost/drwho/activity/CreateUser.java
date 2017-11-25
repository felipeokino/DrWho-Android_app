package v1.localhost.drwho.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import v1.localhost.drwho.login.SingletonUser;
import v1.localhost.drwho.models.Client;
import v1.localhost.drwho.connection.iRetrofit;

public class CreateUser extends AppCompatActivity {

    Button cancel, done;
    EditText name, cpf, crm, address, phone, birthday, specialization, email, passwd;


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
       // if (ValidateFields()) {
            final Client client = GetClientObject();
            try {
                iRetrofit retrofit = iRetrofit.retrofit.create(iRetrofit.class);
                Call<Client> call = retrofit.addClient(client);
                call.enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(@NonNull Call<Client> call, @NonNull Response<Client> response) {
                        int code = response.code();

                        if (code == 201) {
                            GetByCpf(client.getCpf());
                            ShowAlertDialog();
                            Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Client> call, @NonNull Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

//
        //}
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
        cancel = (Button) findViewById(R.id.btnCancel);
        done = (Button) findViewById(R.id.btnDone);
        name = (EditText)findViewById(R.id.edtName);
        cpf = (EditText)findViewById(R.id.edtCpf);
        address = (EditText)findViewById(R.id.edtAddress);
        phone = (EditText)findViewById(R.id.edtPhone);
        birthday = (EditText)findViewById(R.id.edtBirthday);
        email = (EditText)findViewById(R.id.edtEmail);
        passwd = (EditText)findViewById(R.id.edtPasswd);

        cancel = (Button) findViewById(R.id.btnCancel);
        done = (Button) findViewById(R.id.btnDone);
    }

    public Client GetClientObject(){
        return new Client(  email.getText().toString(),
                                    name.getText().toString(),
                                    cpf.getText().toString(),
                                    address.getText().toString(),
                                    phone.getText().toString(),
                                    false);
    }

    public void ShowAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seja bem-vindo ao DrWho!")
        .setMessage("Seu ID de acesso é: " + SingletonUser.getInstance().getUsuario().getId());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void GetByCpf(String cpf){
        try{
            final iRetrofit clients = iRetrofit.retrofit.create(iRetrofit.class);
            final Call<Client> call = clients.getClientByCpf(cpf);
            call.enqueue(new Callback<Client>() {
                @Override
                public void onResponse(Call<Client> call, Response<Client> response) {
                    //if (response.code() == 200){
                    Client client = response.body();
                    SingletonUser.getInstance().setUsuario(client);
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
//                  }else {
//                            Toast.makeText(getBaseContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
//                        }
                }
                @Override
                public void onFailure(Call<Client> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getBaseContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }finally {

        }
    }

}
