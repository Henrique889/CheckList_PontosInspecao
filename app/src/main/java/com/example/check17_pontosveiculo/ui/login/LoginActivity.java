package com.example.check17_pontosveiculo.ui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.checkList.CheckList17;
import com.example.check17_pontosveiculo.ui.login.registro.Registro_User;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    TextView tvRegister;
    Button loginButton ;


    String url = "http://transcr10.com/chek17pontos/login_usuario.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.username);
        etPassword = (EditText) findViewById(R.id.password);
        tvRegister = (TextView)findViewById(R.id.tv_register);
        loginButton = findViewById(R.id.login);

        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,
                        Registro_User.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);

                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();

                new LoginUser().execute(Email, Password);
                loadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public class LoginUser extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String Email = strings[0];
            String Password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("user_id", Email)
                    .add("user_password", Password)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    assert response.body() != null;
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        Intent i = new Intent(LoginActivity.this,
                                CheckList17.class);
                        startActivity(i);
                        finish();
                    }else{
                        showToast("E-mail ou senha é incompatível!");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
    public void showToast(final String Text){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }
}
