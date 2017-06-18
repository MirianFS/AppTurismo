package com.example.mirian.appturismo;

import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TelaInicial extends AppCompatActivity {

    private Button botaoLocalizacao;

    private ListView listaItens;

    private String[] itens = {
           "Local 1",
           "Local 2",
           "Local 3"
    };
    private String[] descricao = {
            "Descrição do local 1",
            "Descrição do local 2",
            "Descrição do local 3"
    };

    BDPontoTuristico bd = new BDPontoTuristico(this, "BANCO_PONTOTURISTICO", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        botaoLocalizacao = (Button) findViewById(R.id.botao_localizacao);

        listaItens = (ListView) findViewById(R.id.listViewId);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        listaItens.setAdapter(adapter);

        // testando adicionar Ponto Turistico no banco de dados
        // atualmente o adicinar ponto turistico não funciona :(
        //bd.adicionarPontoTuristico(new PontoTuristico(1, "nome","foto", "local", "descricao","data", "entrada", "teminteresse"));
//Toast.makeText(TelaInicial.this, "salvo com sucesso", Toast.LENGTH_LONG).show();


        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigoPosicao = position;
                Toast.makeText(getApplicationContext(), descricao[position], Toast.LENGTH_SHORT).show();
            }
        });

        // testando adicionar Ponto Turistico no banco de dados
        // atualmente o adicinar ponto turistico não funciona :(
       //bd.adicionarPontoTuristico(new PontoTuristico(1, "nome","foto", "local", "descricao","data", "entrada", "teminteresse"));
        //Toast.makeText(TelaInicial.this, "salvo com sucesso", Toast.LENGTH_LONG).show();


        botaoLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:<LAT>,<LONG>?q=<QUERY>");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    public void listarPontosTuristicos (){
        List<PontoTuristico> pontoTuristicos = bd.ListarFavoritos();

        for (PontoTuristico pt: pontoTuristicos){
            Log.d("Lista", "\nID: " + pt.getCodigo() + "\nNome: " + pt.getNome());
        }
    }
}
