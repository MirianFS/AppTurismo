package com.example.mirian.appturismo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TelaInicial extends Activity {

    private ListView listaItens;

    private String[] opcao = {
            "parquedaluz", "mirantemorropedras"
    };

    private Button botaoFavoritos;

    private BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        listaItens = (ListView) findViewById(R.id.listViewId);
        botaoFavoritos = (Button) findViewById(R.id.botaoFavoritosId);

        botaoFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaInicial.this, FavoritosActivity.class));
            }
        });

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text2,
                bd.getLista()
        );

        listaItens.setAdapter(adapter);

        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TelaInicial.this, DetalheActivity.class);
                intent.putExtra("opcao", opcao[position]);
                startActivity(intent);
            }
        });
*/

    }



}