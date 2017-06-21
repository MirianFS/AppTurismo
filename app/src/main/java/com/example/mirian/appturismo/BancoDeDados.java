package com.example.mirian.appturismo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class BancoDeDados extends SQLiteOpenHelper {

    public ContentValues contentValues;
    private PontoTuristico pontoTuristico;

    private static final String BD_NOME = "banco.db";
    private static final int BD_VERSAO = 1;
    public static final String TABELA = "pontos_turisticos";
    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_FOTO = "foto";
    public static final String COLUNA_LOCAL = "local";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_ENTRADA = "entrada";
    public static final String COLUNA_FAVORITO = "favorito";

    public BancoDeDados(Context context) {
        super(context, BD_NOME, null, BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE = "CREATE TABLE " + TABELA + "( " + COLUNA_CODIGO
                + " INTEGER PRIMARY KEY ," + COLUNA_NOME + " TEXT NOT NULL,"
                + COLUNA_FOTO + " TEXT NOT NULL, " + COLUNA_LOCAL + " TEXT NOT NULL,"
                + COLUNA_DESCRICAO+ " TEXT NOT NULL, " + COLUNA_DATA + " TEXT NOT NULL,"
                + COLUNA_ENTRADA + " TEXT NOT NULL, " + COLUNA_FAVORITO + " BOOLEAN)";
        db.execSQL(SQL_CREATE);

        contentValues = new ContentValues();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }


    public List<PontoTuristico> listarPontosTuristicos() {

        List<PontoTuristico> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("pontos_turisticos", null, null, null, null, null, "nome");
        while (cursor.moveToNext()) {
            PontoTuristico pontoTuristico = new PontoTuristico();
            setPontoTuristicoFromCursor(cursor, pontoTuristico);
            lista.add(pontoTuristico);
        }

        return lista;
    }


    public void salvarPontoTuristico(PontoTuristico pontoTuristico) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = pontoTuristico.getContentValues();
        db.insert("pontos_turisticos", null, contentValues);
    }

    private void setPontoTuristicoFromCursor(Cursor cursor, PontoTuristico pontoTuristico) {
        pontoTuristico.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
        pontoTuristico.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        pontoTuristico.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
        pontoTuristico.setLocal(cursor.getString(cursor.getColumnIndex("local")));
        pontoTuristico.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        pontoTuristico.setData(cursor.getString(cursor.getColumnIndex("data")));
        pontoTuristico.setEntrada(cursor.getString(cursor.getColumnIndex("entrada")));
        pontoTuristico.setFavorito(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("favorito"))));
    }


}
