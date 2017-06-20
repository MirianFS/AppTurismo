package com.example.mirian.appturismo;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DetalheActivity extends AppCompatActivity {

    private ImageView imagem;
    private Button botaoVoltar;
    private Button botaoMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        imagem = (ImageView) findViewById(R.id.imagemId);
        botaoVoltar = (Button) findViewById(R.id.botaoVoltarId);
        botaoMapa = (Button) findViewById(R.id.botaoMapaId);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String opcaoEscolhida = extra.getString("opcao");
            if (opcaoEscolhida.equals("parquedaluz")) {
                imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.parquedaluz));
            } else if (opcaoEscolhida.equals("mirantemorropedras")){
                imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.mirantemorropedras));
            }
        }

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetalheActivity.this, BancoDeDados.class));
            }
        });

        botaoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:<LAT>,<LONG>?q=<QUERY>");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
}
