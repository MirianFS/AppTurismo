package com.example.mirian.appturismo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritosActivity extends Activity {

    private ListView listaFavoritos;
    private String[] opcao = {
            "parquedaluz", "mirantemorropedras"
    };

    private ArrayAdapter<String> adapter;

    private ArrayList itens;

    private BancoDeDados bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        listaFavoritos = (ListView) findViewById(R.id.listaFavoritosId);
        itens = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text2,
                itens

        );

        listaFavoritos.setAdapter(adapter);

        listaFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavoritosActivity.this, DetalheActivity.class);
                intent.putExtra("opcao", opcao[position]);
                startActivity(intent);
            }
        });

    }

}
