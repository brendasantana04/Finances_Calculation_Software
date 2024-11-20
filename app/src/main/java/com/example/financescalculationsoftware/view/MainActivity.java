package com.example.financescalculationsoftware.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.financescalculationsoftware.R;

/*
 *@author:<Brenda>
 *@ra:<1110482313042>
 */

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private TextView labelAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tipo = bundle.getString("tipo");
            if ("Despesas".equals(tipo)) {
                fragment = new DespesaFragment();
            } else if ("Lista de Desejos".equals(tipo)) {
                fragment = new WishlistFragment();
            }

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
            fragment = new DespesaFragment();
            replaceFragment(fragment);
            return true;
        } else if (id == R.id.menu_lista_desejos) {
            fragment = new WishlistFragment();
            replaceFragment(fragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        ImageView imageViewLogo = findViewById(R.id.image_view);
        imageViewLogo.setVisibility(View.GONE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView imageViewLogo = findViewById(R.id.image_view);
        imageViewLogo.setVisibility(View.VISIBLE);
    }
}
