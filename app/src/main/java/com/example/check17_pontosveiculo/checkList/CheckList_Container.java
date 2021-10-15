package com.example.check17_pontosveiculo.checkList;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.check17_pontosveiculo.MySingleton.MySingleton;
import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.checkList.imagem.imageView_Container;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckList_Container extends AppCompatActivity {
    CheckBox checkBox_secao,checkBox_secao2,checkBox_secao3,checkBox_portas_interiores,checkBox_portas_interiores2,
            checkBox_portas_interiores3,checkBox_ladodireito,checkBox_ladodireito2,checkBox_ladodireito3,
            checkBox_ladoesquerdo,checkBox_ladoesquerdo2,checkBox_ladoesquerdo3,checkBoxParede_Dianteira,
            checkBoxParede_Dianteira2,checkBoxParede_Dianteira3,checkBox_Teto,checkBox_Teto2,checkBox_Teto3,
            checkBox_piso,checkBox_piso2,checkBox_piso3;
    EditText txt_secao,txt_portas_interiores,txt_ladodireito,txt_ladoesquerdo,txtparede_Dianteira,txt_Teto,txtPiso;
    ImageView imgFoto;
    Button btn_enviar, btnPhoto;

    private static final int COD_PERMISSAO = 100;
    private static final int COD_SELECIONA = 10;
    private static final int COD_FOTO = 20;

    private static final String PASTA_PRINCIPAL = "minhasImagensApp/";  //dir principal
    private static final String PASTA_IMAGEM = "imagens";  //PASTA ONDE FICARAM AS FOTOS
    private static final String DIRETORIO_IMAGEM = PASTA_PRINCIPAL + PASTA_IMAGEM;

    StringRequest stringRequest;
    private String path;
    File fileImagem;
    Bitmap bitmap;

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

        imgFoto = findViewById(R.id.imgFoto);
        btn_enviar = findViewById(R.id.btn_Envio);
        btnPhoto = findViewById(R.id.btn_foto);
        if(solicitarPermissoesVersoesSuperiores()){
            btnPhoto.setEnabled(true);
            btn_enviar.setEnabled(true);
        }else{
            btnPhoto.setEnabled(false);
            btn_enviar.setEnabled(false);
        }
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarImagem();
                btn_enviar.setEnabled(true);
            }
        });

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarWebService();
            }
        });
    }
    void SendEmail(String secao, String portas, String ladoDireito, String ladoEsquerdo, String parede, String teto, String piso){
        Intent email = new Intent(android.content.Intent.ACTION_SEND);
        Bundle bundle = getIntent().getExtras();
        Uri bmpUri = getLocalBitmapUri(imgFoto);

        /* Fill it with Data */
        email.setType("plain/text");
        email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"informatica@transcr.com.br,operacional.vcp@transcr.com.br"});
        email.putExtra(android.content.Intent.EXTRA_SUBJECT, "CheckList Inspeção do Container");
        email.putExtra(android.content.Intent.EXTRA_TEXT,"Número do Processo: "+bundle.getString("ONE")+
                ", Data / horário da Vistoria: "+bundle.getString("TWO")+", Transportadora: "+bundle.getString("THREE")+
                ", Nome do Motorista: "+bundle.getString("FOUR")+ ", Placa do Cavalo / Caminhão: "+bundle.getString("FIVE")+
                ", Placa da Carreta: "+bundle.getString("SIX")+ ", Número do lacre OEA: "+bundle.getString("SEVEN")+
                ", Número do lacre do Armador: "+bundle.getString("EIGHT")+" - PONTOS DE INSPEÇÃO "+
                ", Seção inferior externa: "+ secao.toString()+", Anotação: "+ txt_secao.getText().toString()+
                ", Portas interiores/exteriores: "+ portas.toString()+ ", Anotação: "+ txt_portas_interiores.getText().toString()+
                ", Lado direito: "+ ladoDireito.toString()+", Anotação: "+ txt_ladodireito.getText().toString()+", Lado esquerdo: "+ ladoEsquerdo.toString()+
                ", Anotação: "+ txt_ladoesquerdo.getText().toString()+", Parede dianteira: "+ parede.toString()+", Anotação: "+ txtparede_Dianteira.getText().toString()+
                ", Teto interior/exterior: "+ teto.toString()+", Anotação: "+ txt_Teto.getText().toString()+", Piso: "+ piso.toString()+
                ", Anotação: "+ txtPiso.getText().toString() + "\nA imagem está em anexo...");

        email.putExtra(Intent.EXTRA_STREAM, bmpUri);
        email.setType("image/*");
        /* Send it off to the Activity-Chooser */
        startActivity(Intent.createChooser(email, "Enviando E-mail..."));

    }
    // Returns the URI path to the Bitmap displayed in specified ImageView
    public Uri getLocalBitmapUri(ImageView imageView) {
        Long consecultivo = System.currentTimeMillis()/1000;
        String nome = consecultivo.toString()+".jpg";
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            // Use methods on Context to access package-specific directories on external storage.
            // This way, you don't need to request external read/write permission.
            // See https://youtu.be/5xVh-7ywKpE?t=25m25s
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + DIRETORIO_IMAGEM + File.separator + nome);
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            // **Warning:** This will fail for API >= 24, use a FileProvider as shown below instead.
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    private void carregarImagem() {
        final CharSequence[] option = {"Tirar Foto", "Selecionar da Galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder( CheckList_Container.this );
        builder.setTitle( "Escolha uma opção" );
        builder.setItems( option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick ( DialogInterface dialogInterface, int which ) {
                if (option[which].equals( "Tirar Foto" )) {
                    //chamar method
                    abrirCamera();
                } else {
                    if (option[which].equals( "Selecionar da Galeria" )) {
                        //To call function
                        Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                        intent.setType( "image/" );
                        startActivityForResult( Intent.createChooser( intent, "Selecione" ), COD_SELECIONA );
                    } else {
                        dialogInterface.dismiss();
                    }

                }

            }
        } );
        builder.show();
    }

    private void abrirCamera () {
        File meuFile = new File( Environment.getExternalStorageDirectory(), DIRETORIO_IMAGEM );
        boolean estaCriada = meuFile.exists();

        if (estaCriada == false) {

            estaCriada = meuFile.mkdirs();
        }

        if (estaCriada == true) {

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            Long consecultivo = System.currentTimeMillis() / 1000;
            String nome = timeStamp.toString() + ".jpg";

            //caminho completo da imagem
            path = Environment.getExternalStorageDirectory() + File.separator + DIRETORIO_IMAGEM + File.separator + nome;  //caminho completo da imagem
            fileImagem = new File(path);
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagem));
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                String authorities= CheckList_Container.this.getPackageName()+".provider";
                Uri imageUri= FileProvider.getUriForFile(CheckList_Container.this,authorities,fileImagem);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            }else
            {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagem));
            }
            startActivityForResult(intent,COD_FOTO);

        }
    }

    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );
        switch (requestCode) {
            case COD_SELECIONA:
                Uri cargaOEA = data.getData();
                imgFoto.setImageURI(cargaOEA);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap( CheckList_Container.this.getContentResolver(), cargaOEA );
                    imgFoto.setImageBitmap( bitmap );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile( CheckList_Container.this, new String[]{path}, null,
                        new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted ( String path, Uri uri ) {
                                Log.i( "Path", "" + path );
                            }
                        } );
                bitmap = BitmapFactory.decodeFile( path );
                imgFoto.setImageBitmap( bitmap );
                break;
        }
        bitmap=redimensionarImagem(bitmap,580,580);
    }
    private Bitmap redimensionarImagem(Bitmap bitmap, float larguraNova, float alturaNova) {

        int largura=bitmap.getWidth();
        int altura=bitmap.getHeight();

        if(largura>larguraNova || altura>alturaNova){
            float escalaLargura=larguraNova/largura;
            float escalaAltura= alturaNova/altura;

            Matrix matrix=new Matrix();
            matrix.postScale(escalaLargura,escalaAltura);

            return Bitmap.createBitmap(bitmap,0,0,largura,altura,matrix,false);

        }else{
            return bitmap;
        }
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
    private void carregarWebService() {
        String ip = getString( R.string.ip);
        String url = ip+"/chek17pontos/cadastro_containers.php?";

        stringRequest = new StringRequest( Request.Method.POST, url, new Response.Listener <String>() {
            @Override
            public void onResponse ( String response ) {
                if (response.trim().equalsIgnoreCase( "Registrado" )) {
                    showToast( "Registro com sucesso");
                } else {
                    showToast("Registro não inserido" );
                    Log.i( "RESPOSTA: ", "" + response );
                }
            }
        }, new com.android.volley.Response.ErrorListener(){
            @Override
            public void onErrorResponse ( VolleyError error ) {
                showToast( "Não foi possível enviar os dados");
                Log.i("ERROR", error.toString());
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String  checkBox_Secao = "",checkBox_Secao2 = "",checkBox_Secao3 = "",checkBox_Portas_interiores="",
                        checkBox_Portas_interiores2="",checkBox_Portas_interiores3="", checkBox_Ladodireito = "",
                        checkBox_Ladodireito2= "",checkBox_Ladodireito3= "",checkBox_Ladoesquerdo="",
                        checkBox_Ladoesquerdo2="",checkBox_Ladoesquerdo3="", checkBox_parede_dianteira = "",
                        checkBox_parede_dianteira2 = "",checkBox_parede_dianteira3="",
                        checkBox_teto = "", checkBox_teto2="",checkBox_teto3="",
                        checkBoxPiso = "",checkBoxPiso2="",checkBoxPiso3="";

                String secao = null,portas= null,ladoDireito= null,ladoEsquerdo= null,
                        parede=null,teto = null,piso=null;

                Bundle bundle = getIntent().getExtras();

                String Numero_Processo = bundle.getString("ONE");
                String data_hora = bundle.getString("TWO");
                String Transportadora = bundle.getString("THREE");
                String Nome = bundle.getString("FOUR");
                String Placa_Caminhao = bundle.getString("FIVE");
                String Placa_Carreta = bundle.getString("SIX");
                String Numero_lacreOEA = bundle.getString("SEVEN");
                String Num_Lacre = bundle.getString("EIGHT");

                Map<String, String> parametros = new HashMap<>();
                parametros.put("Numero_Embarque", Numero_Processo);
                parametros.put("Data_Horario", data_hora);
                parametros.put("Transportadora", Transportadora);
                parametros.put("Nome_Motorista", Nome);
                parametros.put("Placa_Caminhao", Placa_Caminhao);
                parametros.put("Placa_Carreta", Placa_Carreta);
                parametros.put("Num_LacreOEA", Numero_lacreOEA);
                parametros.put("Num_Lacre", Num_Lacre);


                if(checkBox_secao.isChecked()) {

                    checkBox_Secao = checkBox_secao.getText().toString();
                    secao = checkBox_secao.getText().toString();
                    parametros.put("secao_inferior_externa", checkBox_Secao);
                }

                if(checkBox_secao2.isChecked()) {
                    checkBox_Secao2 = checkBox_secao2.getText().toString();
                    secao = checkBox_secao2.getText().toString();
                    parametros.put("secao_inferior_externa2", checkBox_Secao2);
                }
                if(checkBox_secao3.isChecked()) {
                    checkBox_Secao3 = checkBox_secao3.getText().toString();
                    secao = checkBox_secao3.getText().toString();
                    parametros.put("secao_inferior_externa3", checkBox_Secao3);
                }

                String txt_Secao = txt_secao.getText().toString();
                parametros.put("txt_secao_inferior_externa", txt_Secao);

                if(checkBox_portas_interiores.isChecked()){

                    checkBox_Portas_interiores = checkBox_portas_interiores.getText().toString();
                    portas = checkBox_portas_interiores.getText().toString();
                    parametros.put("portas_interiores_exteriores", checkBox_Portas_interiores);
                }
                if(checkBox_portas_interiores2.isChecked()){

                    checkBox_Portas_interiores2 = checkBox_portas_interiores2.getText().toString();
                    portas = checkBox_portas_interiores2.getText().toString();
                    parametros.put("portas_interiores_exteriores2", checkBox_Portas_interiores2);
                }
                if(checkBox_portas_interiores3.isChecked()) {
                    checkBox_Portas_interiores3 = checkBox_portas_interiores3.getText().toString();
                    portas = checkBox_portas_interiores3.getText().toString();
                    parametros.put("portas_interiores_exteriores3", checkBox_Portas_interiores3);

                }

                String txt_Portas_interiores = txt_portas_interiores.getText().toString();
                parametros.put("txt_portas_interiores_exteriores", txt_Portas_interiores);

                if(checkBox_ladodireito.isChecked()){

                    checkBox_Ladodireito = checkBox_ladodireito.getText().toString();
                    ladoDireito = checkBox_ladodireito.getText().toString();
                    parametros.put("ladoDireito", checkBox_Ladodireito);
                }
                if(checkBox_ladodireito2.isChecked()){

                    checkBox_Ladodireito2 = checkBox_ladodireito2.getText().toString();
                    ladoDireito = checkBox_ladodireito2.getText().toString();
                    parametros.put("ladoDireito2", checkBox_Ladodireito2);
                }
                if(checkBox_ladodireito3.isChecked()) {
                    checkBox_Ladodireito3 = checkBox_ladodireito3.getText().toString();
                    ladoDireito = checkBox_ladodireito3.getText().toString();
                    parametros.put("ladoDireito3", checkBox_Ladodireito3);

                }

                String txt_Ladodireito = txt_ladodireito.getText().toString();
                parametros.put("txt_ladoDireito", txt_Ladodireito);

                if(checkBox_ladoesquerdo.isChecked()){

                    checkBox_Ladoesquerdo = checkBox_ladoesquerdo.getText().toString();
                    ladoEsquerdo = checkBox_ladoesquerdo.getText().toString();
                    parametros.put("ladoEsquerdo", checkBox_Ladoesquerdo);
                }
                if(checkBox_ladoesquerdo2.isChecked()){

                    checkBox_Ladoesquerdo2 = checkBox_ladoesquerdo2.getText().toString();
                    ladoEsquerdo = checkBox_ladoesquerdo2.getText().toString();
                    parametros.put("ladoEsquerdo2", checkBox_Ladoesquerdo2);
                }
                if(checkBox_ladoesquerdo3.isChecked()) {
                    checkBox_Ladoesquerdo3 = checkBox_ladoesquerdo3.getText().toString();
                    ladoEsquerdo = checkBox_ladoesquerdo3.getText().toString();
                    parametros.put("ladoEsquerdo3", checkBox_Ladoesquerdo3);

                }

                String txt_Ladoesquerdo = txt_ladoesquerdo.getText().toString();
                parametros.put("txt_ladoEsquerdo", txt_Ladoesquerdo);

                if (checkBoxParede_Dianteira.isChecked()) {
                    checkBox_parede_dianteira = checkBoxParede_Dianteira.getText().toString();
                    parede = checkBoxParede_Dianteira.getText().toString();
                    parametros.put("parede_Dianteira", checkBox_parede_dianteira);
                }

                if (checkBoxParede_Dianteira2.isChecked()) {
                    checkBox_parede_dianteira2 = checkBoxParede_Dianteira2.getText().toString();
                    parede = checkBoxParede_Dianteira2.getText().toString();
                    parametros.put("parede_Dianteira2", checkBox_parede_dianteira2);
                }

                if (checkBoxParede_Dianteira3.isChecked()) {
                    checkBox_parede_dianteira3 = checkBoxParede_Dianteira3.getText().toString();
                    parede = checkBoxParede_Dianteira3.getText().toString();
                    parametros.put("parede_Dianteira3", checkBox_parede_dianteira3);

                }
                String  txtParededianteira = txtparede_Dianteira.getText().toString();
                parametros.put("txt_parede_Dianteira", txtParededianteira);


                if(checkBox_Teto.isChecked()){
                    checkBox_teto = checkBox_Teto.getText().toString();
                    teto = checkBox_Teto.getText().toString();
                    parametros.put("teto_interior_exterior", checkBox_teto);

                }
                if(checkBox_Teto2.isChecked()){
                    checkBox_teto2 = checkBox_Teto2.getText().toString();
                    teto = checkBox_Teto2.getText().toString();
                    parametros.put("teto_interior_exterior2", checkBox_teto2);
                }
                if(checkBox_Teto3.isChecked()) {
                    checkBox_teto3 = checkBox_Teto3.getText().toString();
                    teto = checkBox_Teto3.getText().toString();
                    parametros.put("teto_interior_exterior3", checkBox_teto3);
                }

                String txt_teto = txt_Teto.getText().toString();
                parametros.put("txt_teto_interior_exterior", txt_teto);

                if(checkBox_piso.isChecked()){
                    checkBoxPiso = checkBox_piso.getText().toString();
                    piso = checkBox_piso.getText().toString();
                    parametros.put("piso", checkBoxPiso);
                }
                if(checkBox_piso2.isChecked()){
                    checkBoxPiso2 = checkBox_piso2.getText().toString();
                    piso = checkBox_piso2.getText().toString();
                    parametros.put("piso2", checkBoxPiso2);
                }
                if(checkBox_piso3.isChecked()) {
                    checkBoxPiso3 = checkBox_piso3.getText().toString();
                    piso = checkBox_piso3.getText().toString();
                    parametros.put("piso3", checkBoxPiso3);

                }

                String txt_piso = txtPiso.getText().toString();
                parametros.put("txt_piso", txt_piso);
                String combustivel_imagem;
                if (bitmap != null){
                    combustivel_imagem = converterImgString(bitmap);
                }else{
                    combustivel_imagem = "";
                }

                parametros.put("imagem", combustivel_imagem);
                SendEmail(secao, portas, ladoDireito, ladoEsquerdo, parede, teto, piso);

                return parametros;

            }

        };

        MySingleton.getInstance(CheckList_Container.this).addToRequestQueue(stringRequest);


    }



    public static  String converterImgString(Bitmap bitmap) {

        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, array );
        byte [] imageByte = array.toByteArray();
        String imgS = Base64.encodeToString(imageByte,Base64.DEFAULT);
        return imgS;
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

    //solicitação de permissões

    private boolean solicitarPermissoesVersoesSuperiores() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            //validar se estamos em versão de android menor que 6 para solicitar permissoes
            return true;
        }

        //ver se as permissões foram aceitas
        if((CheckList_Container.this.checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&(CheckList_Container.this.checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }


        if ((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)||(shouldShowRequestPermissionRationale(CAMERA)))){
            carregarDialogoRecomendacao();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, COD_PERMISSAO);
        }

        return false;//processa o evento dependendo do que se defina aqui
    }

    @Override
    public void onRequestPermissionsResult( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==COD_PERMISSAO){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){//REPRESENTA DUAS PERMISSOES
                Toast.makeText(CheckList_Container.this,"Permissões Aceitas",Toast.LENGTH_SHORT);
                btnPhoto.setEnabled(true);
            }
        }else{
            solicitarPermissoesManual();
        }
    }


    private void solicitarPermissoesManual() {
        final CharSequence[] opciones={"sim","não"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(CheckList_Container.this);
        alertOpciones.setTitle("Deseja configurar as permissões manualmente?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("sim")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",CheckList_Container.this.getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(CheckList_Container.this,"Permissões Aceitas",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void carregarDialogoRecomendacao() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(CheckList_Container.this);
        dialogo.setTitle("Permissões Desativadas");
        dialogo.setMessage("Deve aceitar as permissões para funcionamento completo do App");

        dialogo.setPositiveButton("Aceitar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
            }
        });
        dialogo.show();
    }


}
