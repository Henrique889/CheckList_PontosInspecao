package com.example.check17_pontosveiculo.checkList.imagem;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.check17_pontosveiculo.R;

public class imageView_Container extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_imagem_container);

        ImageView myImage = findViewById(R.id.mImage_container);
        myImage.setImageResource(R.drawable.ch_container);

    }
}
