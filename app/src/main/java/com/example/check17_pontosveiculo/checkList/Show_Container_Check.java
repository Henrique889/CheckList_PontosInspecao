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
import com.example.check17_pontosveiculo.mDataObject.Registros_Containers;
import com.example.check17_pontosveiculo.mRecycler.CheckAdapter_Container;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Show_Container_Check extends AppCompatActivity {
    private static final String EXTRA_1 = "Numero_P_Embarque" ;

    RecyclerView recyclerView;
    private List<Registros_Containers> list;
    StringRequest stringRequest;
    ProgressDialog progressDialog;

    TextView numero_processo_embarque, placa_carreta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_containers_show);

        numero_processo_embarque = findViewById(R.id.id_numero_emb);
        placa_carreta = findViewById (R.id.txt_Carreta);

        Intent inte = getIntent();

        if (inte != null) {
            String string = getIntent().getStringExtra(EXTRA_1);
            numero_processo_embarque.setText( string );
        }

        recyclerView = (RecyclerView)findViewById(R.id.id_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        loadRecyclerView();
    }

    private void loadRecyclerView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Buscando...");
        progressDialog.show();

        String url = "http://transcr10.com/chek17pontos/MostrarDados_Containers.php?Numero_P_Embarque=" + numero_processo_embarque.getText().toString();
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

                        list.add(new Registros_Containers(
                                spacecraft.getString("Numero_P_Embarque"),
                                spacecraft.getString("Data_Horario"),
                                spacecraft.getString("Transportadora"),
                                spacecraft.getString("Nome_Motorista"),
                                spacecraft.getString("Placa_Caminhao"),
                                spacecraft.getString("Placa_Carreta"),
                                spacecraft.getString("Num_LacreOEA"),
                                spacecraft.getString("Num_Lacre"),
                                spacecraft.getString("secao_inferior_externa"),
                                spacecraft.getString("secao_inferior_externa3"),
                                spacecraft.getString("txt_secao_inferior_externa"),
                                spacecraft.getString("portas_interiores_exteriores"),
                                spacecraft.getString("portas_interiores_exteriores3"),
                                spacecraft.getString("txt_portas_interiores_exteriores"),
                                spacecraft.getString("ladoDireito"),
                                spacecraft.getString("ladoDireito3"),
                                spacecraft.getString("txt_ladoDireito"),
                                spacecraft.getString("ladoEsquerdo"),
                                spacecraft.getString("ladoEsquerdo3"),
                                spacecraft.getString("txt_ladoEsquerdo"),
                                spacecraft.getString("parede_Dianteira"),
                                spacecraft.getString("parede_Dianteira3"),
                                spacecraft.getString("txt_parede_Dianteira"),
                                spacecraft.getString("teto_interior_exterior"),
                                spacecraft.getString("teto_interior_exterior3"),
                                spacecraft.getString("txt_teto_interior_exterior"),
                                spacecraft.getString("piso"),
                                spacecraft.getString("piso3"),
                                spacecraft.getString("txt_piso")
                        ));
                    }
                    CheckAdapter_Container adapter = new CheckAdapter_Container(Show_Container_Check.this, list);
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
