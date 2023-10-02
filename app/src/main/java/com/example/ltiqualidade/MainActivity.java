package com.example.ltiqualidade;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //coloca o fundo branco que foi definido no tema
        setTheme(R.style.theme_main);

        setContentView(R.layout.activity_main);

        //definindo links para as imagens de redes socias
        ImageView botaoGmail = findViewById(R.id.id_gmail);
        botaoGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:lti.qualidade@gmail.com")));
            }
        });

        ImageView botaoWhatsapp = findViewById(R.id.id_whatsapp);
        botaoWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send/?phone=71999851376&text&type=phone_number&app_absent=0")));
        }
        });

        ImageView botaoInstagram = findViewById(R.id.id_instagram);
        botaoInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/ltiqualidade/")));
        }
        });
    }
    //acionando botoes de acesso as outras telas
    public void manutencao(View view){
        Intent intent1 = new Intent(getApplicationContext(), manutencao.class);
        startActivity(intent1);
    }
    public void instalacao(View view){
        Intent intent1 = new Intent(getApplicationContext(), instalacao.class);
        startActivity(intent1);
    }
    public void outros(View view){
        Intent intent1 = new Intent(getApplicationContext(), outros.class);
        startActivity(intent1);
    }
}
