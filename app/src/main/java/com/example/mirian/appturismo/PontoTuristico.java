package com.example.mirian.appturismo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PontoTuristico {

    private int codigo;
    private String nome;
    private String foto;
    private String local;
    private String descricao;
    private String data;
    private String entrada;
    private boolean favorito;
    private BancoDeDados bancoDeDados;

    public PontoTuristico () {
    }

    public PontoTuristico(int codigo, String nome, String foto, String local, String descricao, String data, String entrada, boolean favorito) {
        this.codigo = codigo;
        this.nome = nome;
        this.foto = foto;
        this.local = local;
        this.descricao = descricao;
        this.data = data;
        this.entrada = entrada;
        this.favorito = favorito;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(bancoDeDados.COLUNA_CODIGO, this.codigo);
        cv.put(bancoDeDados.COLUNA_NOME, this.nome);
        cv.put(bancoDeDados.COLUNA_FOTO, this.foto);
        cv.put(bancoDeDados.COLUNA_LOCAL, this.local);
        cv.put(bancoDeDados.COLUNA_DESCRICAO, this.descricao);
        cv.put(bancoDeDados.COLUNA_DATA, this.data);
        cv.put(bancoDeDados.COLUNA_ENTRADA, this.entrada);
        cv.put(bancoDeDados.COLUNA_FAVORITO, this.favorito);
        return cv;
    }

}
