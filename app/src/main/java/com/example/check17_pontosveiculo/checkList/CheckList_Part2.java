package com.example.check17_pontosveiculo.checkList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.mDataObject.Spacecraft;
import com.example.check17_pontosveiculo.mRecycler.MyAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CheckList_Part2 extends AppCompatActivity{
    RecyclerView recyclerView;
    private List<Spacecraft> listItems;
    JsonObjectRequest jsonObjectReq;
    ProgressDialog progressDialog;
    private static final String EXTRA_Num = "Numero_Embarque" ;
    private static final String urlAddress = "http://transcr10.com/chek17pontos/consultar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall1);

        recyclerView = (RecyclerView)findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();
        loadRecyclerView();

        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.check2);
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

    }

    private void loadRecyclerView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();

        //jsonObjectReq = new JsonObjectRequest(Request.Method.GET, urlAddress, null, this, this);
        //MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlAddress,
                new Response.Listener<String>() {
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

                                listItems.add(new Spacecraft(
                                        spacecraft.getString("Nome_Motorista"),
                                        spacecraft.getString("Data_Horario"),
                                        spacecraft.getString("Placa_Caminhao"),
                                        spacecraft.getString("Numero_Embarque"),
                                        spacecraft.getString("Num_LacreOEA")
                                ));
                            }
                        MyAdapter adapter = new MyAdapter(CheckList_Part2.this, listItems);
                        recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener( new MyAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick ( int position ) {
                                    Intent detailIntent = new Intent(CheckList_Part2.this, Show_CheckList.class );
                                    detailIntent.putExtra( EXTRA_Num,listItems.get( position ).getNumero().toString()  );
                                    startActivity( detailIntent );
                                }
                            } );

                    }catch (JSONException e){
                        e.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Não foi possível listar os registros " + response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Erro ao Carregar", Toast.LENGTH_SHORT).show();
                        Log.i( "RESPOSTA: ", "" + error );
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }

}

