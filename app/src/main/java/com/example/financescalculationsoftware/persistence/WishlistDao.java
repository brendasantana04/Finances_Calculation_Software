package com.example.financescalculationsoftware.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.financescalculationsoftware.model.Wishlist;

import java.util.ArrayList;
import java.util.List;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class WishlistDao implements ICRUDDao<Wishlist> {

    private final SQLiteDatabase database;

    public WishlistDao(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void insert(Wishlist wishlist) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("descricao", wishlist.getDescricao());
        values.put("valor", wishlist.getValor());
        values.put("prioridade", wishlist.getPrioridade());

        database.insert("Wishlist", null, values);
    }

    @Override
    public int update(Wishlist wishlist) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("descricao", wishlist.getDescricao());
        values.put("valor", wishlist.getValor());
        values.put("prioridade", wishlist.getPrioridade());

        return database.update("Wishlist", values, "id = ?", new String[]{String.valueOf(wishlist.getId())});
    }

    @Override
    public void delete(Wishlist wishlist) throws SQLException {
        database.delete("Wishlist", "id = ?", new String[]{String.valueOf(wishlist.getId())});
    }

    @SuppressLint("Range")
    @Override
    public Wishlist findOne(int id) throws SQLException {
        Cursor cursor = database.query("Wishlist", null, "id = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setId(cursor.getInt(cursor.getColumnIndex("id")));
            wishlist.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            wishlist.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
            wishlist.setPrioridade(cursor.getString(cursor.getColumnIndex("prioridade")));
            cursor.close();
            return wishlist;
        }

        return null;
    }

    @SuppressLint("Range")
    @Override
    public List<Wishlist> findAll() throws SQLException {
        List<Wishlist> wishlists = new ArrayList<>();
        Cursor cursor = database.query("Wishlist", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Wishlist wishlist = new Wishlist();
                wishlist.setId(cursor.getInt(cursor.getColumnIndex("id")));
                wishlist.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                wishlist.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                wishlist.setPrioridade(cursor.getString(cursor.getColumnIndex("prioridade")));
                wishlists.add(wishlist);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return wishlists;
    }
}

