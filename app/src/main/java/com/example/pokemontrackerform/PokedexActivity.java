package com.example.pokemontrackerform;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Array;
import java.util.*;

import org.w3c.dom.Text;

public class PokedexActivity extends AppCompatActivity {

    TextView numberTV;
    EditText numberET;
    TextView nameTV;
    EditText nameET;
    EditText speciesET;
    RadioButton femaleB;
    RadioButton maleB;
    RadioButton unknownB;
    TextInputLayout heightLayout;
    TextInputEditText heightTIL;
    TextInputLayout weightLayout;

    TextInputEditText weightTIL;
    Spinner levelS;
    String selectLevel = "";
    EditText hpET;
    EditText attackET;
    EditText defenseET;
    Button resetB;
    Button saveB;
    RadioGroup genderRG;

    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.resetB){
                reset();
            }
            if (v.getId() == R.id.saveB){
                sumbit();
            }
        }
    };

    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectLevel = parent.getItemAtPosition(position).toString();
            Log.i("DEBUG", "Select order type " + selectLevel);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            selectLevel = "";
        }
    };
    public void reset() {

        numberET.getText().clear();
        nameET.getText().clear();
        speciesET.getText().clear();
        genderRG.check(R.id.unknownB);
        heightTIL.getText().clear();
        weightTIL.getText().clear();
        levelS.setSelection(0);
        hpET.getText().clear();
        attackET.getText().clear();
        defenseET.getText().clear();
    }

    public void sumbit(){
    }


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
        heightLayout = findViewById(R.id.heightTIL);
        heightTIL= findViewById(R.id.heightTIL_input);
        weightLayout = findViewById(R.id.weightTIL);
        weightTIL = findViewById(R.id.weightTIL_input);
        levelS = findViewById(R.id.levelS);
        hpET = findViewById(R.id.hpET);
        attackET = findViewById(R.id.attackET);
        defenseET = findViewById(R.id.defenseET);
        resetB = findViewById(R.id.resetB);
        saveB = findViewById(R.id.saveB);
        genderRG = findViewById(R.id.genderG);

        resetB.setOnClickListener(buttonListener);
        saveB.setOnClickListener(buttonListener);

        LinkedList<Integer> levelsList = new LinkedList<>();
        for (int i = 1; i <= 50; i++){
            levelsList.add(i);
        }
        ArrayAdapter<Integer> levelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,levelsList);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelS.setAdapter(levelAdapter);
        levelS.setOnItemSelectedListener(spinnerListener);



    }
}