package com.example.check17_pontosveiculo.checkList;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.checkList.imagem.imageView_Container;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckList_Container extends AppCompatActivity {
    CheckBox checkBox_secao,checkBox_secao2,checkBox_secao3,checkBox_portas_interiores,checkBox_portas_interiores2,
            checkBox_portas_interiores3,checkBox_ladodireito,checkBox_ladodireito2,checkBox_ladodireito3,
            checkBox_ladoesquerdo,checkBox_ladoesquerdo2,checkBox_ladoesquerdo3,checkBoxParede_Dianteira,
            checkBoxParede_Dianteira2,checkBoxParede_Dianteira3,checkBox_Teto,checkBox_Teto2,checkBox_Teto3,
            checkBox_piso,checkBox_piso2,checkBox_piso3;
    EditText txt_secao,txt_portas_interiores,txt_ladodireito,txt_ladoesquerdo,txtparede_Dianteira,txt_Teto,txtPiso;
    Button btn_enviar;
    String url_Register = "http://transcr10.com/chek17pontos/cadastro_containers.php";
    EditText txtNumero,txtData_Horario,txtTransportadora,txt_nome,txtPlaca_Caminhao,txtPlaca_Carreta,txtNumero_lacreOEA,txtNum_Lacre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_container);

        checkBox_secao = findViewById(R.id.checkBox_secao_inferior);
        checkBox_secao2 = findViewById(R.id.checkBox_secao_inferior2);
        checkBox_secao3 = findViewById(R.id.checkBox_secao_inferior3);
        txt_secao = findViewById(R.id.txt_secao_inferior);
        checkBox_portas_interiores = findViewById(R.id.checkBox_portas_interiores_exterior);
        checkBox_portas_interiores2 = findViewById(R.id.checkBox_portas_interiores_exterior2);
        checkBox_portas_interiores3 = findViewById(R.id.checkBox_portas_interiores_exterior3);
        txt_portas_interiores = findViewById(R.id.txt_portas_interiores_exterior);
        checkBox_ladodireito = findViewById(R.id.checkBox_lado_direito);
        checkBox_ladodireito2 = findViewById(R.id.checkBox_lado_direito2);
        checkBox_ladodireito3 = findViewById(R.id.checkBox_lado_direito3);
        txt_ladodireito = findViewById(R.id.txt_lado_direito);
        checkBox_ladoesquerdo = findViewById(R.id.checkBox_lado_esquerdo);
        checkBox_ladoesquerdo2 = findViewById(R.id.checkBox_lado_esquerdo2);
        checkBox_ladoesquerdo3 = findViewById(R.id.checkBox_lado_esquerdo3);
        txt_ladoesquerdo = findViewById(R.id.txt_lado_esquerdo);
        checkBoxParede_Dianteira = findViewById(R.id.checkBox_p_Dianteira);
        checkBoxParede_Dianteira2 = findViewById(R.id.checkBox_p_Dianteira2);
        checkBoxParede_Dianteira3 = findViewById(R.id.checkBox_p_Dianteira3);
        txtparede_Dianteira = findViewById(R.id.txt_paredeDianteira);
        checkBox_Teto = findViewById(R.id.checkBox_Teto_interior);
        checkBox_Teto2 = findViewById(R.id.checkBox_Teto_interior2);
        checkBox_Teto3 = findViewById(R.id.checkBox_Teto_interior3);
        txt_Teto = findViewById(R.id.txt_Teto_interior);
        checkBox_piso = findViewById(R.id.checkBox_Piso);
        checkBox_piso2 = findViewById(R.id.checkBox_Piso2);
        checkBox_piso3 = findViewById(R.id.checkBox_Piso3);
        txtPiso = findViewById(R.id.txt_Piso);


        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.container);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main:
                        startActivity(new Intent(getApplicationContext(),CheckList17.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.check1:
                        startActivity(new Intent(getApplicationContext(),CheckList_Part1.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.check2:
                        startActivity(new Intent(getApplicationContext(), CheckList_Part2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.container:
                        return true;
                    case R.id.container2:
                        startActivity(new Intent(getApplicationContext(), CheckList_Container2.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });

        btn_enviar = findViewById(R.id.btn_Envio);

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                String Numero_Processo = bundle.getString("ONE");
                String data_hora = bundle.getString("TWO");
                String Transportadora = bundle.getString("THREE");
                String Nome = bundle.getString("FOUR");
                String Placa_Caminhao = bundle.getString("FIVE");
                String Placa_Carreta = bundle.getString("SIX");
                String Numero_lacreOEA = bundle.getString("SEVEN");
                String Num_Lacre = bundle.getString("EIGHT");

                String  checkBox_Secao = "",checkBox_Secao2 = "",checkBox_Secao3 = "",checkBox_Portas_interiores="",
                        checkBox_Portas_interiores2="",checkBox_Portas_interiores3="", checkBox_Ladodireito = "",
                        checkBox_Ladodireito2= "",checkBox_Ladodireito3= "",checkBox_Ladoesquerdo="",
                        checkBox_Ladoesquerdo2="",checkBox_Ladoesquerdo3="", checkBox_parede_dianteira = "",
                        checkBox_parede_dianteira2 = "",checkBox_parede_dianteira3="",
                        checkBox_teto = "", checkBox_teto2="",checkBox_teto3="",
                        checkBoxPiso = "",checkBoxPiso2="",checkBoxPiso3="";

                String secao = null,portas= null,ladoDireito= null,ladoEsquerdo= null,
                        parede=null,teto = null,piso=null;
                if(checkBox_secao.isChecked()) {

                    checkBox_Secao = checkBox_secao.getText().toString();
                    secao = checkBox_secao.getText().toString();
                }

                if(checkBox_secao2.isChecked()) {

                    checkBox_Secao2 = checkBox_secao2.getText().toString();
                    secao = checkBox_secao2.getText().toString();
                }
                if(checkBox_secao3.isChecked()) {
                    checkBox_Secao3 = checkBox_secao3.getText().toString();
                    secao = checkBox_secao3.getText().toString();
                }


                String txt_Secao = txt_secao.getText().toString();

                if(checkBox_portas_interiores.isChecked()){

                    checkBox_Portas_interiores = checkBox_portas_interiores.getText().toString();
                    portas = checkBox_portas_interiores.getText().toString();
                }
                if(checkBox_portas_interiores2.isChecked()){

                    checkBox_Portas_interiores2 = checkBox_portas_interiores2.getText().toString();
                    portas = checkBox_portas_interiores2.getText().toString();
                }
                if(checkBox_portas_interiores3.isChecked()) {
                    checkBox_Portas_interiores3 = checkBox_portas_interiores3.getText().toString();
                    portas = checkBox_portas_interiores3.getText().toString();

                }

                String txt_Portas_interiores = txt_portas_interiores.getText().toString();

                if(checkBox_ladodireito.isChecked()){

                    checkBox_Ladodireito = checkBox_ladodireito.getText().toString();
                    ladoDireito = checkBox_ladodireito.getText().toString();
                }
                if(checkBox_ladodireito2.isChecked()){

                    checkBox_Ladodireito2 = checkBox_ladodireito2.getText().toString();
                    ladoDireito = checkBox_ladodireito2.getText().toString();
                }
                if(checkBox_ladodireito3.isChecked()) {
                    checkBox_Ladodireito3 = checkBox_ladodireito3.getText().toString();
                    ladoDireito = checkBox_ladodireito3.getText().toString();

                }

                String txt_Ladodireito = txt_ladodireito.getText().toString();

                if(checkBox_ladoesquerdo.isChecked()){

                    checkBox_Ladoesquerdo = checkBox_ladoesquerdo.getText().toString();
                    ladoEsquerdo = checkBox_ladoesquerdo.getText().toString();
                }
                if(checkBox_ladoesquerdo2.isChecked()){

                    checkBox_Ladoesquerdo2 = checkBox_ladoesquerdo2.getText().toString();
                    ladoEsquerdo = checkBox_ladoesquerdo2.getText().toString();
                }
                if(checkBox_ladoesquerdo3.isChecked()) {
                    checkBox_Ladoesquerdo3 = checkBox_ladoesquerdo3.getText().toString();
                    ladoEsquerdo = checkBox_ladoesquerdo3.getText().toString();

                }

                String txt_Ladoesquerdo = txt_ladoesquerdo.getText().toString();

                if (checkBoxParede_Dianteira.isChecked()) {

                    checkBox_parede_dianteira = checkBoxParede_Dianteira.getText().toString();
                    parede = checkBoxParede_Dianteira.getText().toString();
                }

                if (checkBoxParede_Dianteira2.isChecked()) {

                    checkBox_parede_dianteira2 = checkBoxParede_Dianteira2.getText().toString();
                    parede = checkBoxParede_Dianteira2.getText().toString();
                }

                if (checkBoxParede_Dianteira3.isChecked()) {
                    checkBox_parede_dianteira3 = checkBoxParede_Dianteira3.getText().toString();
                    parede = checkBoxParede_Dianteira3.getText().toString();

                }
                String  txtParededianteira = txtparede_Dianteira.getText().toString();


                if(checkBox_Teto.isChecked()){
                    checkBox_teto = checkBox_Teto.getText().toString();
                    teto = checkBox_Teto.getText().toString();
                }
                if(checkBox_Teto2.isChecked()){
                    checkBox_teto2 = checkBox_Teto2.getText().toString();
                    teto = checkBox_Teto2.getText().toString();
                }
                if(checkBox_Teto3.isChecked()) {
                    checkBox_teto3 = checkBox_Teto3.getText().toString();
                    teto = checkBox_Teto3.getText().toString();
                }

                String txt_teto = txt_Teto.getText().toString();

                if(checkBox_piso.isChecked()){

                    checkBoxPiso = checkBox_piso.getText().toString();
                    piso = checkBox_piso.getText().toString();
                }
                if(checkBox_piso2.isChecked()){
                    checkBoxPiso2 = checkBox_piso2.getText().toString();
                    piso = checkBox_piso2.getText().toString();
                }
                if(checkBox_piso3.isChecked()) {
                    checkBoxPiso3 = checkBox_piso3.getText().toString();
                    piso = checkBox_piso3.getText().toString();

                }

                String txt_piso = txtPiso.getText().toString();


                new Registrando_Check().execute(Numero_Processo, data_hora, Transportadora, Nome, Placa_Caminhao, Placa_Carreta, Numero_lacreOEA, Num_Lacre,
                        checkBox_Secao,checkBox_Secao2,checkBox_Secao3,txt_Secao,checkBox_Portas_interiores,
                        checkBox_Portas_interiores2,checkBox_Portas_interiores3,txt_Portas_interiores,
                        checkBox_Ladodireito, checkBox_Ladodireito2,checkBox_Ladodireito3,txt_Ladodireito,
                        checkBox_Ladoesquerdo,checkBox_Ladoesquerdo2,checkBox_Ladoesquerdo3,txt_Ladoesquerdo,
                        checkBox_parede_dianteira,checkBox_parede_dianteira2,checkBox_parede_dianteira3,
                        txtParededianteira,checkBox_teto , checkBox_teto2,checkBox_teto3,txt_teto,checkBoxPiso,
                        checkBoxPiso2, checkBoxPiso3,txt_piso);

                Intent email = new Intent(android.content.Intent.ACTION_SEND);

                /* Fill it with Data */
                email.setType("plain/text");
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"informatica@transcr.com.br,operacional.vcp@transcr.com.br"});
                email.putExtra(android.content.Intent.EXTRA_SUBJECT, "CheckList Inspeção do Container");
                email.putExtra(android.content.Intent.EXTRA_TEXT,"Número do Processo: "+bundle.getString("ONE")+
                        ", Data / horário da Vistoria: "+bundle.getString("TWO")+", Transportadora: "+bundle.getString("THREE")+
                        ", Nome do Motorista: "+bundle.getString("FOUR")+ ", Placa do Cavalo / Caminhão: "+bundle.getString("FIVE")+
                        ", Placa da Carreta: "+bundle.getString("SIX")+ ", Número do lacre OEA: "+bundle.getString("SEVEN")+
                        ", Número do lacre do Armador: "+bundle.getString("EIGHT")+" - PONTOS DE INSPEÇÃO "+
                        ", Seção inferior externa: "+ secao.toString()+", Anotação: "+ txt_Secao.toString()+
                        ", Portas interiores/exteriores: "+ portas.toString()+ ", Anotação: "+ txt_Portas_interiores.toString()+
                        ", Lado direito: "+ ladoDireito.toString()+", Anotação: "+ txt_Ladodireito.toString()+", Lado esquerdo: "+ ladoEsquerdo.toString()+
                        ", Anotação: "+ txt_Ladoesquerdo.toString()+", Parede dianteira: "+ parede.toString()+", Anotação: "+ txtParededianteira.toString()+
                        ", Teto interior/exterior: "+ teto.toString()+", Anotação: "+ txt_teto.toString()+", Piso: "+ piso.toString()+
                        ", Anotação: "+ txt_piso.toString());

                /* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(email, "Enviando E-mail..."));

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.i1:
                //perform any action;
                Intent myIntent = new Intent(CheckList_Container.this,
                        imageView_Container.class);
                startActivity(myIntent);
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class Registrando_Check extends AsyncTask<String, Void, String> {
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

            String CheckBox_secao = strings[8];
            String CheckBox_secao2= strings[9];
            String CheckBox_secao3= strings[10];
            String Txt_secao= strings[11];
            String CheckBox_Portas_interiores = strings[12];
            String CheckBox_Portas_interiores2 = strings[13];
            String CheckBox_Portas_interiores3= strings[14];
            String Txt_Portas_interiores = strings[15];
            String CheckBox_Ladodireito = strings[16];
            String CheckBox_Ladodireito2= strings[17];
            String CheckBox_Ladodireito3= strings[18];
            String Txt_Ladodireito = strings[19];
            String CheckBox_Ladoesquerdo= strings[20];
            String CheckBox_Ladoesquerdo2= strings[21];
            String CheckBox_Ladoesquerdo3= strings[22];
            String Txt_Ladoesquerdo= strings[23];
            String CheckBox_parede_dianteira= strings[24];
            String CheckBox_parede_dianteira2= strings[25];
            String CheckBox_parede_dianteira3 = strings[26];
            String TxtParede_dianteira= strings[27];
            String CheckBox_teto= strings[28];
            String CheckBox_teto2= strings[29];
            String CheckBox_teto3= strings[30];
            String Txt_teto= strings[31];
            String CheckBoxPiso= strings[32];
            String CheckBoxPiso2= strings[33];
            String CheckBoxPiso3= strings[34];
            String Txt_piso= strings[35];


            String finalURL = url_Register + "?numero_embarque=" + Numero_Processo +
                    "&data_horario=" + data_hora + "&transportadora=" + Transportadora + "&nome_motorista=" + Nome +
                    "&placa_caminhao=" + Placa_Caminhao + "&placa_carreta=" + Placa_Carreta +
                    "&num_lacreOEA=" + Numero_lacreOEA + "&num_lacre=" + Num_Lacre + "&secao_inferior=" + CheckBox_secao +
                    "&secao_inferior2=" + CheckBox_secao2 + "&secao_inferior3=" + CheckBox_secao3 +
                    "&secao_inferior_anotacao="+ Txt_secao + "&portas_interiores=" + CheckBox_Portas_interiores +
                    "&portas_interiores2=" + CheckBox_Portas_interiores2 + "&portas_interiores3=" + CheckBox_Portas_interiores3 +
                    "&portas_interiores_anotacao="+ Txt_Portas_interiores + "&lado_direito=" + CheckBox_Ladodireito +
                    "&lado_direito2=" + CheckBox_Ladodireito2 + "&lado_direito3=" + CheckBox_Ladodireito3 +
                    "&lado_direito_anotacao="+ Txt_Ladodireito + "&lado_esquerdo=" + CheckBox_Ladoesquerdo +
                    "&lado_esquerdo2=" + CheckBox_Ladoesquerdo2 + "&lado_esquerdo3=" + CheckBox_Ladoesquerdo3 +
                    "&lado_esquerdo_anotacao="+ Txt_Ladoesquerdo  + "&paredeDianteira=" + CheckBox_parede_dianteira +
                    "&paredeDianteira2=" + CheckBox_parede_dianteira2 + "&paredeDianteira3=" + CheckBox_parede_dianteira3 +
                    "&paredeDianteira_anotacao="+ TxtParede_dianteira + "&teto_interior=" + CheckBox_teto +
                    "&teto_interior2=" + CheckBox_teto2 + "&teto_interior3=" + CheckBox_teto3 +
                    "&teto_interior_anotacao="+ Txt_teto + "&piso=" + CheckBoxPiso +
                    "&piso2=" + CheckBoxPiso2 + "&piso3=" + CheckBoxPiso3 + "&piso_anotacao="+ Txt_piso;

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
                Toast.makeText(CheckList_Container.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }


}
