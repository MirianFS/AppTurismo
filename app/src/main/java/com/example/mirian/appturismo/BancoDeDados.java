package com.example.mirian.appturismo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class BancoDeDados extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS ponto_turistico(codigo INTEGER PRIMARY KEY, nome TEXT, foto TEXT, local TEXT, descricao TEXT, data TEXT, entrada TEXT)");

            //Inserir dados (linhas que estão comentadas já foram executadas e já existem no banco)
            //bancoDados.execSQL("INSERT INTO ponto_turistico(codigo, nome, foto, local, descricao, data, entrada) VALUES(1, 'Local 1', 'imagem1.png', 'local 1', 'descrição do local 1', 'data 1', 'entrada 1')");
            //bancoDados.execSQL("INSERT INTO ponto_turistico(codigo, nome, foto, local, descricao, data, entrada) VALUES(2, 'Local 2', 'imagem2.png', 'local 2', 'descrição do local 2', 'data 2', 'entrada 2')");

            Cursor cursor = bancoDados.rawQuery("SELECT * FROM ponto_turistico", null);

            int indiceColunaCodigo = cursor.getColumnIndex("codigo");
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaFoto = cursor.getColumnIndex("foto");
            int indiceColunaDescricao = cursor.getColumnIndex("descricao");
            int indiceColunaData = cursor.getColumnIndex("data");
            int indiceColunaEntrada = cursor.getColumnIndex("entrada");

            cursor.moveToFirst();

            while (cursor != null) {
                Log.i("RESULTADO - Código: ", cursor.getString(indiceColunaCodigo));
                Log.i("RESULTADO - Nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - Foto: ", cursor.getString(indiceColunaFoto));
                Log.i("RESULTADO - Descrição: ", cursor.getString(indiceColunaDescricao));
                Log.i("RESULTADO - Data: ", cursor.getString(indiceColunaData));
                Log.i("RESULTADO - Entrada: ", cursor.getString(indiceColunaEntrada));
                cursor.moveToNext();
            }

        } catch (Exception e) {
                e.printStackTrace();
        }

    }
}