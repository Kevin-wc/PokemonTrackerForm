package com.example.pokemontrackerform;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PokedexActivity extends AppCompatActivity {

    EditText numberET;
    EditText nameET;
    EditText speciesET;
    RadioButton femaleB;
    RadioButton maleB;
    RadioButton unknownB;
    EditText heightET;
    EditText weightET;
    Spinner levelS;
    EditText hpET;
    EditText attackET;
    EditText defenseET;
    Button resetB;
    Button saveB;

    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pokedex);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numberET = findViewById(R.id.numberET);
        nameET = findViewById(R.id.nameET);
        speciesET = findViewById(R.id.speciesET);
        femaleB = findViewById(R.id.femaleB);
        maleB = findViewById(R.id.maleB);
        unknownB = findViewById(R.id.unknownB);
        heightET = findViewById(R.id.heightET);
        weightET = findViewById(R.id.weightET);
        levelS = findViewById(R.id.levelS);
        hpET = findViewById(R.id.hpET);
        attackET = findViewById(R.id.attackET);
        defenseET = findViewById(R.id.defenseET);
        resetB = findViewById(R.id.resetB);
        saveB = findViewById(R.id.saveB);



    }
}