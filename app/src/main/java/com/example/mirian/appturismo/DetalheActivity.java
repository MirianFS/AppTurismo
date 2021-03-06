package com.example.mirian.appturismo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetalheActivity extends AppCompatActivity {

    private ImageView imagem;
    private Button botaoMapa;
    private Button botaoAddFavoritos;
    private BancoDeDados bd;
    private SQLiteDatabase database;
    private TextView dadosLocal;
    private PontoTuristico pt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        imagem = (ImageView) findViewById(R.id.imagemId);
        botaoMapa = (Button) findViewById(R.id.botaoMapaId);
        botaoAddFavoritos = (Button) findViewById(R.id.botaoAddFavoritosId);
        bd = new BancoDeDados(this);
        database = bd.getWritableDatabase();
        dadosLocal = (TextView) findViewById(R.id.campoTextoId);


        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int codigo = extra.getInt("opcao");

            pt = bd.consultarPontoTuristicoPorId(codigo);

            StringBuilder info = new StringBuilder();
            info.append("Nome: " + pt.getNome());
            info.append("\nDescrição: " + pt.getDescricao());
            info.append("\nData: " + pt.getData());
            info.append("\nEntrada: " + pt.getEntrada());
            info.append("\nFavorito: " + pt.getFavorito());
            dadosLocal.setText(info.toString());

            selecionarFoto();
        }

        botaoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapa = pt.getLocal();
                Uri uri = Uri.parse(mapa);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        botaoAddFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pt.setFavorito("Sim");
                bd.salvarPontoTuristico(pt);
                Toast.makeText(DetalheActivity.this, "Local adicionado aos Favoritos.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void selecionarFoto() {
        String enderecoImagem = pt.getFoto();
        if (enderecoImagem.equals("parquedaluz")) {
            imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.parquedaluz));
        } else if (enderecoImagem.equals("praiadajoaquina")) {
            imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.praiadajoaquina));
        } else if (enderecoImagem.equals("betocarrero")) {
            imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.betocarrero));
        } else if (enderecoImagem.equals("fortalezasjpg")) {
            imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fortalezasjpg));
        }
    }

}
