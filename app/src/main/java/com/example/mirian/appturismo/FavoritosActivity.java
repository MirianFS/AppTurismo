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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends Activity {

    private ListView listaFavoritos;

    private ArrayAdapter<String> adapter;

    private ArrayList itens;

    private BancoDeDados bd;

    private SQLiteDatabase database;

    private List<PontoTuristico> pontosTuristicosFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        listaFavoritos = (ListView) findViewById(R.id.listaFavoritosId);
        itens = new ArrayList<String>();
        bd = new BancoDeDados(this);
        database = bd.getWritableDatabase();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        setArrayAdapter();

        listaFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(FavoritosActivity.this, DetalheActivity.class);
                PontoTuristico pt = bd.consultarPontoTuristicoPorId(pontosTuristicosFavoritos.get(position).getCodigo());
                int codigo = pt.getCodigo();
                i.putExtra("opcao", codigo);
                startActivity(i);
            }
        });

    }

    private void setArrayAdapter() {
        pontosTuristicosFavoritos = bd.listarPontosTuristicos();

        List<String> valores = new ArrayList<String>();
        for (PontoTuristico pt : pontosTuristicosFavoritos) {
           if (pt.getFavorito().equals("Sim")) {
                valores.add(pt.getNome());
           }
        }

        adapter.clear();
        adapter.addAll(valores);
        listaFavoritos.setAdapter(adapter);
    }

}
