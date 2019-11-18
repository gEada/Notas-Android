package com.example.notas.data_source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.notas.data_model.NotasDataModel;
import com.example.notas.model.Notas;

public class NotasDataSource extends SQLiteOpenHelper {

    private static final String DB_NAME = "notas_app.sql";
    private static final int DB_VERSION = 1;

    SQLiteDatabase db;
    Cursor cursor;

    public NotasDataSource(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();



    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(NotasDataModel.criarQueryTabela());
            Log.i("datasource", "Tabela " + NotasDataModel.getTABELA() + " criada!");
        }catch (Exception e){
            Log.e("datasource", "ERRO DB: " + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    protected boolean insert(String tabela, ContentValues contentValues){

        boolean sucesso = false;

        try {

            sucesso = db.insert(tabela, null,
                    contentValues) > 0;

        }catch (Exception e){
            Log.e("dataSource", "Erro no SQL ->> " + e.getMessage());
        }

        return sucesso;

    }

    protected boolean delete(String tabela, int id){
        boolean sucesso = false;

        try{

            sucesso = db.delete(tabela,"id=?",
            new String[]{Integer.toString(id)}) > 0;

        }catch (Exception e){
            Log.e("dataSource", "Erro no SQL ->> " + e.getMessage());

        }

        return sucesso;

    }



}
