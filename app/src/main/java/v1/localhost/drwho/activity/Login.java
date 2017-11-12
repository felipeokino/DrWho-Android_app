package v1.localhost.drwho.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import v1.localhost.drwho.R;

public class Login extends AppCompatActivity {

    Button login;
    TextView newUser;
    EditText user, passwd;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        login = (Button) findViewById(R.id.btnLogin);
        newUser = (TextView) findViewById(R.id.tvNewUser);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next(v);
            }
        });
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextNew(v);
            }
        });
    }
    public void Next(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void NextMain(View view){
        EditText user = (EditText) findViewById(R.id.edtUser);
        EditText passwd = (EditText) findViewById(R.id.edtPasswd);
            if(user.getText().toString().equals("admin")){
                if(passwd.getText().toString().equals("1234")){
                    Intent intent = new Intent(this, Initial.class);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(getBaseContext(), "usuario ou senha incorretos", Toast.LENGTH_LONG).show();
            }
    }
    public void NextNew(View view){
        Intent intent = new Intent(this, ClientDoctor.class);
        startActivity(intent);
    }
}
