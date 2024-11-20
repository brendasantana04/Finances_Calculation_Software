package com.example.financescalculationsoftware.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.financescalculationsoftware.model.Despesa;

import java.util.ArrayList;
import java.util.List;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class DespesaDao implements ICRUDDao<Despesa> {

    private final SQLiteDatabase database;

    public DespesaDao(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void insert(Despesa despesa) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("descricao", despesa.getDescricao());
        values.put("valor", despesa.getValor());
        values.put("categoria", despesa.getCategoria());

        database.insert("Despesa", null, values);
    }

    @Override
    public int update(Despesa despesa) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("descricao", despesa.getDescricao());
        values.put("valor", despesa.getValor());
        values.put("categoria", despesa.getCategoria());

        return database.update("Despesa", values, "id = ?", new String[]{String.valueOf(despesa.getId())});
    }

    @Override
    public void delete(Despesa despesa) throws SQLException {
        database.delete("Despesa", "id = ?", new String[]{String.valueOf(despesa.getId())});
    }

    @SuppressLint("Range")
    @Override
    public Despesa findOne(int id) throws SQLException {
        Cursor cursor = database.query("Despesa", null, "id = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Despesa despesa = new Despesa();
            despesa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            despesa.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            despesa.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
            despesa.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));
            cursor.close();
            return despesa;
        }

        return null;
    }

    @SuppressLint("Range")
    @Override
    public List<Despesa> findAll() throws SQLException {
        List<Despesa> despesas = new ArrayList<>();
        Cursor cursor = database.query("Despesa", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Despesa despesa = new Despesa();
                despesa.setId(cursor.getInt(cursor.getColumnIndex("id")));
                despesa.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                despesa.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                despesa.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));
                despesas.add(despesa);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return despesas;
    }
}

