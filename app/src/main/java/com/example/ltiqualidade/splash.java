package com.example.ltiqualidade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    private static final int SPLASH_DURATION = 5000; // Tempo de exibição da tela de splash em milissegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Usando um Handler para atrasar a transição para a tela principal
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finaliza a atividade da tela de splash para evitar que o usuário retorne a ela pressionando o botão Voltar
            }
        }, SPLASH_DURATION);
    }
}