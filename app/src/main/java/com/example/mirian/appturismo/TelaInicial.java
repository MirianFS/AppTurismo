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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TelaInicial extends AppCompatActivity {

    private Button botaoLocalizacao;
    BDPontoTuristico bd = new BDPontoTuristico(this, "BANCO_PONTOTURISTICO", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        botaoLocalizacao = (Button) findViewById(R.id.botao_localizacao);
        ListView listView = (ListView) findViewById(R.id.listarPontosTuristicos);

        // testando adicionar Ponto Turistico no banco de dados
        // atualmente o adicinar ponto turistico não funciona :(
       bd.adicionarPontoTuristico(new PontoTuristico(1, "nome","foto", "local", "descricao","data", "entrada", "teminteresse"));
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
