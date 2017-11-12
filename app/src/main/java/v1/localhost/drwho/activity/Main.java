package v1.localhost.drwho.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import v1.localhost.drwho.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        final TextView txtNome = (TextView) findViewById(R.id.txtNome);
        final TextView txtSpecs = (TextView) findViewById(R.id.txtSpecs);
        Button sincrono = (Button) findViewById(R.id.btnSincrono);
        Button btnClear = (Button) findViewById(R.id.btnClear);

        sincrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetrofit githubUser = iRetrofit.retrofit.create(iRetrofit.class);
                final Call<> call = githubUser.getDoctors(2);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        int code = response.code();
                        if (code == 200) {
                            Usuario usuario = response.body();
                            txtNome.setText(usuario.getName());
                            txtSpecs.setText(usuario.getSpecialization());
                            Toast.makeText(getBaseContext(), "Nome do usuário: " +
                                    usuario.getName(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        //Seguidores

        Button seguidores = (Button) findViewById(R.id.btnAsc);
        /*seguidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRetrofit gitHubFollowers = iRetrofit.retrofit.create(iRetrofit.class);
                final Call<List<Usuario>> call = gitHubFollowers.getDoctors(2);
                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        List<Usuario> lista = response.body();
                        for (Usuario usuario : lista) {
                            Log.d("MainActivity", usuario.name);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {

                    }
                });
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNome.setText("");
                txtSpecs.setText("");
            }
        });*/
    }
}
