package com.example.financescalculationsoftware.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.financescalculationsoftware.R;
import com.example.financescalculationsoftware.controller.WishlistController;
import com.example.financescalculationsoftware.model.Wishlist;
import com.example.financescalculationsoftware.persistence.DatabaseHelper;
import com.example.financescalculationsoftware.persistence.WishlistDao;

import java.util.List;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class WishlistFragment extends Fragment {

    private EditText edtIdWishlist, edtDescricaoWishlist, edtValorWishlist, edtPrioridadeWishlist;
    private Button btnCadastrarWishlist, btnAtualizarWishlist, btnDeletarWishlist, btnProcurarWishlist, btnListarWishlist;
    private TextView txtResultadosWishlist;

    private WishlistController wishlistController;

    public WishlistFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        edtIdWishlist = view.findViewById(R.id.edtIdWishlist);
        edtDescricaoWishlist = view.findViewById(R.id.edtDescricaoWishlist);
        edtValorWishlist = view.findViewById(R.id.edtValorWishlist);
        edtPrioridadeWishlist = view.findViewById(R.id.edtPrioridadeWishlist);
        btnCadastrarWishlist = view.findViewById(R.id.btnCadastrarWishlist);
        btnAtualizarWishlist = view.findViewById(R.id.btnAtualizarWishlist);
        btnDeletarWishlist = view.findViewById(R.id.btnDeletarWishlist);
        btnProcurarWishlist = view.findViewById(R.id.btnProcurarWishlist);
        btnListarWishlist = view.findViewById(R.id.btnListarWishlist);
        txtResultadosWishlist = view.findViewById(R.id.txtResultadosWishlist);

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        WishlistDao wishlistDao = new WishlistDao(databaseHelper.getWritableDatabase());
        wishlistController = new WishlistController(wishlistDao);

        btnCadastrarWishlist.setOnClickListener(v -> {
            cadastrarWishlist();
        });

        btnAtualizarWishlist.setOnClickListener(v -> {
            atualizarWishlist();
        });

        btnDeletarWishlist.setOnClickListener(v -> {
            deletarWishlist();
        });

        btnProcurarWishlist.setOnClickListener(v -> {
            procurarWishlist();
        });

        btnListarWishlist.setOnClickListener(v -> {
            listarWishlist();
        });

        return view;
    }

    private void cadastrarWishlist() {
        String descricao = edtDescricaoWishlist.getText().toString();
        double valor = Double.parseDouble(edtValorWishlist.getText().toString());
        String prioridade = edtPrioridadeWishlist.getText().toString();

        Wishlist wishlist = new Wishlist();
        wishlist.setDescricao(descricao);
        wishlist.setValor(valor);
        wishlist.setPrioridade(prioridade);

        try {
            wishlistController.inserir(wishlist);
            limparCampos();
            listarWishlist();
            Toast.makeText(getContext(), "produto da wishlist foi encontrado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "erro: ", Toast.LENGTH_SHORT).show();
        }
    }


    private void atualizarWishlist() {
        int id = Integer.parseInt(edtIdWishlist.getText().toString());
        String descricao = edtDescricaoWishlist.getText().toString();
        double valor = Double.parseDouble(edtValorWishlist.getText().toString());
        String prioridade = edtPrioridadeWishlist.getText().toString();

        Wishlist wishlist = new Wishlist();
        wishlist.setId(id);
        wishlist.setDescricao(descricao);
        wishlist.setValor(valor);
        wishlist.setPrioridade(prioridade);

        try {
            wishlistController.atualizar(wishlist);
            limparCampos();
            listarWishlist();
            Toast.makeText(getContext(), "produto da wishlist foi atualizado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "erro: ", Toast.LENGTH_SHORT).show();
        }
    }

    private void deletarWishlist() {
        int id = Integer.parseInt(edtIdWishlist.getText().toString());

        try {
            Wishlist wishlist = new Wishlist();
            wishlist.setId(id);
            wishlistController.deletar(wishlist);
            limparCampos();
            listarWishlist();
            Toast.makeText(getContext(), "produto da wishlist foi deletado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "erro: ", Toast.LENGTH_SHORT).show();
        }
    }

    private void procurarWishlist() {
        int id = Integer.parseInt(edtIdWishlist.getText().toString());

        try {
            Wishlist wishlist = wishlistController.procurarUm(id);
            if (wishlist != null) {
                edtDescricaoWishlist.setText(wishlist.getDescricao());
                edtValorWishlist.setText(String.valueOf(wishlist.getValor()));
                edtPrioridadeWishlist.setText(wishlist.getPrioridade());
            } else {
                Toast.makeText(getContext(), "produto da wishlist não existe", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "erro: ", Toast.LENGTH_SHORT).show();
        }
    }

    private void listarWishlist() {
        try {
            List<Wishlist> wishlists = wishlistController.procurarTodos();
            StringBuilder sb = new StringBuilder();
            for (Wishlist wishlist : wishlists) {
                sb.append(wishlist.toString()).append("\n\n");
            }
            txtResultadosWishlist.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "erro: ", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        edtIdWishlist.setText("");
        edtDescricaoWishlist.setText("");
        edtValorWishlist.setText("");
        edtPrioridadeWishlist.setText("");
    }
}
