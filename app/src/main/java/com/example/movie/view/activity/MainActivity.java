package com.example.movie.view.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.database.AppDatabase;
import com.example.movie.database.TiketModel;
import com.example.movie.view.fragment.MovieFragment;
import com.example.movie.view.fragment.TiketFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private AppDatabase appDatabase;
    private Button btnBeli, btnData;
    private EditText etIdfilm, etJudul;
    private Fragment selectedFragment = new MovieFragment();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.activitymain_bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(selectedFragment);

        btnData = findViewById(R.id.btn_data);
        btnBeli = findViewById(R.id.btn_beli);

        etIdfilm = findViewById(R.id.edit3);
        etJudul = findViewById(R.id.edit1);

        appDatabase = AppDatabase.initDatabase(getApplicationContext());

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    TiketModel tiketModel = new TiketModel();

                    tiketModel.setIdfilm(etIdfilm.getText().toString());
                    tiketModel.setJudul(etJudul.getText().toString());

                    appDatabase.tiketDAO().insertTiket(tiketModel);

                    Log.d("MainActivity", "sukses");
                    Toast.makeText(getApplicationContext(), "Berhasil Dibeli", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("MainActivity", "proses gagal, msg : "+ex.getMessage());
                    Toast.makeText(getApplicationContext(), "Proses Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_bottomnav_movie:
                selectedFragment = new MovieFragment();
                loadFragment(selectedFragment);
                break;

            case R.id.menu_bottomnav_tiket:
                selectedFragment = new TiketFragment();
                loadFragment(selectedFragment);
                break;
        }

        return loadFragment(selectedFragment);
    }

    private boolean loadFragment(Fragment selectedFragment) {
        if (selectedFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activitymain_fragment,selectedFragment)
                    .commit();
            return true;
        }
        return false;
    }
}
