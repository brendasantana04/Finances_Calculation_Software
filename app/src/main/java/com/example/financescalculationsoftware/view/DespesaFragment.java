package com.example.financescalculationsoftware.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.financescalculationsoftware.R;
import com.example.financescalculationsoftware.controller.DespesaController;
import com.example.financescalculationsoftware.model.Despesa;
import com.example.financescalculationsoftware.persistence.DatabaseHelper;
import com.example.financescalculationsoftware.persistence.DespesaDao;

import java.sql.SQLException;
import java.util.List;

public class DespesaFragment extends Fragment {

    private EditText edtDescricao, edtValor, edtCategoria, edtId;
    private Button btnCadastrar, btnAtualizar, btnDeletar, btnProcurar, btnListar;
    private TextView txtResultados;
    private DespesaController despesaController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_despesa, container, false);

        edtDescricao = view.findViewById(R.id.edtDescricaoDespesa);
        edtValor = view.findViewById(R.id.edtValorDespesa);
        edtCategoria = view.findViewById(R.id.edtCategoriaDespesa);
        edtId = view.findViewById(R.id.edtIdDespesa);

        btnCadastrar = view.findViewById(R.id.btnCadastrarDespesa);
        btnAtualizar = view.findViewById(R.id.btnAtualizarDespesa);
        btnDeletar = view.findViewById(R.id.btnDeletarDespesa);
        btnProcurar = view.findViewById(R.id.btnProcurarDespesa);
        btnListar = view.findViewById(R.id.btnListarDespesa);

        txtResultados = view.findViewById(R.id.txtResultadosDespesa);

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        despesaController = new DespesaController(new DespesaDao(dbHelper.getWritableDatabase()));

        btnCadastrar.setOnClickListener(op -> cadastrarDespesa());
        btnAtualizar.setOnClickListener(op -> atualizarDespesa());
        btnDeletar.setOnClickListener(op -> deletarDespesa());
        btnProcurar.setOnClickListener(op -> procurarDespesa());
        btnListar.setOnClickListener(op -> listarDespesas());

        return view;
    }

    private void cadastrarDespesa() {
        try {
            String descricao = edtDescricao.getText().toString();
            double valor = Double.parseDouble(edtValor.getText().toString());
            String categoria = edtCategoria.getText().toString();

            Despesa despesa = new Despesa();
            despesa.setDescricao(descricao);
            despesa.setValor(valor);
            despesa.setCategoria(categoria);

            despesaController.inserir(despesa);
            Toast.makeText(getContext(), "Despesa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao cadastrar despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void atualizarDespesa() {
        try {
            int id = Integer.parseInt(edtId.getText().toString());
            Despesa despesa = despesaController.procurarUm(id);
            if (despesa == null) {
                Toast.makeText(getContext(), "Despesa não encontrada!", Toast.LENGTH_SHORT).show();
                return;
            }

            despesa.setDescricao(edtDescricao.getText().toString());
            despesa.setValor(Double.parseDouble(edtValor.getText().toString()));
            despesa.setCategoria(edtCategoria.getText().toString());

            despesaController.atualizar(despesa);
            Toast.makeText(getContext(), "Despesa atualizada com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao atualizar despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void deletarDespesa() {
        try {
            int id = Integer.parseInt(edtId.getText().toString());
            Despesa despesa = despesaController.procurarUm(id);
            if (despesa == null) {
                Toast.makeText(getContext(), "Despesa não encontrada!", Toast.LENGTH_SHORT).show();
                return;
            }

            despesaController.deletar(despesa);
            Toast.makeText(getContext(), "Despesa deletada com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao deletar despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void procurarDespesa() {
        try {
            int id = Integer.parseInt(edtId.getText().toString());
            Despesa despesa = despesaController.procurarUm(id);
            if (despesa == null) {
                txtResultados.setText("Despesa não encontrada!");
            } else {
                txtResultados.setText(despesa.toString());
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao procurar despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void listarDespesas() {
        try {
            List<Despesa> despesas = despesaController.procurarTodos();
            StringBuilder resultado = new StringBuilder();
            for (Despesa despesa : despesas) {
                resultado.append(despesa).append("\n");
            }
            txtResultados.setText(resultado.toString());
        } catch (SQLException e) {
            Toast.makeText(getContext(), "Erro ao listar despesas: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void limparCampos() {
        edtDescricao.setText("");
        edtValor.setText("");
        edtCategoria.setText("");
        edtId.setText("");
    }
}
