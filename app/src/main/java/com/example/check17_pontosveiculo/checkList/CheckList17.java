package com.example.check17_pontosveiculo.checkList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.check17_pontosveiculo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CheckList17 extends AppCompatActivity {
    EditText txtNumero,txtData_Horario,txtTransportadora,txt_nome,txtPlaca_Caminhao,txtPlaca_Carreta,txtNumero_lacreOEA,txtNum_Lacre;
    Button btnEnviar, Btn_limpar;
    ProgressDialog progress;

    String Numero_Processo , data_hora, Transportadora,Nome,placa_Caminhao,placa_Carreta,
            Numero_lacreOEA,Numero_Lacre;

    String url_Register = "http://transcr10.com/chek17pontos/cadastro_info.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkinfo);
        txtNumero = findViewById(R.id.txt_numero);
        txtData_Horario = findViewById(R.id.txt_data_horario);
        txtTransportadora = findViewById(R.id.txt_transportadora);
        txt_nome = findViewById(R.id.txt_nome_motorista);
        txtPlaca_Caminhao = findViewById(R.id.txt_placa_caminhao);
        txtPlaca_Carreta = findViewById(R.id.txt_placa_carreta);
        txtNumero_lacreOEA = findViewById(R.id.txt_num_lacre_OEA);
        txtNum_Lacre = findViewById(R.id.txt_num_lacre);
        btnEnviar = findViewById(R.id.btnEnvio);
        Btn_limpar = findViewById(R.id.btnClear);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String NumeroProcesso = prefs.getString("f1","");
        txtNumero.setText(NumeroProcesso);
        String datahora = prefs.getString("f2","");
        txtData_Horario.setText(datahora);
        String transportadora = prefs.getString("f3","");
        txtTransportadora.setText(transportadora);
        String nome = prefs.getString("f4","");
        txt_nome.setText(nome);
        String PlacaCaminhao = prefs.getString("f5","");
        txtPlaca_Caminhao.setText(PlacaCaminhao);
        String PlacaCarreta = prefs.getString("f6","");
        txtPlaca_Carreta.setText(PlacaCarreta);
        String NumerolacreOEA = prefs.getString("f7","");
        txtNumero_lacreOEA.setText(NumerolacreOEA);
        String NumLacre = prefs.getString("f8","");
        txtNum_Lacre.setText(NumLacre);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog( CheckList17.this );
                progress.setMessage( "Carregando..." );
                progress.show();

                Numero_Processo = txtNumero.getText().toString();
                data_hora = txtData_Horario.getText().toString();
                Transportadora = txtTransportadora.getText().toString();
                Nome = txt_nome.getText().toString();
                placa_Caminhao = txtPlaca_Caminhao.getText().toString();
                placa_Carreta = txtPlaca_Carreta.getText().toString();
                Numero_lacreOEA = txtNumero_lacreOEA.getText().toString();
                Numero_Lacre = txtNum_Lacre.getText().toString();

                //To save data
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CheckList17.this);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("f1", Numero_Processo);
                editor.putString("f2", data_hora);
                editor.putString("f3", Transportadora);
                editor.putString("f4", Nome);
                editor.putString("f5", placa_Caminhao);
                if (!placa_Carreta.equals("")) {
                    editor.putString("f6", placa_Carreta);
                }
                editor.putString("f7", Numero_lacreOEA);
                editor.putString("f8", Numero_Lacre);
                editor.apply();

                progress.hide();

                showToast("Cadastro Realizado");

            }
        });

        Btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumero.getText().clear();
                txtData_Horario.getText().clear();
                txtTransportadora.getText().clear();
                txt_nome.getText().clear();
                txtPlaca_Caminhao.getText().clear();
                txtPlaca_Carreta.setText("-");
                txtNumero_lacreOEA.getText().clear();
                txtNum_Lacre.getText().clear();

            }
        });

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.main);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main:
                        return true;
                    case R.id.check1:
                        if (!txtNumero.equals("") && !txtData_Horario.equals("") && !txtTransportadora .equals("") &&
                                !txt_nome.equals("") && !txtPlaca_Caminhao.equals("") && !txtNumero_lacreOEA.equals("") && !txtNum_Lacre.equals("")){
                            Intent i = new Intent(CheckList17.this, CheckList_Part1.class);
                            String Numero_Embarque = txtNumero.getText().toString();
                            String dataHora = txtData_Horario.getText().toString();
                            String transportadora = txtTransportadora.getText().toString();
                            String txtNome = txt_nome.getText().toString();
                            String placaCaminhao = txtPlaca_Caminhao.getText().toString();
                            String placaCarreta = txtPlaca_Carreta.getText().toString();
                            String NumLacreOEA = txtNumero_lacreOEA.getText().toString();
                            String NumeroLacre = txtNum_Lacre.getText().toString();

                            Bundle bundle = new Bundle();
                            bundle.putString("ONE", Numero_Embarque);
                            bundle.putString("TWO", dataHora);
                            bundle.putString("THREE", transportadora);
                            bundle.putString("FOUR", txtNome);
                            bundle.putString("FIVE", placaCaminhao);
                            bundle.putString("SIX", placaCarreta);
                            bundle.putString("SEVEN", NumLacreOEA);
                            bundle.putString("EIGHT", NumeroLacre);

                            i.putExtras(bundle);

                            startActivity(i);}
                        else {
                            startActivity(new Intent(getApplicationContext(), CheckList_Container.class));
                        }
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.check2:
                        startActivity(new Intent(getApplicationContext(), CheckList_Part2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.container:
                        if (!txtNumero.equals("") && !txtData_Horario.equals("") && !txtTransportadora .equals("") &&
                                !txt_nome.equals("") && !txtPlaca_Caminhao.equals("") && !txtPlaca_Carreta.equals("") &&
                                !txtNumero_lacreOEA.equals("") && !txtNum_Lacre.equals("")){
                            Intent intent = new Intent(getApplicationContext(),CheckList_Container.class);

                            String Numero_Embarque = txtNumero.getText().toString();
                            String dataHora = txtData_Horario.getText().toString();
                            String transportadora = txtTransportadora.getText().toString();
                            String txtNome = txt_nome.getText().toString();
                            String placaCaminhao = txtPlaca_Caminhao.getText().toString();
                            String placaCarreta = txtPlaca_Carreta.getText().toString();
                            String NumLacreOEA = txtNumero_lacreOEA.getText().toString();
                            String NumeroLacre = txtNum_Lacre.getText().toString();

                            Bundle bundle = new Bundle();
                            bundle.putString("ONE", Numero_Embarque);
                            bundle.putString("TWO", dataHora);
                            bundle.putString("THREE", transportadora);
                            bundle.putString("FOUR", txtNome);
                            bundle.putString("FIVE", placaCaminhao);
                            bundle.putString("SIX", placaCarreta);
                            bundle.putString("SEVEN", NumLacreOEA);
                            bundle.putString("EIGHT", NumeroLacre);

                            intent.putExtras(bundle);

                            startActivity(intent);}
                        else {
                            startActivity(new Intent(getApplicationContext(), CheckList_Container.class));
                        }
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.container2:
                        startActivity(new Intent(getApplicationContext(), CheckList_Container2.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }

    public class Registrando_1 extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String Numero_Processo = strings[0];
            String data_hora = strings[1];
            String Transportadora = strings[2];
            String Nome = strings[3];
            String Placa_Caminhao = strings[4];
            String Placa_Carreta = strings[5];
            String Numero_lacreOEA = strings[6];
            String Num_Lacre = strings[7];


            String finalURL = url_Register + "?numero_embarque=" + Numero_Processo +
                    "&data_horario=" + data_hora +
                    "&transportadora=" + Transportadora + "&nome_motorista=" + Nome +
                    "&placa_caminhao=" + Placa_Caminhao + "&placa_carreta=" + Placa_Carreta +
                    "&num_lacreOEA=" + Numero_lacreOEA+
                    "&num_lacre=" + Num_Lacre;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(finalURL)
                        .get()
                        .build();
                Response response = null;

                try {
                    response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String result = response.body().string();

                        if (result.equalsIgnoreCase("Registrado")) {
                            showToast("Cadastro Realizado");

                        } else {
                            showToast("Oops! por favor tente novamente");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                Toast.makeText(CheckList17.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }



}
