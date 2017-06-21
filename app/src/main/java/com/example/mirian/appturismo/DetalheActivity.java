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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class DetalheActivity extends AppCompatActivity {

    private ImageView imagem;
    private Button botaoMapa;
    private Button botaoAddFavoritos;
    private BancoDeDados bd;
    private SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        imagem = (ImageView) findViewById(R.id.imagemId);
        botaoMapa = (Button) findViewById(R.id.botaoMapaId);
        botaoAddFavoritos = (Button) findViewById(R.id.botaoAddFavoritosId);
        bd = new BancoDeDados(this);
        database = bd.getWritableDatabase();

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int codigo = extra.getInt("opcao");
            /*
            if (opcaoEscolhida.equals("parquedaluz")) {
                imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.parquedaluz));
            } else if (opcaoEscolhida.equals("mirantemorropedras")){
                imagem.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.mirantemorropedras));
            }
            */

            PontoTuristico pt = bd.consultarPontoTuristicoPorId(codigo);
            StringBuilder info = new StringBuilder();
            info.append("Nome: " + pt.getNome());
            info.append("\nFoto: " + pt.getFoto());
            info.append("\nLocal: " + pt.getLocal());
            info.append("\nDescrição: " + pt.getDescricao());
            info.append("\nData: " + pt.getData());
            info.append("\nEntrada: " + pt.getEntrada());
        }

        botaoMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:-27.592407,-48.560654?q=Parque+da+Luz");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        botaoAddFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //recuperar cód do item no banco
                //alterar parâmetro deste item no banco para favorito = true
                Toast.makeText(DetalheActivity.this, "Local adicionado aos Favoritos.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
