package com.example.mirian.appturismo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FavoritosActivity extends Activity {

    private BancoDeDados bancoDeDados;
    private ListView listaFavoritos;
    private String[] opcao = {
            "parquedaluz", "mirantemorropedras"
    };

    private ArrayAdapter<String> adapter;

    private ArrayList itens;

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

        try {
            listarFavoritos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void listarFavoritos() {
            try {

                //Recuperar os dados da tabela
                Cursor cursor = bancoDeDados.getBancoDados().rawQuery("SELECT * FROM ponto_turistico WHERE favorito = 'true'", null);

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
                    itens.add(cursor.getString(indiceColunaNome) + " - "
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


}
