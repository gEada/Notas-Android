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

import java.util.ArrayList;

public class DataSource extends SQLiteOpenHelper {

    private static final String DB_NAME = "notas_app.sql";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db;
    private Cursor cursor;

    public DataSource(@Nullable Context context) {
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

    protected boolean update(String tabela, ContentValues contentValues){
        boolean sucesso;

        int id = contentValues.getAsInteger("id");
        try {

            sucesso = db.update(tabela, contentValues, "id=?", new String[]{Integer.toString(id)}) > 0;
        }catch(Exception e){
            sucesso = false;

            Log.e("dataSource", "ERROR DB ->>> " + e.getMessage());
        }



        return sucesso;
    }

    protected ArrayList<Notas> getAllNotas(){
        Notas obj = new Notas();

        ArrayList<Notas> arrayList = new ArrayList<Notas>();

        String SQL = "SELECT * FROM " + NotasDataModel.getTABELA() + " DESC";

        cursor = db.rawQuery(SQL, null);

        if(cursor.moveToFirst()){

            do {

                obj.setConteudo(cursor.getString(cursor.getColumnIndex(NotasDataModel.getConteudo())));
                obj.setData(cursor.getString(cursor.getColumnIndex(NotasDataModel.getData())));
                obj.setTitulo(cursor.getString(cursor.getColumnIndex(NotasDataModel.getTitulo())));

                arrayList.add(obj);

            }while (cursor.moveToNext());
        }

        cursor.close();


        return arrayList;

    }



}
