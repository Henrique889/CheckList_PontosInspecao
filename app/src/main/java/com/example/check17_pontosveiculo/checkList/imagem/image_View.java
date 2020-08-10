package com.example.check17_pontosveiculo.checkList.imagem;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.check17_pontosveiculo.R;


public class image_View extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_imagem);

        ImageView myCheckImage = findViewById(R.id.my_imageView);
        myCheckImage.setImageResource(R.drawable.check_image);

    }
}
