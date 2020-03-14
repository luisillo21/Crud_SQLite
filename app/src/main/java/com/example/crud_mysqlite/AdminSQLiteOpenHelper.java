package com.example.crud_mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context,String nombre,SQLiteDatabase.CursorFactory factory, int version){
         super(context,nombre,factory,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // AQUI SE crea la table USUARIO(idUsuario,nom2bre,telefono);
        db.execSQL("CREATE TABLE usuarios(idUsuario text primary key,nombre text,telefono text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("CREATE TABLE usuarios(idUsuario text primary key,nombre text,telefono text)");
    }
}
