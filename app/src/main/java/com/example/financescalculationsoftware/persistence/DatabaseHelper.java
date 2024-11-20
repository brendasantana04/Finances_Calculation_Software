package com.example.financescalculationsoftware.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "TransacoesApp.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_DESPESA =
            "CREATE TABLE Despesa (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "descricao TEXT NOT NULL, " +
                    "valor REAL NOT NULL, " +
                    "categoria TEXT NOT NULL);";

    private static final String CREATE_TABLE_WISHLIST =
            "CREATE TABLE Wishlist (" +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "descricao TEXT NOT NULL, " +
                    "valor REAL NOT NULL, " +
                    "prioridade TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHelper", "onCreate chamado");
        db.execSQL(CREATE_TABLE_DESPESA);
        db.execSQL(CREATE_TABLE_WISHLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "onUpgrade chamado - Versão antiga: " + oldVersion + ", nova versão: " + newVersion);
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS Despesa");
            db.execSQL("DROP TABLE IF EXISTS Wishlist");
            onCreate(db);
        }
    }
}

