package com.example.check17_pontosveiculo.checkList.imagem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.checkList.CheckList_Container2;
import com.example.check17_pontosveiculo.checkList.Show_Container_Check;
import com.example.check17_pontosveiculo.mRecycler.MyAdapter_Container;
import com.example.check17_pontosveiculo.mRecycler.MyAdapter_EscolherV;

import java.util.ArrayList;
import java.util.List;

public class EscolherTipoImg extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> list;
    MyAdapter_EscolherV adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escolher_veiculo);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        list.add("Caminhão");
        list.add("Sprinter");
        list.add("Carroceria");
        list.add("Cavalo");
        list.add("Carreta");

        adapter = new MyAdapter_EscolherV(this, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyAdapter_EscolherV.OnItemClickListener() {
            @Override
            public void onItemClick ( int position ) {
                Intent detailIntent = new Intent(EscolherTipoImg.this, image_View.class );
                detailIntent.putExtra("Username", list.get(position));
                startActivity( detailIntent );
            }
        });


    }
}
