package com.example.check17_pontosveiculo.checkList.imagem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.check17_pontosveiculo.R;


public class image_View extends AppCompatActivity {
    private static final String EXTRA = "Username" ;
    TextView _TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_imagem);

        _TextView = findViewById(R.id.textView);
        Intent mIntent = getIntent();
        if (mIntent != null) {
            String placa = mIntent.getStringExtra(EXTRA);
            _TextView.setText( placa );
        }

       if ((_TextView.getText().toString().equals("Caminhão"))){
           ImageView myCheckImage = findViewById(R.id.my_imageView);
           myCheckImage.setImageResource(R.drawable.check_image);
       }
        if ((_TextView.getText().toString().equals("Sprinter"))){
            ImageView myCheckImage = findViewById(R.id.my_imageView);
            myCheckImage.setImageResource(R.drawable.sprinter_bau);
        }
       if ((_TextView.getText().toString().equals("Carroceria"))){
            ImageView myCheckImage = findViewById(R.id.my_imageView);
            myCheckImage.setImageResource(R.drawable.carroceria);
        }
        if ((_TextView.getText().toString().equals("Cavalo"))){
            ImageView myCheckImage = findViewById(R.id.my_imageView);
            myCheckImage.setImageResource(R.drawable.cavalo_mercedes);
        }
        if ((_TextView.getText().toString().equals("Carreta"))){
            ImageView myCheckImage = findViewById(R.id.my_imageView);
            myCheckImage.setImageResource(R.drawable.carreta);
        }




    }
}
