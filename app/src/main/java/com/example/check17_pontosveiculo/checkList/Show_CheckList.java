package com.example.check17_pontosveiculo.checkList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.check17_pontosveiculo.MySingleton.MySingleton;
import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.mDataObject.Registros_Checks;
import com.example.check17_pontosveiculo.mRecycler.CheckAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Show_CheckList extends AppCompatActivity{
    private static final String EXTRA_Num = "Numero_Embarque" ;

    RecyclerView recyclerView;
    private List<Registros_Checks> list;
    StringRequest stringRequest;
    ProgressDialog progressDialog;

    TextView numero_embarque, placa_caminhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_show);

        numero_embarque = findViewById(R.id.id_numero_embarque);
        placa_caminhao = findViewById (R.id.txt_PLACA);

        Intent i = getIntent();

        if (i != null) {
            String s = i.getStringExtra(EXTRA_Num);
            numero_embarque.setText( s );
        }

        recyclerView = (RecyclerView)findViewById(R.id.id_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadRecyclerView();
    }

    private void loadRecyclerView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Buscando...");
        progressDialog.show();

        String url = "http://transcr10.com/chek17pontos/MostrarDados.php?Numero_Embarque=" + numero_embarque.getText().toString();
        url = url.replace( " ", "%20" );

        stringRequest = new StringRequest( Request.Method.GET, url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {

                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject spacecraft = array.getJSONObject(i);

                                list.add(new Registros_Checks(
                                        spacecraft.getString("Numero_Embarque"),
                                        spacecraft.getString("Data_Horario"),
                                        spacecraft.getString("Transportadora"),
                                        spacecraft.getString("Nome_Motorista"),
                                        spacecraft.getString("Placa_Caminhao"),
                                        spacecraft.getString("Placa_Carreta"),
                                        spacecraft.getString("Num_LacreOEA"),
                                        spacecraft.getString("Num_Lacre"),
                                        spacecraft.getString("posterior_parachoques"),
                                        spacecraft.getString("posterior_parachoques3"),
                                        spacecraft.getString("posterior_anotacao"),
                                        spacecraft.getString("motor"),
                                        spacecraft.getString("motor3"),
                                        spacecraft.getString("motor_anotacao"),
                                        spacecraft.getString("Pneus_Calotas"),
                                        spacecraft.getString("Pneus_Calotas3"),
                                        spacecraft.getString("pneus_anotacao"),
                                        spacecraft.getString("Piso_Cabine"),
                                        spacecraft.getString("Piso_Cabine3"),
                                        spacecraft.getString("piso_anotacao"),
                                        spacecraft.getString("tanque_combustivel"),
                                        spacecraft.getString("tanque_combustivel3"),
                                        spacecraft.getString("tanque_anotacao"),
                                        spacecraft.getString("cabine"),
                                        spacecraft.getString("cabine3"),
                                        spacecraft.getString("cabine_anotacao"),
                                        spacecraft.getString("tanques_ar"),
                                        spacecraft.getString("tanques_ar3"),
                                        spacecraft.getString("tanquesAr_anotacao"),
                                        spacecraft.getString("eixos_transmissao"),
                                        spacecraft.getString("eixos_transmissao3"),
                                        spacecraft.getString("eixos_anotacao"),
                                        spacecraft.getString("area_quintaRoda"),
                                        spacecraft.getString("area_quintaRoda3"),
                                        spacecraft.getString("quintaRoda_anotacao"),
                                        spacecraft.getString("exterior_abaixo"),
                                        spacecraft.getString("exterior_abaixo3"),
                                        spacecraft.getString("exterior_anotacao"),
                                        spacecraft.getString("piso_interior"),
                                        spacecraft.getString("piso_interior3"),
                                        spacecraft.getString("pisoInterior_anotacao"),
                                        spacecraft.getString("portas_afora_adentro"),
                                        spacecraft.getString("portas_afora_adentro3"),
                                        spacecraft.getString("portas_anotacao"),
                                        spacecraft.getString("paredes_laterais"),
                                        spacecraft.getString("paredes_laterais3"),
                                        spacecraft.getString("paredes_anotacao"),
                                        spacecraft.getString("teto_interior_exterior"),
                                        spacecraft.getString("teto_interior_exterior3"),
                                        spacecraft.getString("teto_anotacao"),
                                        spacecraft.getString("parede_dianteira"),
                                        spacecraft.getString("parede_dianteira3"),
                                        spacecraft.getString("paredeDianteira_anotacao"),
                                        spacecraft.getString("unidade_refrigerador"),
                                        spacecraft.getString("unidade_refrigerador3"),
                                        spacecraft.getString("refrigerador_anotacao"),
                                        spacecraft.getString("escapamento"),
                                        spacecraft.getString("escapamento3"),
                                        spacecraft.getString("escapamento_anotacao")
                                ));
                            }
                            CheckAdapter adapter = new CheckAdapter(Show_CheckList.this, list);
                            recyclerView.setAdapter(adapter);

                        }catch (JSONException e){
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Não foi possível mostrar as informações do cadastro " + response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Erro ao Carregar as Informações", Toast.LENGTH_SHORT).show();
                        Log.i( "RESPOSTA: ", "" + error );
                    }
                });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
