package com.example.mirian.appturismo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mirian.appturismo.PontoTuristico;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mirian on 12/06/2017.
 */

public class BDPontoTuristico extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO =1;
    private static final String BANCO_PONTOTURISTICO = "bd_pontoturistico.db";

    private static final String TABELA_PONTOTURISTICO = "tb_pontoturistico";

    private static final String COLUNA_CODIGO = "_codigo";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_FOTO = "foto";
    private static final String COLUNA_LOCAL = "local";
    private static final String COLUNA_DESCRICAO = "descricao";
    private static final String COLUNA_DATA = "data";
    private static final String COLUNA_ENTRADA = "entrada";
    private static final String COLUNA_TEMINTERESSE = "teminteresse";


    public BDPontoTuristico(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, BANCO_PONTOTURISTICO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE" +TABELA_PONTOTURISTICO+ "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUNA_NOME + " TEXT,"
                + COLUNA_FOTO + " TEXT,"
                + COLUNA_LOCAL + " TEXT,"
                + COLUNA_DESCRICAO + " TEXT,"
                + COLUNA_DATA + " TEXT,"
                + COLUNA_ENTRADA + " TEXT" +")";

        db.execSQL(QUERY_COLUNA);
    }

    void adicionarPontoTuristico (PontoTuristico pontoTuristico){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, pontoTuristico.getNome());
        values.put(COLUNA_FOTO, pontoTuristico.getFoto());
        values.put(COLUNA_LOCAL, pontoTuristico.getLocal());
        values.put(COLUNA_DESCRICAO, pontoTuristico.getDescricao());
        values.put(COLUNA_DATA, pontoTuristico.getData());
        values.put(COLUNA_ENTRADA, pontoTuristico.getEntrada());
        values.put(COLUNA_TEMINTERESSE, pontoTuristico.getTeminteresse());

        db.insert(TABELA_PONTOTURISTICO, null, values);
        //db.close();
    }

    public List<PontoTuristico> ListarFavoritos (){ // atualmente estar pegando todos os pontos turistico do banco
        List<PontoTuristico> listaFavoritos = new ArrayList<PontoTuristico>();
        String query = "SELECT * FROM" + TABELA_PONTOTURISTICO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                PontoTuristico pontoTuristicoFavoritos = new PontoTuristico();

                pontoTuristicoFavoritos.setCodigo(Integer.parseInt(cursor.getString(0)));
                pontoTuristicoFavoritos.setNome(cursor.getString(1));
                pontoTuristicoFavoritos.setFoto(cursor.getString(2));
                pontoTuristicoFavoritos.setLocal(cursor.getString(3));
                pontoTuristicoFavoritos.setDescricao(cursor.getString(4));
                pontoTuristicoFavoritos.setData(cursor.getString(5));
                pontoTuristicoFavoritos.setEntrada(cursor.getString(6));
                pontoTuristicoFavoritos.setTeminteresse(cursor.getString(7));

                listaFavoritos.add(pontoTuristicoFavoritos);
            } while (cursor.moveToNext());
        }

        return listaFavoritos;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
