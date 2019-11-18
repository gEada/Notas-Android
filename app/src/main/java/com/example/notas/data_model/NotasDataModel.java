package com.example.notas.data_model;

import android.util.Log;

public class NotasDataModel {



    private  final static String id = "id";

    private  final static String TABELA = "notas";

    private final static String titulo = "titulo";

    private final static String conteudo = "conteudo";

    private  final static String data = "data";
    private static String queryCriarTabela = "";


    public static String criarQueryTabela(){

        queryCriarTabela += "CREATE TABLE " + TABELA;
        queryCriarTabela += "(";
        queryCriarTabela += id + " INTEGER AUTO_INCREMENT, ";
        queryCriarTabela  += titulo + " TEXT, ";
        queryCriarTabela += conteudo + " LONGTEXT, ";
        queryCriarTabela += data + " DATE, ";
        queryCriarTabela += " PRIMARY KEY(id)";
        queryCriarTabela += ")";

        Log.i("dataModel", "Query --> " + queryCriarTabela);

        return queryCriarTabela;
    }

    public static String getId() {
        return id;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getTitulo() {
        return titulo;
    }

    public static String getConteudo() {
        return conteudo;
    }

    public static String getData() {
        return data;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        NotasDataModel.queryCriarTabela = queryCriarTabela;
    }
}
