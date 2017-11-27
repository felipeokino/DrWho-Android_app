package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import v1.localhost.drwho.login.SingletonUser;
import v1.localhost.drwho.models.Client;
import v1.localhost.drwho.connection.iRetrofit;
import v1.localhost.drwho.utils.MaskUtils;

public class CreateUser extends AppCompatActivity {

    Button cancel, done, ok;
    EditText name, cpf, address, phone, birthday, email, passwd;
    TextView _birthday;
    boolean _exists = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        setTitle("New User");
        InitializeComponents();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            bindActivity();
        }
        cpf.addTextChangedListener(MaskUtils.insert(cpf, MaskUtils.MaskType.CPF));
        phone.addTextChangedListener(MaskUtils.insert(phone, MaskUtils.MaskType.PHONE));
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
        if (ValidateFields()) {
            Verify(cpf.getText().toString());
            if(!is_exists()) {
                final Client client = GetClientObject();
                try {
                    iRetrofit retrofit = iRetrofit.retrofit.create(iRetrofit.class);
                    Call<Client> call = retrofit.addClient(client);
                    call.enqueue(new Callback<Client>() {
                        @Override
                        public void onResponse(@NonNull Call<Client> call, @NonNull Response<Client> response) {
                            int code = response.code();

                            if (code == 201) {
                                Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso: " + String.valueOf(code),
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getBaseContext(), Login.class);
                                startActivity(intent);


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
            }else{
                Toast.makeText(getBaseContext(), "Usuário existente", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getBaseContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
        }
    }

    public void Cancel(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public boolean ValidateFields(){
        if(name.getText() == null)
            if (cpf.getText() == null)
                if(address.getText() == null)
                    if(phone.getText() == null)
                        if(birthday.getText() == null)
                            if(email.getText() == null)
                                if(passwd.getText() == null)
                                    return false;

        return true;
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
        _birthday = (TextView)findViewById(R.id.tvBirthday);
        cancel = (Button) findViewById(R.id.btnCancel);
        done = (Button) findViewById(R.id.btnDone);
        ok = (Button) findViewById(R.id.btnOk);
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
        builder.setCancelable(false);
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

    public void bindActivity(){
        name.setText(SingletonUser.getInstance().getUsuario().getName());
        name.setKeyListener(null);
        cpf.setText(SingletonUser.getInstance().getUsuario().getCpf());
        cpf.setKeyListener(null);
        birthday.setVisibility(View.INVISIBLE);
        _birthday.setVisibility(View.INVISIBLE);
        address.setText(SingletonUser.getInstance().getUsuario().getAddress());
        address.setKeyListener(null);
        phone.setText(SingletonUser.getInstance().getUsuario().getPhoneNumber());
        phone.setKeyListener(null);
        email.setText(SingletonUser.getInstance().getUsuario().getEmail());
        email.setKeyListener(null);
        passwd.setText("***********");
        passwd.setKeyListener(null);
        cancel.setVisibility(View.INVISIBLE);
        done.setVisibility(View.INVISIBLE);
        ok.setVisibility(View.VISIBLE);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Verify(String _cpf){
        final iRetrofit client = iRetrofit.retrofit.create(iRetrofit.class);
        Call<Client> call = client.getClientByCpf(_cpf);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if(response.code() == 200){
                    Toast.makeText(getBaseContext(), "Usuario existente", Toast.LENGTH_LONG).show();
                    set_exists(true);
                }else if(response.code() == 404){
                    set_exists(false);
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Usuario existente", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean is_exists() {
        return _exists;
    }

    public void set_exists(boolean _exists) {
        this._exists = _exists;
    }
}
