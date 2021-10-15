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
import com.example.check17_pontosveiculo.checkList.imagem.EscolherTipoImg;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CheckList_Part1 extends AppCompatActivity{
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
    ImageView imgFoto;
    EditText txt_roda,txt_exterior,txt_piso_interior,txt_portas,txt_paredes,txt_teto,txt_parede_dianteira,txt_refrigerador,txt_escapamento;
    Button btnEnviando, btnFoto;
    StringRequest stringRequest;

    private static final int COD_PERMISSAO = 100;
    private static final int COD_SELECIONA = 10;
    private static final int COD_FOTO = 20;

    private static final String PASTA_PRINCIPAL = "minhasImagensApp/";  //dir principal
    private static final String PASTA_IMAGEM = "imagens";  //PASTA ONDE FICARAM AS FOTOS
    private static final String DIRETORIO_IMAGEM = PASTA_PRINCIPAL + PASTA_IMAGEM;
    Intent email;
    private String path;
    File fileImagem;
    Bitmap bitmap;

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

        imgFoto = findViewById(R.id.imgFoto);
        btnEnviando = findViewById(R.id.btn_enviar);
        btnFoto = findViewById(R.id.btn_foto);
        if(solicitarPermissoesVersoesSuperiores()){
            btnFoto.setEnabled(true);
            btnEnviando.setEnabled(true);
        }else{
            btnFoto.setEnabled(false);
            btnEnviando.setEnabled(false);
        }
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarImagem();
                btnEnviando.setEnabled(true);
            }
        });

        btnEnviando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carregarWebService();

                Intent intent = new Intent(CheckList_Part1.this,CheckList17.class);
                startActivity(intent);

            }
        });
    }

    void sendEmail(String parachoque, String motor, String pneus, String piso_cabine, String combustivel, String cabine, String tanques_ar, String
                   eixos, String quinta_roda, String exterior, String piso_interior, String portas_afora, String paredes_laterais, String teto_interior,
                   String parede_dianteira, String refrigerador, String escapamento){
        Intent email = new Intent(android.content.Intent.ACTION_SEND);
        Bundle bundle = getIntent().getExtras();
        Uri bmpUri = getLocalBitmapUri(imgFoto);
        // Fill it with Data
        email.setType("plain/text");
        email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"informatica@transcr.com.br,operacional.vcp@transcr.com.br"});
        email.putExtra(android.content.Intent.EXTRA_SUBJECT, "CheckList Inspeção do Veículo");
        email.putExtra(android.content.Intent.EXTRA_TEXT, "Número do Processo: " + bundle.getString("ONE") +
                ", Data / horário da Vistoria: " + bundle.getString("TWO") + ", Transportadora: " + bundle.getString("THREE") +
                ", Nome do Motorista: " + bundle.getString("FOUR") + ", Placa do Cavalo / Caminhão: " + bundle.getString("FIVE") +
                ", Placa da Carreta: " + bundle.getString("SIX") + ", Número do lacre OEA: " + bundle.getString("SEVEN") +
                ", Número do lacre do Armador: " + bundle.getString("EIGHT") + " - PONTOS DE INSPEÇÃO " + "- Cabine / Cavalo " +
                ", Posterior parachoque/grades: " + parachoque.toString() + ", Anotação: " + txt_parachoque.getText().toString() +
                ", Motor: " + motor.toString() + ", Anotação: " + txt_motor.getText().toString() +
                ", Pneus/Calotas: " + pneus.toString() + ", Anotação: " + txt_pneus.getText().toString() + ", Piso/Cabine: " + piso_cabine.toString() +
                ", Anotação: " + txt_piso.getText().toString() + ", Tanque de Combustível: " + combustivel.toString() + ", Anotação: " + txt_tanque.getText().toString() +
                ", Cabine: " + cabine.toString() + ", Anotação: " + txt_cabine.getText().toString() + ", Tanques de Ar: " + tanques_ar.toString() +
                ", Anotação: " + txt_tanques_ar.getText().toString() + ", Eixos de transmissão: " + eixos.toString() + ", Anotação: " + txt_eixos.getText().toString() +
                " - Trailer / Reboque " + ", Área da Quinta Roda: " + quinta_roda.toString() + ", Anotação: " + txt_roda.getText().toString() +
                ", Exterior / abaixo: " + exterior.toString() + ", Anotação: " + txt_exterior.getText().toString() + ", Piso interior: " + piso_interior.toString() +
                ", Anotação: " + txt_piso_interior.getText().toString() +
                ", Portas afora/adentro: " + portas_afora.toString() + ", Anotação: " + txt_portas.getText().toString() +
                ", Paredes laterais: " + paredes_laterais.toString() + ", Anotação: " + txt_paredes.getText().toString() +
                ", Teto interior/exterior: " + teto_interior.toString() + ", Anotação: " + txt_teto.getText().toString() +
                ", Parede dianteira: " + parede_dianteira.toString() + ", Anotação: " + txt_parede_dianteira.getText().toString() +
                ", Unidade do Refrigerador: " + refrigerador.toString() + ", Anotação: " + txt_refrigerador.getText().toString() +
                ", Escapamento: " + escapamento.toString() + ", Anotação: " + txt_escapamento.getText().toString() + "\nA imagem está em anexo...");
        email.putExtra(Intent.EXTRA_STREAM, bmpUri);
        email.setType("image/*");
        // Send it off to the Activity-Chooser
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
    private void carregarImagem () {
        final CharSequence[] option = {"Tirar Foto", "Selecionar da Galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder( CheckList_Part1.this );
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

            Long consecultivo = System.currentTimeMillis() / 1000;
            String nome = consecultivo.toString() + ".jpg";

            //caminho completo da imagem
            path = Environment.getExternalStorageDirectory() + File.separator + DIRETORIO_IMAGEM + File.separator + nome;  //caminho completo da imagem
            fileImagem = new File(path);
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagem));
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                String authorities= CheckList_Part1.this.getPackageName()+".provider";
                Uri imageUri= FileProvider.getUriForFile(CheckList_Part1.this,authorities,fileImagem);
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
                    bitmap = MediaStore.Images.Media.getBitmap( CheckList_Part1.this.getContentResolver(), cargaOEA );
                    imgFoto.setImageBitmap( bitmap );
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case COD_FOTO:
                MediaScannerConnection.scanFile( CheckList_Part1.this, new String[]{path}, null,
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
    private void carregarWebService() {
        String ip = getString( R.string.ip);
        String url = ip+"/chek17pontos/cadastro_checklist.php?";



        stringRequest = new StringRequest( Request.Method.POST, url, new Response.Listener <String>() {
            @Override
            public void onResponse ( String response ) {
                if (response.trim().equalsIgnoreCase( "registra" )) {
                    showToast( "Registro com sucesso");
                } else {
                    showToast("Registro não inserido" );
                    Log.i( "RESPOSTA: ", "" + response );
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse ( VolleyError error ) {
                showToast( "Não foi possível enviar os dados");
                Log.i("ERROR", error.toString());
            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String CheckBox_Parachoque = "", CheckBox_Parachoque2 = "", CheckBox_Parachoque3 = "", CheckBox_Motor = "", CheckBox_Motor2 = "", CheckBox_Motor3 = "",
                        CheckBox_Pneus = "", CheckBox_Pneus2 = "", CheckBox_Pneus3 = "", CheckBox_Piso = "", CheckBox_Piso2 = "", CheckBox_Piso3 = "", CheckBox_Tanque = "", CheckBox_Tanque2 = "",
                        CheckBox_Tanque3 = "", CheckBox_Cabine = "", CheckBox_Cabine2 = "", CheckBox_Cabine3 = "", CheckBox_Tanques_ar = "", CheckBox_Tanques_ar2 = "", CheckBox_Tanques_ar3 = "",
                        CheckBox_Eixos = "", CheckBox_Eixos2 = "", CheckBox_Eixos3 = "", CheckBox_Roda = "", CheckBox_Roda2 = "", CheckBox_Roda3 = "", CheckBox_Exterior = "", CheckBox_Exterior2 = "",
                        CheckBox_Exterior3 = "",
                        CheckBox_Piso_interior = "", CheckBox_Piso_interior2 = "", CheckBox_Piso_interior3 = "", CheckBox_Portas = "", CheckBox_Portas2 = "",
                        CheckBox_Portas3 = "", CheckBox_Paredes = "", CheckBox_Paredes2 = "", CheckBox_Paredes3 = "", CheckBox_Teto = "", CheckBox_Teto2 = "", CheckBox_Teto3 = "",
                        CheckBox_Parede_dianteira = "", CheckBox_Parede_dianteira2 = "", CheckBox_Parede_dianteira3 = "", CheckBox_Refrigerador = "", CheckBox_Refrigerador2 = "",
                        CheckBox_Refrigerador3 = "", CheckBox_Escapamento = "", CheckBox_Escapamento2 = "", CheckBox_Escapamento3 = "";
                String parachoque = null, motor = null, pneus = null, piso_cabine=null, combustivel=null, cabine = null, tanques_ar = null,eixos = null,
                        quinta_roda=null, exterior = null, piso_interior = null, portas_afora = null, paredes_laterais = null, teto_interior = null, parede_dianteira=null,
                        refrigerador = null,escapamento = null;

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

                if (checkBox_parachoque.isChecked()) {
                    CheckBox_Parachoque = checkBox_parachoque.getText().toString();
                    parachoque = checkBox_parachoque.getText().toString();
                    parametros.put("posterior_parachoques", CheckBox_Parachoque);

                }

                if (checkBox_parachoque2.isChecked()) {
                    CheckBox_Parachoque2 = checkBox_parachoque2.getText().toString();
                    parachoque = checkBox_parachoque2.getText().toString();
                    parametros.put("posterior_parachoques2", CheckBox_Parachoque2);

                }

                if (checkBox_parachoque2.isChecked()) {
                    CheckBox_Parachoque3 = checkBox_parachoque3.getText().toString();
                    parachoque = checkBox_parachoque3.getText().toString();
                    parametros.put("posterior_parachoques3", CheckBox_Parachoque3);
                }

                String Txt_parachoque = txt_parachoque.getText().toString();

                if (checkBox_motor.isChecked()) {
                    CheckBox_Motor = checkBox_motor.getText().toString();
                    motor = checkBox_motor.getText().toString();
                    parametros.put("motor", CheckBox_Motor);
                }

                if (checkBox_motor2.isChecked()) {
                    CheckBox_Motor2 = checkBox_motor2.getText().toString();
                    motor = checkBox_motor2.getText().toString();
                    parametros.put("motor2", CheckBox_Motor2);


                }

                if (checkBox_motor3.isChecked()) {
                    CheckBox_Motor3 = checkBox_motor3.getText().toString();
                    motor = checkBox_motor3.getText().toString();
                    parametros.put("motor3", CheckBox_Motor3);
                }

                String Txt_motor = txt_motor.getText().toString();
                if (checkBox_pneus.isChecked()) {
                    CheckBox_Pneus = checkBox_pneus.getText().toString();
                    pneus = checkBox_pneus.getText().toString();
                    parametros.put("Pneus_Calotas", CheckBox_Pneus);
                }

                if (checkBox_pneus2.isChecked()) {
                    CheckBox_Pneus2 = checkBox_pneus2.getText().toString();
                    pneus = checkBox_pneus2.getText().toString();
                    parametros.put("Pneus_Calotas2", CheckBox_Pneus2);

                }

                if (checkBox_pneus3.isChecked()) {
                    CheckBox_Pneus3 = checkBox_pneus3.getText().toString();
                    pneus = checkBox_pneus3.getText().toString();
                    parametros.put("Pneus_Calotas3", CheckBox_Pneus3);
                }

                String Txt_pneus = txt_pneus.getText().toString();

                if (checkBox_piso.isChecked()) {
                    CheckBox_Piso = checkBox_piso.getText().toString();
                    piso_cabine = checkBox_piso.getText().toString();
                    parametros.put("Piso_Cabine", CheckBox_Piso);

                }

                if (checkBox_piso2.isChecked()) {
                    CheckBox_Piso2 = checkBox_piso2.getText().toString();
                    piso_cabine = checkBox_piso2.getText().toString();
                    parametros.put("Piso_Cabine2", CheckBox_Piso2);

                }
                if (checkBox_piso3.isChecked()) {
                    CheckBox_Piso3 = checkBox_piso3.getText().toString();
                    piso_cabine = checkBox_piso3.getText().toString();
                    parametros.put("Piso_Cabine3", CheckBox_Piso3);

                }
                String Txt_piso = txt_piso.getText().toString();

                if (checkBox_tanque.isChecked()) {
                    CheckBox_Tanque = checkBox_tanque.getText().toString();
                    combustivel = checkBox_tanque.getText().toString();
                    parametros.put("tanque_combustivel", CheckBox_Tanque);

                }

                if (checkBox_tanque2.isChecked()) {
                    CheckBox_Tanque2 = checkBox_tanque2.getText().toString();
                    combustivel = checkBox_tanque2.getText().toString();
                    parametros.put("tanque_combustivel2", CheckBox_Tanque2);

                }

                if (checkBox_tanque3.isChecked()) {
                    CheckBox_Tanque3 = checkBox_tanque3.getText().toString();
                    combustivel = checkBox_tanque3.getText().toString();
                    parametros.put("tanque_combustivel3", CheckBox_Tanque3);
                }

                String Txt_tanque = txt_tanque.getText().toString();

                if (checkBox_cabine.isChecked()) {
                    CheckBox_Cabine = checkBox_cabine.getText().toString();
                    cabine = checkBox_cabine.getText().toString();
                    parametros.put("cabine", CheckBox_Cabine);

                }

                if (checkBox_cabine2.isChecked()) {
                    CheckBox_Cabine2 = checkBox_cabine2.getText().toString();
                    cabine = checkBox_cabine2.getText().toString();
                    parametros.put("cabine2", CheckBox_Cabine2);

                }

                if (checkBox_cabine3.isChecked()) {
                    CheckBox_Cabine3 = checkBox_cabine3.getText().toString();
                    cabine = checkBox_cabine3.getText().toString();
                    parametros.put("cabine3", CheckBox_Cabine3);
                }

                String Txt_cabine = txt_cabine.getText().toString();
                if (checkBox_tanques_ar.isChecked()) {
                    CheckBox_Tanques_ar = checkBox_tanques_ar.getText().toString();
                    tanques_ar = checkBox_tanques_ar.getText().toString();
                    parametros.put("tanques_ar", CheckBox_Tanques_ar);

                }

                if (checkBox_tanques_ar2.isChecked()) {
                    CheckBox_Tanques_ar2 = checkBox_tanques_ar2.getText().toString();
                    tanques_ar = checkBox_tanques_ar2.getText().toString();
                    parametros.put("tanques_ar2", CheckBox_Tanques_ar2);

                }

                if (checkBox_tanques_ar3.isChecked()) {
                    CheckBox_Tanques_ar3 = checkBox_tanques_ar3.getText().toString();
                    tanques_ar = checkBox_tanques_ar3.getText().toString();
                    parametros.put("tanques_ar3", CheckBox_Tanques_ar3);

                }

                String Txt_tanques_ar = txt_tanques_ar.getText().toString();

                if (checkBox_eixos.isChecked()) {
                    CheckBox_Eixos = checkBox_eixos.getText().toString();
                    eixos = checkBox_eixos.getText().toString();
                    parametros.put("eixos_transmissao", CheckBox_Eixos);

                }

                if (checkBox_eixos2.isChecked()) {
                    CheckBox_Eixos2 = checkBox_eixos2.getText().toString();
                    eixos = checkBox_eixos2.getText().toString();
                    parametros.put("eixos_transmissao2", CheckBox_Eixos2);

                }

                if (checkBox_eixos3.isChecked()) {
                    CheckBox_Eixos3 = checkBox_eixos3.getText().toString();
                    eixos = checkBox_eixos3.getText().toString();
                    parametros.put("eixos_transmissao3", CheckBox_Eixos3);

                }

                String Txt_eixos = txt_eixos.getText().toString();

                if (checkBox_roda.isChecked()) {
                    CheckBox_Roda = checkBox_roda.getText().toString();
                    quinta_roda = checkBox_roda.getText().toString();
                    parametros.put("area_quintaRoda", CheckBox_Roda);

                }

                if (checkBox_roda2.isChecked()) {
                    CheckBox_Roda2 = checkBox_roda2.getText().toString();
                    quinta_roda = checkBox_roda2.getText().toString();
                    parametros.put("area_quintaRoda2", CheckBox_Roda2);

                }

                if (checkBox_roda3.isChecked()) {
                    CheckBox_Roda3 = checkBox_roda3.getText().toString();
                    quinta_roda = checkBox_roda3.getText().toString();
                    parametros.put("area_quintaRoda3", CheckBox_Roda3);

                }

                String Txt_roda = txt_roda.getText().toString();
                if (checkBox_exterior.isChecked()) {
                    CheckBox_Exterior = checkBox_exterior.getText().toString();
                    exterior = checkBox_exterior.getText().toString();
                    parametros.put("exterior_abaixo", CheckBox_Exterior);

                }

                if (checkBox_exterior2.isChecked()) {
                    CheckBox_Exterior2 = checkBox_exterior2.getText().toString();
                    exterior = checkBox_exterior2.getText().toString();
                    parametros.put("exterior_abaixo2", CheckBox_Exterior2);

                }

                if (checkBox_exterior3.isChecked()) {
                    CheckBox_Exterior3 = checkBox_exterior3.getText().toString();
                    exterior = checkBox_exterior3.getText().toString();
                    parametros.put("exterior_abaixo3", CheckBox_Exterior3);

                }

                String Txt_exterior = txt_exterior.getText().toString();

                if (checkBox_piso_interior.isChecked()) {
                    CheckBox_Piso_interior = checkBox_piso_interior.getText().toString();
                    piso_interior = checkBox_piso_interior.getText().toString();
                    parametros.put("piso_interior", CheckBox_Piso_interior);

                }

                if (checkBox_piso_interior2.isChecked()) {
                    CheckBox_Piso_interior2 = checkBox_piso_interior2.getText().toString();
                    piso_interior = checkBox_piso_interior2.getText().toString();

                    parametros.put("piso_interior2", CheckBox_Piso_interior2);

                }

                if (checkBox_piso_interior3.isChecked()) {
                    CheckBox_Piso_interior3 = checkBox_piso_interior3.getText().toString();
                    piso_interior = checkBox_piso_interior3.getText().toString();
                    parametros.put("piso_interior3", CheckBox_Piso_interior3);

                }

                String Txt_piso_interior = txt_piso_interior.getText().toString();

                if (checkBox_portas.isChecked()) {
                    CheckBox_Portas = checkBox_portas.getText().toString();
                    portas_afora = checkBox_portas.getText().toString();
                    parametros.put("portas_afora_adentro", CheckBox_Portas);
                }

                if (checkBox_portas2.isChecked()) {
                    CheckBox_Portas2 = checkBox_portas2.getText().toString();
                    portas_afora = checkBox_portas2.getText().toString();
                    parametros.put("portas_afora_adentro2", CheckBox_Portas2);
                }

                if (checkBox_portas3.isChecked()) {
                    CheckBox_Portas3 = checkBox_portas3.getText().toString();
                    portas_afora = checkBox_portas3.getText().toString();
                    parametros.put("portas_afora_adentro3", CheckBox_Portas3);
                }

                String Txt_portas = txt_portas.getText().toString();

                if (checkBox_paredes.isChecked()) {
                    CheckBox_Paredes = checkBox_paredes.getText().toString();
                    paredes_laterais = checkBox_paredes.getText().toString();
                    parametros.put("paredes_laterais", CheckBox_Paredes);
                }

                if (checkBox_paredes2.isChecked()) {
                    CheckBox_Paredes2 = checkBox_paredes2.getText().toString();
                    paredes_laterais = checkBox_paredes2.getText().toString();
                    parametros.put("paredes_laterais2", CheckBox_Paredes2);
                }

                if (checkBox_paredes3.isChecked()) {
                    CheckBox_Paredes3 = checkBox_paredes3.getText().toString();
                    paredes_laterais = checkBox_paredes3.getText().toString();
                    parametros.put("paredes_laterais3", CheckBox_Paredes3);
                }

                String Txt_paredes = txt_paredes.getText().toString();

                if (checkBox_teto.isChecked()) {
                    CheckBox_Teto = checkBox_teto.getText().toString();
                    teto_interior = checkBox_teto.getText().toString();
                    parametros.put("teto_interior_exterior", CheckBox_Teto);
                }

                if (checkBox_teto2.isChecked()) {
                    CheckBox_Teto2 = checkBox_teto2.getText().toString();
                    teto_interior = checkBox_teto2.getText().toString();
                    parametros.put("teto_interior_exterior2", CheckBox_Teto2);
                }

                if (checkBox_teto3.isChecked()) {
                    CheckBox_Teto3 = checkBox_teto3.getText().toString();
                    teto_interior = checkBox_teto3.getText().toString();

                    parametros.put("teto_interior_exterior3", CheckBox_Teto3);
                }

                String Txt_teto = txt_teto.getText().toString();

                if (checkBox_parede_dianteira.isChecked()) {
                    CheckBox_Parede_dianteira = checkBox_parede_dianteira.getText().toString();
                    parede_dianteira = checkBox_parede_dianteira.getText().toString();
                    parametros.put("parede_dianteira", CheckBox_Parede_dianteira);
                }

                if (checkBox_parede_dianteira2.isChecked()) {
                    CheckBox_Parede_dianteira2 = checkBox_parede_dianteira2.getText().toString();
                    parede_dianteira = checkBox_parede_dianteira2.getText().toString();
                    parametros.put("parede_dianteira2", CheckBox_Parede_dianteira2);
                }

                if (checkBox_parede_dianteira3.isChecked()) {
                    CheckBox_Parede_dianteira3 = checkBox_parede_dianteira3.getText().toString();
                    parede_dianteira = checkBox_parede_dianteira3.getText().toString();
                    parametros.put("parede_dianteira3", CheckBox_Parede_dianteira3);
                }

                String Txt_parede_dianteira = txt_parede_dianteira.getText().toString();

                if (checkBox_refrigerador.isChecked()) {
                    CheckBox_Refrigerador = checkBox_refrigerador.getText().toString();
                    refrigerador = checkBox_refrigerador.getText().toString();
                    parametros.put("unidade_refrigerador", CheckBox_Refrigerador);
                }

                if (checkBox_refrigerador2.isChecked()) {
                    CheckBox_Refrigerador2 = checkBox_refrigerador2.getText().toString();
                    refrigerador = checkBox_refrigerador2.getText().toString();
                    parametros.put("unidade_refrigerador2", CheckBox_Refrigerador2);
                }

                if (checkBox_refrigerador3.isChecked()) {
                    CheckBox_Refrigerador3 = checkBox_refrigerador3.getText().toString();
                    refrigerador = checkBox_refrigerador3.getText().toString();
                    parametros.put("unidade_refrigerador3", CheckBox_Refrigerador3);
                }

                String Txt_refrigerador = txt_refrigerador.getText().toString();

                if (checkBox_escapamento.isChecked()) {
                    CheckBox_Escapamento = checkBox_escapamento.getText().toString();
                    escapamento = checkBox_escapamento.getText().toString();
                    parametros.put("escapamento", CheckBox_Escapamento);
                }

                if (checkBox_escapamento2.isChecked()) {
                    CheckBox_Escapamento2 = checkBox_escapamento2.getText().toString();
                    escapamento = checkBox_escapamento2.getText().toString();
                    parametros.put("escapamento2", CheckBox_Escapamento2);

                }

                if (checkBox_escapamento3.isChecked()) {
                    CheckBox_Escapamento3 = checkBox_escapamento3.getText().toString();
                    escapamento = checkBox_escapamento3.getText().toString();
                    parametros.put("escapamento3", CheckBox_Escapamento3);

                }

                String Txt_escapamento = txt_escapamento.getText().toString();
                String combustivel_imagem = converterImgString(bitmap);

                parametros.put("posterior_anotacao", Txt_parachoque);
                parametros.put("motor_anotacao", Txt_motor);
                parametros.put("pneus_anotacao", Txt_pneus);
                parametros.put("piso_anotacao", Txt_piso);
                parametros.put("tanque_anotacao", Txt_tanque);
                parametros.put("cabine_anotacao", Txt_cabine);
                parametros.put("tanquesAr_anotacao", Txt_tanques_ar);
                parametros.put("eixos_anotacao", Txt_eixos);

                parametros.put("quintaRoda_anotacao", Txt_roda);
                parametros.put("exterior_anotacao", Txt_exterior);
                parametros.put("pisoInterior_anotacao", Txt_piso_interior);
                parametros.put("portas_anotacao", Txt_portas);
                parametros.put("paredes_anotacao", Txt_paredes);
                parametros.put("teto_anotacao", Txt_teto);
                parametros.put("paredeDianteira_anotacao", Txt_parede_dianteira);
                parametros.put("refrigerador_anotacao", Txt_refrigerador);
                parametros.put("escapamento_anotacao", Txt_escapamento);

                parametros.put("imagem", combustivel_imagem);
                sendEmail(parachoque, motor, pneus, piso_cabine, combustivel, cabine, tanques_ar, eixos,
                        quinta_roda, exterior, piso_interior, portas_afora, paredes_laterais, teto_interior, parede_dianteira,
                        refrigerador, escapamento);

                return parametros;

            }

        };

        MySingleton.getInstance(CheckList_Part1.this).addToRequestQueue(stringRequest);


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
                        EscolherTipoImg.class);
                startActivity(myIntent);
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
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
                Toast.makeText(CheckList_Part1.this,
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
        if((CheckList_Part1.this.checkSelfPermission(WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)&&(CheckList_Part1.this.checkSelfPermission(CAMERA)==PackageManager.PERMISSION_GRANTED)){
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
                Toast.makeText(CheckList_Part1.this,"Permissões Aceitas",Toast.LENGTH_SHORT);
                btnFoto.setEnabled(true);
            }
        }else{
            solicitarPermissoesManual();
        }
    }


    private void solicitarPermissoesManual() {
        final CharSequence[] opciones={"sim","não"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(CheckList_Part1.this);
        alertOpciones.setTitle("Deseja configurar as permissões manualmente?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("sim")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",CheckList_Part1.this.getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(CheckList_Part1.this,"Permissões Aceitas",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void carregarDialogoRecomendacao() {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(CheckList_Part1.this);
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
