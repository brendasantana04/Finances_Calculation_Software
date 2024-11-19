package com.example.financescalculationsoftware.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.financescalculationsoftware.R;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private TextView labelAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajusta o layout para o modo imersivo
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        // Verifica o tipo do fragmento a ser exibido
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tipo = bundle.getString("tipo");
            if ("Despesas".equals(tipo)) {
                fragment = new DespesaFragment();
            } else if ("Lista de Desejos".equals(tipo)) {
                fragment = new WishlistFragment();
            }

            // Exibe o fragmento inicial
            if (fragment != null) {
                replaceFragment(fragment);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_despesas) {
            // Navega para o fragmento de despesas
            fragment = new DespesaFragment();
            replaceFragment(fragment);
            return true;
        } else if (id == R.id.menu_lista_desejos) {
            // Navega para o fragmento de wishlist
            fragment = new WishlistFragment();
            replaceFragment(fragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
//    Código para esconder a label do nome do app quando vc entrar no fragment
//    private void replaceFragment(Fragment fragment) {
//        FragmentManager fm = getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//
//        if (labelAppName != null) {
//            labelAppName.setVisibility(View.GONE);
//        }
//
//        ft.replace(R.id.fragment_container, fragment);
//        ft.commit();
//    }
}
