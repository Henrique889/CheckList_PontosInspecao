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
import com.example.check17_pontosveiculo.checkList.imagem.image_View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CheckList_Part1 extends AppCompatActivity {
    CheckBox checkBox_parachoque,checkBox_parachoque2,checkBox_parachoque3,checkBox_motor,checkBox_motor2,checkBox_motor3,
            checkBox_pneus,checkBox_pneus2,checkBox_pneus3,checkBox_piso,checkBox_piso2,checkBox_piso3,checkBox_tanque,checkBox_tanque2,
            checkBox_tanque3,checkBox_cabine,checkBox_cabine2,checkBox_cabine3,checkBox_tanques_ar,checkBox_tanques_ar2,checkBox_tanques_ar3,
            checkBox_eixos,checkBox_eixos2,checkBox_eixos3;
    CheckBox checkBox_roda,checkBox_roda2,checkBox_roda3,checkBox_exterior,checkBox_exterior2,checkBox_exterior3,
            checkBox_piso_interior,checkBox_piso_interior2,checkBox_piso_interior3,checkBox_portas,checkBox_portas2,
            checkBox_portas3,checkBox_paredes,checkBox_paredes2,checkBox_paredes3,checkBox_teto,checkBox_teto2,checkBox_teto3,
            checkBox_parede_dianteira,checkBox_parede_dianteira2,checkBox_parede_dianteira3,checkBox_refrigerador,checkBox_refrigerador2,
            checkBox_refrigerador3,checkBox_escapamento,checkBox_escapamento2,checkBox_escapamento3;
    EditText txt_parachoque,txt_motor,txt_pneus,txt_piso,txt_tanque,txt_cabine,txt_tanques_ar,txt_eixos;
    EditText txt_roda,txt_exterior,txt_piso_interior,txt_portas,txt_paredes,txt_teto,txt_parede_dianteira,txt_refrigerador,txt_escapamento;
    Button btnEnviando;

    String url_Register = "http://transcr10.com/chek17pontos/cadastro_checklist.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check01);
        checkBox_parachoque = findViewById(R.id.checkBox_parachoque);
        checkBox_parachoque2 = findViewById(R.id.checkBox_parachoque2);
        checkBox_parachoque3 = findViewById(R.id.checkBox_parachoque3);
        txt_parachoque = findViewById(R.id.txt_parachoque);
        checkBox_motor = findViewById(R.id.checkBox_motor);
        checkBox_motor2 = findViewById(R.id.checkBox_motor2);
        checkBox_motor3 = findViewById(R.id.checkBox_motor3);
        txt_motor = findViewById(R.id.txt_motor);
        checkBox_pneus = findViewById(R.id.checkBox_pneus);
        checkBox_pneus2 = findViewById(R.id.checkBox_pneus2);
        checkBox_pneus3 = findViewById(R.id.checkBox_pneus3);
        txt_pneus = findViewById(R.id.txt_pneus);
        checkBox_piso = findViewById(R.id.checkBox_piso);
        checkBox_piso2 = findViewById(R.id.checkBox_piso2);
        checkBox_piso3 = findViewById(R.id.checkBox_piso3);
        txt_piso = findViewById(R.id.txt_piso);
        checkBox_tanque = findViewById(R.id.checkBox_tanque);
        checkBox_tanque2 = findViewById(R.id.checkBox_tanque2);
        checkBox_tanque3 = findViewById(R.id.checkBox_tanque3);
        txt_tanque = findViewById(R.id.txt_tanque);
        checkBox_cabine = findViewById(R.id.checkBox_cabine);
        checkBox_cabine2 = findViewById(R.id.checkBox_cabine2);
        checkBox_cabine3 = findViewById(R.id.checkBox_cabine3);
        txt_cabine = findViewById(R.id.txt_cabine);
        checkBox_tanques_ar = findViewById(R.id.checkBox_tanques_ar);
        checkBox_tanques_ar2 = findViewById(R.id.checkBox_tanques_ar2);
        checkBox_tanques_ar3 = findViewById(R.id.checkBox_tanques_ar3);
        txt_tanques_ar = findViewById(R.id.txt_tanques_ar);
        checkBox_eixos = findViewById(R.id.checkBox_eixos);
        checkBox_eixos2 = findViewById(R.id.checkBox_eixos2);
        checkBox_eixos3 = findViewById(R.id.checkBox_eixos3);
        txt_eixos = findViewById(R.id.txt_eixos);

        checkBox_roda = findViewById(R.id.checkBox_roda);
        checkBox_roda2 = findViewById(R.id.checkBox_roda2);
        checkBox_roda3 = findViewById(R.id.checkBox_roda3);
        txt_roda = findViewById(R.id.txt_roda);
        checkBox_exterior = findViewById(R.id.checkBox_exterior);
        checkBox_exterior2 = findViewById(R.id.checkBox_exterior2);
        checkBox_exterior3 = findViewById(R.id.checkBox_exterior3);
        txt_exterior = findViewById(R.id.txt_exterior);
        checkBox_piso_interior = findViewById(R.id.checkBox_piso_interior);
        checkBox_piso_interior2 = findViewById(R.id.checkBox_piso_interior2);
        checkBox_piso_interior3 = findViewById(R.id.checkBox_piso_interior3);
        txt_piso_interior = findViewById(R.id.txt_piso_interior);
        checkBox_portas = findViewById(R.id.checkBox_portas);
        checkBox_portas2 = findViewById(R.id.checkBox_portas2);
        checkBox_portas3 = findViewById(R.id.checkBox_portas3);
        txt_portas = findViewById(R.id.txt_portas);
        checkBox_paredes = findViewById(R.id.checkBox_paredes);
        checkBox_paredes2 = findViewById(R.id.checkBox_paredes2);
        checkBox_paredes3 = findViewById(R.id.checkBox_paredes3);
        txt_paredes = findViewById(R.id.txt_paredes);
        checkBox_teto = findViewById(R.id.checkBox_teto);
        checkBox_teto2 = findViewById(R.id.checkBox_teto2);
        checkBox_teto3 = findViewById(R.id.checkBox_teto3);
        txt_teto = findViewById(R.id.txt_teto);
        checkBox_parede_dianteira = findViewById(R.id.checkBox_parede_dianteira);
        checkBox_parede_dianteira2 = findViewById(R.id.checkBox_parede_dianteira2);
        checkBox_parede_dianteira3 = findViewById(R.id.checkBox_parede_dianteira3);
        txt_parede_dianteira = findViewById(R.id.txt_parede_dianteira);
        checkBox_refrigerador = findViewById(R.id.checkBox_refrigerador);
        checkBox_refrigerador2 = findViewById(R.id.checkBox_refrigerador2);
        checkBox_refrigerador3 = findViewById(R.id.checkBox_refrigerador3);
        txt_refrigerador = findViewById(R.id.txt_refrigerador);
        checkBox_escapamento = findViewById(R.id.checkBox_escapamento);
        checkBox_escapamento2 = findViewById(R.id.checkBox_escapamento2);
        checkBox_escapamento3 = findViewById(R.id.checkBox_escapamento3);
        txt_escapamento = findViewById(R.id.txt_escapamento);

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.check1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main:
                        startActivity(new Intent(getApplicationContext(),CheckList17.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.check1:
                        return true;
                    case R.id.check2:
                        startActivity(new Intent(getApplicationContext(), CheckList_Part2.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.container:
                        startActivity(new Intent(getApplicationContext(), CheckList_Container.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.container2:
                        startActivity(new Intent(getApplicationContext(), CheckList_Container2.class));
                        overridePendingTransition(0, 0);
                        return true;


                }
                return false;
            }
        });

        btnEnviando = findViewById(R.id.btn_enviar);

        btnEnviando.setOnClickListener(new View.OnClickListener() {
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

                String  checkBox_Parachoque = "",checkBox_Parachoque2 = "",checkBox_Parachoque3="",checkBox_Motor = "",checkBox_Motor2 = "",checkBox_Motor3="",
                        checkBox_Pneus = "",checkBox_Pneus2 = "",checkBox_Pneus3="",checkBox_Piso="",checkBox_Piso2="",checkBox_Piso3="",checkBox_Tanque="",checkBox_Tanque2="",
                        checkBox_Tanque3="",checkBox_Cabine="",checkBox_Cabine2="",checkBox_Cabine3="",checkBox_Tanques_ar="",checkBox_Tanques_ar2="",checkBox_Tanques_ar3="",
                        checkBox_Eixos="",checkBox_Eixos2="",checkBox_Eixos3="",checkBox_Roda="",checkBox_Roda2="",checkBox_Roda3="",checkBox_Exterior="",checkBox_Exterior2="",
                        checkBox_Exterior3="",
                        checkBox_Piso_interior="",checkBox_Piso_interior2="",checkBox_Piso_interior3="",checkBox_Portas="",checkBox_Portas2="",
                        checkBox_Portas3="",checkBox_Paredes="",checkBox_Paredes2="",checkBox_Paredes3="",checkBox_Teto="",checkBox_Teto2="",checkBox_Teto3="",
                        checkBox_Parede_dianteira="",checkBox_Parede_dianteira2="",checkBox_Parede_dianteira3="",checkBox_Refrigerador="",checkBox_Refrigerador2="",
                        checkBox_Refrigerador3="",checkBox_Escapamento="",checkBox_Escapamento2="",checkBox_Escapamento3="";

                String parachoque = null, motor = null, pneus = null, piso_cabine=null, combustivel=null, cabine = null, tanques_ar = null,eixos = null,
                quinta_roda=null, exterior = null, piso_interior = null, portas_afora = null, paredes_laterais = null, teto_interior = null, parede_dianteira=null,
                refrigerador = null,escapamento = null;

                if(checkBox_parachoque.isChecked()){

                    checkBox_Parachoque = checkBox_parachoque.getText().toString();
                    parachoque = checkBox_parachoque.getText().toString();
                }
                if(checkBox_parachoque2.isChecked()){

                    checkBox_Parachoque2 = checkBox_parachoque2.getText().toString();
                    parachoque = checkBox_parachoque2.getText().toString();
                }
                if(checkBox_parachoque2.isChecked()) {
                    checkBox_Parachoque3 = checkBox_parachoque3.getText().toString();
                    parachoque = checkBox_parachoque3.getText().toString();

                }

                String Txt_parachoque = txt_parachoque.getText().toString();
                if(checkBox_motor.isChecked()) {
                    checkBox_Motor = checkBox_motor.getText().toString();
                    motor = checkBox_motor.getText().toString();

                }
                if(checkBox_motor2.isChecked()) {
                    checkBox_Motor2 = checkBox_motor2.getText().toString();
                    motor = checkBox_motor2.getText().toString();

                }
                if(checkBox_motor3.isChecked()) {
                    checkBox_Motor3 = checkBox_motor3.getText().toString();
                    motor = checkBox_motor3.getText().toString();

                }
                String Txt_motor = txt_motor.getText().toString();
                if(checkBox_pneus.isChecked()) {
                    checkBox_Pneus = checkBox_pneus.getText().toString();
                    pneus = checkBox_pneus.getText().toString();

                }
                if(checkBox_pneus2.isChecked()) {
                    checkBox_Pneus2 = checkBox_pneus2.getText().toString();
                    pneus = checkBox_pneus2.getText().toString();
                }
                if(checkBox_pneus3.isChecked()) {
                    checkBox_Pneus3 = checkBox_pneus3.getText().toString();
                    pneus = checkBox_pneus3.getText().toString();

                }
                String Txt_pneus = txt_pneus.getText().toString();
                if(checkBox_piso.isChecked()) {
                    checkBox_Piso = checkBox_piso.getText().toString();
                    piso_cabine = checkBox_piso.getText().toString();

                }
                if(checkBox_piso2.isChecked()) {
                    checkBox_Piso2= checkBox_piso2.getText().toString();
                    piso_cabine = checkBox_piso2.getText().toString();
                }
                if(checkBox_piso3.isChecked()) {
                    checkBox_Piso3= checkBox_piso3.getText().toString();
                    piso_cabine = checkBox_piso3.getText().toString();

                }
                String Txt_piso = txt_piso.getText().toString();
                if(checkBox_tanque.isChecked()) {
                    checkBox_Tanque= checkBox_tanque.getText().toString();
                    combustivel = checkBox_tanque.getText().toString();
                }
                if(checkBox_tanque2.isChecked()) {
                    checkBox_Tanque2= checkBox_tanque2.getText().toString();
                    combustivel = checkBox_tanque2.getText().toString();

                }
                if(checkBox_tanque3.isChecked()) {
                    checkBox_Tanque3= checkBox_tanque3.getText().toString();
                    combustivel = checkBox_tanque3.getText().toString();

                }
                String Txt_tanque = txt_tanque.getText().toString();
                if(checkBox_cabine.isChecked()) {
                    checkBox_Cabine= checkBox_cabine.getText().toString();
                    cabine = checkBox_cabine.getText().toString();

                }
                if(checkBox_cabine2.isChecked()) {
                    checkBox_Cabine2 = checkBox_cabine2.getText().toString();
                    cabine = checkBox_cabine2.getText().toString();
                }
                if(checkBox_cabine3.isChecked()) {
                    checkBox_Cabine3 = checkBox_cabine3.getText().toString();
                    cabine = checkBox_cabine3.getText().toString();

                }
                String Txt_cabine = txt_cabine.getText().toString();
                if(checkBox_tanques_ar.isChecked()) {
                    checkBox_Tanques_ar = checkBox_tanques_ar.getText().toString();
                    tanques_ar = checkBox_tanques_ar.getText().toString();
                }
                if(checkBox_tanques_ar2.isChecked()) {
                    checkBox_Tanques_ar2= checkBox_tanques_ar2.getText().toString();
                    tanques_ar = checkBox_tanques_ar2.getText().toString();
                }
                if(checkBox_tanques_ar3.isChecked()) {
                    checkBox_Tanques_ar3= checkBox_tanques_ar3.getText().toString();
                    tanques_ar = checkBox_tanques_ar3.getText().toString();
                }
                String Txt_tanques_ar = txt_tanques_ar.getText().toString();
                if(checkBox_eixos.isChecked()) {
                    checkBox_Eixos = checkBox_eixos.getText().toString();
                    eixos = checkBox_eixos.getText().toString();
                }
                if(checkBox_eixos2.isChecked()) {
                    checkBox_Eixos2= checkBox_eixos2.getText().toString();
                    eixos = checkBox_eixos2.getText().toString();
                }
                if(checkBox_eixos3.isChecked()) {
                    checkBox_Eixos3= checkBox_eixos3.getText().toString();
                    eixos = checkBox_eixos3.getText().toString();
                }
                String Txt_eixos = txt_eixos.getText().toString();

                if(checkBox_roda.isChecked()) {
                    checkBox_Roda= checkBox_roda.getText().toString();
                    quinta_roda = checkBox_roda.getText().toString();
                }
                if(checkBox_roda2.isChecked()) {
                    checkBox_Roda2= checkBox_roda2.getText().toString();
                    quinta_roda = checkBox_roda2.getText().toString();
                }
                if(checkBox_roda3.isChecked()) {
                    checkBox_Roda3= checkBox_roda3.getText().toString();
                    quinta_roda = checkBox_roda3.getText().toString();
                }
                String Txt_roda = txt_roda.getText().toString();
                if(checkBox_exterior.isChecked()) {
                    checkBox_Exterior= checkBox_exterior.getText().toString();
                    exterior = checkBox_exterior.getText().toString();
                }
                if(checkBox_exterior2.isChecked()) {
                    checkBox_Exterior2= checkBox_exterior2.getText().toString();
                    exterior = checkBox_exterior2.getText().toString();
                }
                if(checkBox_exterior3.isChecked()) {
                    checkBox_Exterior3= checkBox_exterior3.getText().toString();
                    exterior = checkBox_exterior3.getText().toString();
                }
                String Txt_exterior = txt_exterior.getText().toString();
                if(checkBox_piso_interior.isChecked()) {
                    checkBox_Piso_interior= checkBox_piso_interior.getText().toString();
                    piso_interior= checkBox_piso_interior.getText().toString();
                }
                if(checkBox_piso_interior2.isChecked()) {
                    checkBox_Piso_interior2= checkBox_piso_interior2.getText().toString();
                    piso_interior= checkBox_piso_interior2.getText().toString();
                }
                if(checkBox_piso_interior3.isChecked()) {
                    checkBox_Piso_interior3= checkBox_piso_interior3.getText().toString();
                    piso_interior= checkBox_piso_interior3.getText().toString();
                }
                String Txt_piso_interior = txt_piso_interior.getText().toString();
                if(checkBox_portas.isChecked()) {
                    checkBox_Portas = checkBox_portas.getText().toString();
                    portas_afora = checkBox_portas.getText().toString();
                }
                if(checkBox_portas2.isChecked()) {
                    checkBox_Portas2= checkBox_portas2.getText().toString();
                    portas_afora = checkBox_portas2.getText().toString();
                }
                if(checkBox_portas3.isChecked()) {
                    checkBox_Portas3= checkBox_portas3.getText().toString();
                    portas_afora = checkBox_portas3.getText().toString();
                }
                String Txt_portas = txt_portas.getText().toString();
                if(checkBox_paredes.isChecked()) {
                    checkBox_Paredes= checkBox_paredes.getText().toString();
                    paredes_laterais= checkBox_paredes.getText().toString();
                }
                if(checkBox_paredes2.isChecked()) {
                    checkBox_Paredes2 = checkBox_paredes2.getText().toString();
                    paredes_laterais= checkBox_paredes2.getText().toString();
                }
                if(checkBox_paredes3.isChecked()) {
                    checkBox_Paredes3= checkBox_paredes3.getText().toString();
                    paredes_laterais= checkBox_paredes3.getText().toString();
                }
                String Txt_paredes = txt_paredes.getText().toString();
                if(checkBox_teto.isChecked()) {
                    checkBox_Teto= checkBox_teto.getText().toString();
                    teto_interior = checkBox_teto.getText().toString();
                }
                if(checkBox_teto2.isChecked()) {
                    checkBox_Teto2= checkBox_teto2.getText().toString();
                    teto_interior = checkBox_teto2.getText().toString();
                }
                if(checkBox_teto3.isChecked()) {
                    checkBox_Teto3= checkBox_teto3.getText().toString();
                    teto_interior = checkBox_teto3.getText().toString();
                }
                String Txt_teto = txt_teto.getText().toString();
                if(checkBox_parede_dianteira.isChecked()) {
                    checkBox_Parede_dianteira= checkBox_parede_dianteira.getText().toString();
                    parede_dianteira = checkBox_parede_dianteira.getText().toString();
                }
                if(checkBox_parede_dianteira2.isChecked()) {
                    checkBox_Parede_dianteira2= checkBox_parede_dianteira2.getText().toString();
                    parede_dianteira = checkBox_parede_dianteira2.getText().toString();
                }
                if(checkBox_parede_dianteira3.isChecked()) {
                    checkBox_Parede_dianteira3= checkBox_parede_dianteira3.getText().toString();
                    parede_dianteira = checkBox_parede_dianteira3.getText().toString();
                }
                String Txt_parede_dianteira = txt_parede_dianteira.getText().toString();

                if(checkBox_refrigerador.isChecked()) {
                    checkBox_Refrigerador = checkBox_refrigerador.getText().toString();
                    refrigerador = checkBox_refrigerador.getText().toString();
                }
                if(checkBox_refrigerador2.isChecked()) {
                    checkBox_Refrigerador2 = checkBox_refrigerador2.getText().toString();
                    refrigerador = checkBox_refrigerador2.getText().toString();
                }
                if(checkBox_refrigerador3.isChecked()) {
                    checkBox_Refrigerador3 = checkBox_refrigerador3.getText().toString();
                    refrigerador = checkBox_refrigerador3.getText().toString();
                }
                String Txt_refrigerador = txt_refrigerador.getText().toString();
                if(checkBox_escapamento.isChecked()) {
                    checkBox_Escapamento= checkBox_escapamento.getText().toString();
                    escapamento = checkBox_escapamento.getText().toString();
                }
                if(checkBox_escapamento2.isChecked()) {
                    checkBox_Escapamento2= checkBox_escapamento2.getText().toString();
                    escapamento = checkBox_escapamento2.getText().toString();
                }
                if(checkBox_escapamento3.isChecked()) {
                    checkBox_Escapamento3= checkBox_escapamento3.getText().toString();
                    escapamento = checkBox_escapamento3.getText().toString();
                }
                String Txt_escapamento = txt_escapamento.getText().toString();

                new Registrar_CheckList().execute(Numero_Processo, data_hora, Transportadora, Nome, Placa_Caminhao, Placa_Carreta, Numero_lacreOEA, Num_Lacre,
                            checkBox_Parachoque, checkBox_Parachoque2, checkBox_Parachoque3,
                            Txt_parachoque, checkBox_Motor, checkBox_Motor2, checkBox_Motor3, Txt_motor, checkBox_Pneus, checkBox_Pneus2, checkBox_Pneus3,
                            Txt_pneus, checkBox_Piso, checkBox_Piso2, checkBox_Piso3, Txt_piso, checkBox_Tanque, checkBox_Tanque2, checkBox_Tanque3,
                            Txt_tanque, checkBox_Cabine, checkBox_Cabine2, checkBox_Cabine3, Txt_cabine,
                            checkBox_Tanques_ar, checkBox_Tanques_ar2, checkBox_Tanques_ar3, Txt_tanques_ar,
                            checkBox_Eixos, checkBox_Eixos2, checkBox_Eixos3, Txt_eixos,
                            checkBox_Roda, checkBox_Roda2, checkBox_Roda3, Txt_roda, checkBox_Exterior, checkBox_Exterior2, checkBox_Exterior3,
                            Txt_exterior, checkBox_Piso_interior, checkBox_Piso_interior2, checkBox_Piso_interior3, Txt_piso_interior,
                            checkBox_Portas, checkBox_Portas2, checkBox_Portas3, Txt_portas, checkBox_Paredes, checkBox_Paredes2, checkBox_Paredes3,
                            Txt_paredes, checkBox_Teto, checkBox_Teto2, checkBox_Teto3, Txt_teto, checkBox_Parede_dianteira, checkBox_Parede_dianteira2,
                            checkBox_Parede_dianteira3, Txt_parede_dianteira, checkBox_Refrigerador, checkBox_Refrigerador2, checkBox_Refrigerador3,
                            Txt_refrigerador, checkBox_Escapamento, checkBox_Escapamento2, checkBox_Escapamento3, Txt_escapamento);

                Intent intent = new Intent(getApplicationContext(),CheckList17.class);
                startActivity(intent);

                Intent email = new Intent(android.content.Intent.ACTION_SEND);

                /* Fill it with Data */
                email.setType("plain/text");
                email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"informatica@transcr.com.br,operacional.vcp@transcr.com.br"});
                email.putExtra(android.content.Intent.EXTRA_SUBJECT, "CheckList Inspeção do Veículo");
                email.putExtra(android.content.Intent.EXTRA_TEXT,"Número do Processo: "+bundle.getString("ONE")+
                        ", Data / horário da Vistoria: "+bundle.getString("TWO")+", Transportadora: "+bundle.getString("THREE")+
                        ", Nome do Motorista: "+bundle.getString("FOUR")+ ", Placa do Cavalo / Caminhão: "+bundle.getString("FIVE")+
                        ", Placa da Carreta: "+bundle.getString("SIX")+ ", Número do lacre OEA: "+bundle.getString("SEVEN")+
                        ", Número do lacre do Armador: "+bundle.getString("EIGHT")+" - PONTOS DE INSPEÇÃO "+"- Cabine / Cavalo "+
                        ", Posterior parachoque/grades: "+ parachoque.toString()+", Anotação: "+ Txt_parachoque.toString()+
                        ", Motor: "+ motor.toString()+ ", Anotação: "+ Txt_motor.toString()+
                        ", Pneus/Calotas: "+ pneus.toString()+", Anotação: "+ Txt_pneus.toString()+", Piso/Cabine: "+ piso_cabine.toString()+
                        ", Anotação: "+ Txt_piso.toString()+", Tanque de Combustível: "+ combustivel.toString()+", Anotação: "+ Txt_tanque.toString()+
                        ", Cabine: "+ cabine.toString()+", Anotação: "+ Txt_cabine.toString()+", Tanques de Ar: "+ tanques_ar.toString()+
                        ", Anotação: "+ Txt_tanques_ar.toString()+", Eixos de transmissão: "+ eixos.toString()+", Anotação: "+ Txt_eixos.toString()+
                        " - Trailer / Reboque "+", Área da Quinta Roda: "+ quinta_roda.toString()+ ", Anotação: "+ Txt_roda.toString()+
                        ", Exterior / abaixo: "+ exterior.toString()+", Anotação: "+ Txt_exterior.toString()+ ", Piso interior: "+ piso_interior.toString()+
                        ", Anotação: "+ Txt_piso_interior.toString()+
                        ", Portas afora/adentro: "+ portas_afora.toString()+", Anotação: "+ Txt_portas.toString()+
                        ", Paredes laterais: "+ paredes_laterais.toString()+", Anotação: "+ Txt_paredes.toString()+
                        ", Teto interior/exterior: "+ teto_interior.toString()+", Anotação: "+ Txt_teto.toString()+
                        ", Parede dianteira: "+ parede_dianteira.toString()+", Anotação: "+ Txt_parede_dianteira.toString()+
                        ", Unidade do Refrigerador: "+ refrigerador.toString()+", Anotação: "+ Txt_refrigerador.toString()+
                        ", Escapamento: "+ escapamento.toString()+ ", Anotação: "+ Txt_escapamento.toString());

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
                Intent myIntent = new Intent(CheckList_Part1.this,
                        image_View.class);
                startActivity(myIntent);
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class Registrar_CheckList extends AsyncTask<String, Void, String> {
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

            String CheckBox_parachoque = strings[8];
            String CheckBox_parachoque2 = strings[9];
            String CheckBox_parachoque3 = strings[10];
            String Txt_parachoque = strings[11];
            String CheckBox_motor = strings[12];
            String CheckBox_motor2 = strings[13];
            String CheckBox_motor3 = strings[14];
            String Txt_motor = strings[15];
            String CheckBox_pneus = strings[16];
            String CheckBox_pneus2 = strings[17];
            String CheckBox_pneus3 = strings[18];
            String Txt_pneus = strings[19];
            String CheckBox_piso = strings[20];
            String CheckBox_piso2 = strings[21];
            String CheckBox_piso3 = strings[22];
            String Txt_piso = strings[23];
            String CheckBox_tanque = strings[24];
            String CheckBox_tanque2 = strings[25];
            String CheckBox_tanque3 = strings[26];
            String Txt_tanque = strings[27];
            String CheckBox_cabine = strings[28];
            String CheckBox_cabine2 = strings[29];
            String CheckBox_cabine3 = strings[30];
            String Txt_cabine = strings[31];
            String CheckBox_tanques_ar = strings[32];
            String CheckBox_tanques_ar2 = strings[33];
            String CheckBox_tanques_ar3 = strings[34];
            String Txt_tanques_ar = strings[35];
            String CheckBox_eixos = strings[36];
            String CheckBox_eixos2 = strings[37];
            String CheckBox_eixos3 = strings[38];
            String Txt_eixos = strings[39];

            String CheckBox_roda = strings[40];
            String CheckBox_roda2 = strings[41];
            String CheckBox_roda3 = strings[42];
            String Txt_roda = strings[43];
            String CheckBox_exterior = strings[44];
            String CheckBox_exterior2 = strings[45];
            String CheckBox_exterior3 = strings[46];
            String Txt_exterior = strings[47];
            String CheckBox_piso_interior = strings[48];
            String CheckBox_piso_interior2 = strings[49];
            String CheckBox_piso_interior3 = strings[50];
            String Txt_piso_interior = strings[51];
            String CheckBox_portas = strings[52];
            String CheckBox_portas2 = strings[53];
            String CheckBox_portas3 = strings[54];
            String Txt_portas = strings[55];
            String CheckBox_paredes = strings[56];
            String CheckBox_paredes2 = strings[57];
            String CheckBox_paredes3 = strings[58];
            String Txt_paredes = strings[59];
            String CheckBox_teto = strings[60];
            String CheckBox_teto2 = strings[61];
            String CheckBox_teto3 = strings[62];
            String Txt_teto = strings[63];
            String CheckBox_parede_dianteira = strings[64];
            String CheckBox_parede_dianteira2 = strings[65];
            String CheckBox_parede_dianteira3 = strings[66];
            String Txt_parede_dianteira = strings[67];
            String CheckBox_refrigerador = strings[68];
            String CheckBox_refrigerador2 = strings[69];
            String CheckBox_refrigerador3 = strings[70];
            String Txt_refrigerador = strings[71];
            String CheckBox_escapamento = strings[72];
            String CheckBox_escapamento2 = strings[73];
            String CheckBox_escapamento3 = strings[74];
            String Txt_escapamento = strings[75];


            String finalURL = url_Register + "?numero_embarque=" + Numero_Processo +
                    "&data_horario=" + data_hora + "&transportadora=" + Transportadora + "&nome_motorista=" + Nome +
                    "&placa_caminhao=" + Placa_Caminhao + "&placa_carreta=" + Placa_Carreta +
                    "&num_lacreOEA=" + Numero_lacreOEA+ "&num_lacre=" + Num_Lacre
                    + "&posteriorparachoques=" + CheckBox_parachoque +
                    "&posteriorparachoques2=" + CheckBox_parachoque2 +"&posteriorparachoques3=" + CheckBox_parachoque3 +
                    "&posterioranotacao=" + Txt_parachoque + "&motor_1=" + CheckBox_motor + "&motor_2=" + CheckBox_motor2 +
                    "&motor_3=" + CheckBox_motor3 + "&motoranotacao=" + Txt_motor + "&PneusCalotas=" + CheckBox_pneus+
                    "&PneusCalotas2=" + CheckBox_pneus2+ "&PneusCalotas3=" + CheckBox_pneus3 +
                    "&pneusanotacao=" + Txt_pneus + "&PisoCabine=" + CheckBox_piso + "&PisoCabine2=" + CheckBox_piso2 +
                    "&PisoCabine3=" + CheckBox_piso3 + "&pisoanotacao=" + Txt_piso + "&tanquecombustivel=" + CheckBox_tanque +
                    "&tanquecombustivel2=" + CheckBox_tanque2 + "&tanquecombustivel3=" + CheckBox_tanque3 +
                    "&tanqueanotacao=" + Txt_tanque + "&cabine_1=" + CheckBox_cabine + "&cabine_2=" + CheckBox_cabine2 +
                    "&cabine_3=" + CheckBox_cabine3 + "&cabineanotacao=" + Txt_cabine +
                    "&tanquesar=" + CheckBox_tanques_ar + "&tanquesar2=" + CheckBox_tanques_ar2 + "&tanquesar3=" +
                    CheckBox_tanques_ar3 + "&tanquesAranotacao=" + Txt_tanques_ar + "&eixostransmissao=" + CheckBox_eixos
                    + "&eixostransmissao2=" + CheckBox_eixos2 + "&eixostransmissao3=" + CheckBox_eixos3 +
                    "&eixosanotacao=" + Txt_eixos + "&areaquintaRoda=" + CheckBox_roda +
                    "&areaquintaRoda2=" + CheckBox_roda2 + "&areaquintaRoda3=" + CheckBox_roda3 + "&quintaRodaanotacao=" +
                    Txt_roda + "&exteriorabaixo=" + CheckBox_exterior + "&exteriorabaixo2=" +
                    CheckBox_exterior2 + "&exteriorabaixo3=" + CheckBox_exterior3 +
                    "&exterioranotacao=" + Txt_exterior + "&pisointerior=" + CheckBox_piso_interior +
                    "&pisointerior2=" + CheckBox_piso_interior2 + "&pisointerior3=" + CheckBox_piso_interior3 +
                    "&pisoInterioranotacao=" + Txt_piso_interior + "&portasafora_adentro=" + CheckBox_portas
                    + "&portasafora_adentro2=" + CheckBox_portas2 + "&portasafora_adentro3=" + CheckBox_portas3 +
                    "&portasanotacao=" + Txt_portas + "&paredeslaterais=" + CheckBox_paredes + "&paredeslaterais2=" +
                    CheckBox_paredes2 + "&paredeslaterais3=" + CheckBox_paredes3 +
                    "&paredesanotacao=" + Txt_paredes + "&tetointerior_exterior=" + CheckBox_teto +
                    "&tetointerior_exterior2=" + CheckBox_teto2 +
                    "&tetointerior_exterior3=" + CheckBox_teto3 + "&tetoanotacao=" + Txt_teto +
                    "&parededianteira=" + CheckBox_parede_dianteira
                    + "&parededianteira2=" + CheckBox_parede_dianteira2 + "&parededianteira3=" +
                    CheckBox_parede_dianteira3 + "&paredeDianteiraanotacao=" + Txt_parede_dianteira + "&unidaderefrigerador=" +
                    CheckBox_refrigerador + "&unidaderefrigerador2=" + CheckBox_refrigerador2
                    + "&unidaderefrigerador3=" + CheckBox_refrigerador3 + "&refrigeradoranotacao=" + Txt_refrigerador
                    + "&escapamento_1=" + CheckBox_escapamento + "&escapamento_2=" + CheckBox_escapamento2
                    + "&escapamento_3=" + CheckBox_escapamento3 + "&escapamentoanotacao=" + Txt_escapamento;

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
                Toast.makeText(CheckList_Part1.this,
                        Text, Toast.LENGTH_LONG).show();
            }
        });
    }


}
