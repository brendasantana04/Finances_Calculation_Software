package com.example.financescalculationsoftware.controller;

import com.example.financescalculationsoftware.model.Wishlist;
import com.example.financescalculationsoftware.persistence.WishlistDao;

import java.sql.SQLException;
import java.util.List;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class WishlistController implements IRegistroTransacao<Wishlist> {

    private final WishlistDao wishlistDao;

    public WishlistController(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @Override
    public void inserir(Wishlist wishlist) throws SQLException {
        wishlistDao.insert(wishlist);
        System.out.println("Wishlist inserida: " + wishlist);
    }

    @Override
    public void atualizar(Wishlist wishlist) throws SQLException {
        wishlistDao.update(wishlist);
        System.out.println("Wishlist atualizada: " + wishlist);
    }

    @Override
    public void deletar(Wishlist wishlist) throws SQLException {
        wishlistDao.delete(wishlist);
        System.out.println("Wishlist deletada: " + wishlist);
    }

    @Override
    public Wishlist procurarUm(int id) throws SQLException {
        return wishlistDao.findOne(id);
    }

    @Override
    public List<Wishlist> procurarTodos() throws SQLException {
        return wishlistDao.findAll();
    }
}

