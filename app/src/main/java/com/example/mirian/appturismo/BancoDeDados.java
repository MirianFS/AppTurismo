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

public class BancoDeDados extends Activity {

    private SQLiteDatabase bancoDados;

    private ListView listaItens;

    private String[] opcao = {
            "parquedaluz", "mirantemorropedras"
    };

    private Button botaoFavoritos;

    private ArrayList listaLocais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        listaItens = (ListView) findViewById(R.id.listViewId);
        listaLocais = new ArrayList<String>();
        botaoFavoritos = (Button) findViewById(R.id.botaoFavoritosId);

        botaoFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BancoDeDados.this, FavoritosActivity.class));
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text2,
                listaLocais
        );

        listaItens.setAdapter(adapter);

        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BancoDeDados.this, DetalheActivity.class);
                intent.putExtra("opcao", opcao[position]);
                startActivity(intent);
            }
        });

        try {

            bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Deletar a tabela
            //bancoDados.execSQL("DROP TABLE ponto_turistico");

            //Criar a tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS ponto_turistico(codigo INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, foto TEXT, local TEXT, descricao TEXT, data TEXT, entrada TEXT, favorito BOOLEAN)");

                //Inserir dados (linhas que estão comentadas já foram executadas e já existem no banco)
           //bancoDados.execSQL("INSERT INTO ponto_turistico(nome, foto, local, descricao, data, entrada, favorito) VALUES('Local 1', 'parquedaluz', 'local 1', 'descrição do local 1', 'data 1', 'entrada 1', 'false')");
           //bancoDados.execSQL("INSERT INTO ponto_turistico(nome, foto, local, descricao, data, entrada, favorito) VALUES('Local 2', 'mirantemorropedras', 'local 2', 'descrição do local 2', 'data 2', 'entrada 2', 'true')");

            recuperarDados();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private void recuperarDados() {

            try {

                //Recuperar os dados da tabela
                Cursor cursor = bancoDados.rawQuery("SELECT * FROM ponto_turistico", null);

                //Recuperar os ids das colunas
                int indiceColunaCodigo = cursor.getColumnIndex("codigo");
                int indiceColunaNome = cursor.getColumnIndex("nome");
                int indiceColunaFoto = cursor.getColumnIndex("foto");
                int indiceColunaDescricao = cursor.getColumnIndex("descricao");
                int indiceColunaData = cursor.getColumnIndex("data");
                int indiceColunaEntrada = cursor.getColumnIndex("entrada");
                int indiceColunaFavorito = cursor.getColumnIndex("favorito");

                //Listar os dados da tabela
                cursor.moveToFirst();
                while (cursor != null) {
                    Log.i("RESULTADO - Código: ", cursor.getString(indiceColunaCodigo));
                    Log.i("RESULTADO - Nome: ", cursor.getString(indiceColunaNome));
                    Log.i("RESULTADO - Foto: ", cursor.getString(indiceColunaFoto));
                    Log.i("RESULTADO - Descrição: ", cursor.getString(indiceColunaDescricao));
                    Log.i("RESULTADO - Data: ", cursor.getString(indiceColunaData));
                    Log.i("RESULTADO - Entrada: ", cursor.getString(indiceColunaEntrada));
                    Log.i("RESULTADO - Favorito: ", cursor.getString(indiceColunaFavorito));
                    listaLocais.add(cursor.getString(indiceColunaNome) + " - "
                            + cursor.getString(indiceColunaFoto) + " - "
                            + cursor.getString(indiceColunaDescricao) + " - "
                            + cursor.getString(indiceColunaData) + " - "
                            + cursor.getString(indiceColunaEntrada) + " - "
                            + cursor.getString(indiceColunaFavorito));
                    cursor.moveToNext();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public SQLiteDatabase getBancoDados() {
            return bancoDados;
        }

}