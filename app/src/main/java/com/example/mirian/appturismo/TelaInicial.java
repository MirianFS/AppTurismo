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
import java.util.List;

public class TelaInicial extends Activity {

    private ListView listView;

    private Button botaoFavoritos;

    private BancoDeDados bd;

    private SQLiteDatabase database;

    private ArrayAdapter adapter;

    private List<PontoTuristico> pontosTuristicos;

    private int posicaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        listView = (ListView) findViewById(R.id.listViewId);
        botaoFavoritos = (Button) findViewById(R.id.botaoFavoritosId);
        bd = new BancoDeDados(this);
        database = bd.getWritableDatabase();
        inserirPontosTuristicos();

        botaoFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaInicial.this, FavoritosActivity.class));
            }
        });

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        setArrayAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TelaInicial.this, DetalheActivity.class);
                PontoTuristico pt = bd.consultarPontoTuristicoPorId(pontosTuristicos.get(position).getCodigo());
                int codigo = pt.getCodigo();
                i.putExtra("opcao", codigo);
                startActivity(i);
            }
        });

    }

    public void inserirPontosTuristicos() {
        //PontoTuristico pt1 = new PontoTuristico(1, "Ponto 1", "foto 1", "local 1", "descricao 1", "data 1","entrada 1", false);
        //bd.salvarPontoTuristico(pt1);
        //PontoTuristico pt2 = new PontoTuristico(2, "Ponto 2", "foto 2", "local 2", "descricao 2", "data 2","entrada 2", true);
        //bd.salvarPontoTuristico(pt2);
    }

    private void setArrayAdapter() {
        pontosTuristicos = bd.listarPontosTuristicos();

        List<String> valores = new ArrayList<String>();
        for (PontoTuristico pt : pontosTuristicos) {
            valores.add(pt.getNome());
        }

        adapter.clear();
        adapter.addAll(valores);
        listView.setAdapter(adapter);
    }

}