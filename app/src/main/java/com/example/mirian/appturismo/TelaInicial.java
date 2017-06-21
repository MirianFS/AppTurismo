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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends Activity {

    private ListView listView;

    private Button botaoFavoritos;

    private BancoDeDados bd;

    private SQLiteDatabase database;

    private ArrayAdapter adapter;

    private List<PontoTuristico> pontosTuristicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        listView = (ListView) findViewById(R.id.listViewId);
        botaoFavoritos = (Button) findViewById(R.id.botaoFavoritosId);
        bd = new BancoDeDados(this);
        database = bd.getWritableDatabase();
        //bd.removerPontoTuristicoPorId(1);
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

    public void inserirPontosTuristicos() {

        // primeiro ponto turístico adicionado é o Parque da Luz
        PontoTuristico pt1 = new PontoTuristico();
        pt1.setCodigo(1);
        pt1.setNome("Parque da Luz");
        pt1.setFoto("parquedaluz");
        pt1.setLocal("geo:-27.592407,-48.560654?q=Parque+da+Luz");
        pt1.setDescricao("O Parque da Luz é uma área verde de lazer da cidade brasileira de Florianópolis, capital do estado de " +
                "Santa Catarina. Com área de 37.435 m², está localizado integralmente na zona urbana central da cidade, nos altos" +
                " da rua Felipe Schmidt.");
        pt1.setData("Aberto todos os dias 24h.");
        pt1.setEntrada("Gratuito.");
        pt1.setFavorito(false);
        bd.salvarPontoTuristico(pt1);

// segundo ponto turístico adicionado é Beto Carrero World
        PontoTuristico pt2 = new PontoTuristico();
        pt2.setCodigo(2);
        pt2.setNome("Beto Carrero World");
        pt2.setFoto("betocarrero");
        pt2.setLocal("geo:-26.8016998,-48.6197606?q=Beto+Carrero+World");
        pt2.setDescricao("Beto Carrero World é um parque temático localizado no litoral norte do estado de Santa Catarina, Brasil." +
                " Inaugurado no dia 28 de dezembro de 1991, pelo seu idealizador João Batista Sérgio Murad, artisticamente conhecido" +
                " como Beto Carrero, o parque foi desenvolvido em uma área de 14 milhões de metros quadrados.");
        pt2.setData("Aberto de quinta-feira à domingo das 09:00h às 18:00h.");
        pt2.setEntrada("De R$ 70,00 à R$ 130,00.");
        pt2.setFavorito(false);
        bd.salvarPontoTuristico(pt2);

// terceiro ponto turístico adicionado é Praia da Joaquina
        PontoTuristico pt3 = new PontoTuristico();
        pt3.setCodigo(3);
        pt3.setNome("Praia da Joaquina");
        pt3.setFoto("praiadajoaquina");
        pt3.setLocal("geo:/@-27.6295906,-48.4510236?q=Praia+da+Joaquina");
        pt3.setDescricao("Praia da Joaquina é uma praia oceânica da cidade brasileira de Florianópolis, ao leste da ilha de Santa" +
                " Catarina, ao sul do Brasil. O ponto procurado por surfistas, já foi sede de alguns campeonatos mundiais de surfe.");
        pt3.setData("Aberto todos os dias 24h.");
        pt3.setEntrada("Gratuito.");
        pt3.setFavorito(false);
        bd.salvarPontoTuristico(pt3);

// quarto ponto turístico adicionado é Fortaleza de São José da Ponta Grossa
        PontoTuristico pt4 = new PontoTuristico();
        pt4.setCodigo(4);
        pt4.setNome("Fortaleza de São José da Ponta Grossa");
        pt4.setFoto("fortalezasjpg");
        pt4.setLocal("geo:/@-27.4316589,-48.5199969?q=Fortaleza+de+S%C3%A3o+Jos%C3%A9+da+Ponta+Grossa");
        pt4.setDescricao("A Fortaleza de São José da Ponta Grossa localiza-se entre as praias do Forte e do Jurerê, no litoral" +
                " do estado de Santa Catarina, no Brasil. Ergue-se em posição dominante na encosta do morro da Ponta Grossa, " +
                "a noroeste da ilha de Santa Catarina, dominando a baía Norte.");
        pt4.setData("Aberto todos os dias das 09:00h às 18:00h.");
        pt4.setEntrada("De R$ 4,00 à R$ 10,00.");
        pt4.setFavorito(false);
        bd.salvarPontoTuristico(pt4);
    }

}