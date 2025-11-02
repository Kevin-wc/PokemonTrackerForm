package com.example.pokemontrackerform;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pokemontrackerform.model.PokedexContentProvider;
import com.example.pokemontrackerform.model.Pokemon;

public class PokemonListActivity extends AppCompatActivity {
    ListView listView;
    SimpleCursorAdapter adapter;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        listView = findViewById(R.id.listView);

        loadData();
    }

    private void loadData(){
        cursor = getContentResolver().query(PokedexContentProvider.CONTENT_URI,
                null, null, null, null);

        String[] columns = {
                "national_number",
                "name",
                "species"
        };

        int[] views = {
                R.id.rowNumber,
                R.id.rowName,
                R.id.rowSpecies
        };
        adapter = new SimpleCursorAdapter(this, R.layout.custom_row, cursor, columns, views, 0);

        listView.setAdapter(adapter);
    }
}