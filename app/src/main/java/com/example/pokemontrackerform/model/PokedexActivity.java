package com.example.pokemontrackerform.model;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
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

import com.example.pokemontrackerform.PokemonListActivity;
import com.example.pokemontrackerform.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.widget.Toast;


import java.util.*;

public class PokedexActivity extends AppCompatActivity {

    TextView numberTV;
    EditText numberET;
    TextView nameTV;
    EditText nameET;
    TextView speciesTV;
    EditText speciesET;
    TextView genderTV;
    RadioButton femaleB;
    RadioButton maleB;
    RadioButton unknownB;
    TextInputLayout heightLayout;
    TextView heightTV;
    TextInputEditText heightTIL;
    TextInputLayout weightLayout;
    TextView weightTV;

    TextInputEditText weightTIL;
    TextView levelsTV;
    Spinner levelS;
    String selectLevel = "";
    TextView hpTV;
    EditText hpET;
    TextView attackTV;
    EditText attackET;
    TextView defenseTV;
    EditText defenseET;
    Button resetB;
    Button saveB;
    RadioGroup genderRG;
    Button dbB;

    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.resetB){
                reset();
            }
            if (v.getId() == R.id.saveB){
                save();
            }
            if (v.getId() == R.id.dbB){
                startActivity(new Intent(PokedexActivity.this, PokemonListActivity.class));
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

    private String getSelectedGender() {
        if (maleB.isChecked()) return "Male";
        if (femaleB.isChecked()) return "Female";
        return "Unknown"; // fallback
    }

    public void save(){
        if(checkValues()) {
            ContentValues cv = new ContentValues();
            cv.put("national_number", Integer.parseInt(numberET.getText().toString()));
            cv.put("name", nameET.getText().toString());
            cv.put("species", speciesET.getText().toString());
            cv.put("gender", getSelectedGender());
            cv.put("height", Double.parseDouble(heightTIL.getText().toString()));
            cv.put("weight", Double.parseDouble(weightTIL.getText().toString()));
            cv.put("level", Integer.parseInt(selectLevel));
            cv.put("hp", Integer.parseInt(hpET.getText().toString()));
            cv.put("attack", Integer.parseInt(attackET.getText().toString()));
            cv.put("defense", Integer.parseInt(defenseET.getText().toString()));

            Uri result = getContentResolver().insert(
                    PokedexContentProvider.CONTENT_URI,
                    cv
            );

            if (result == null) {

                Toast.makeText(this,
                        "Pokémon already exists.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,
                        "Pokémon added to Pokédex!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.constraint);
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
        numberTV = findViewById(R.id.numberTV);
        nameTV = findViewById(R.id.nameTV);
        genderTV = findViewById(R.id.genderTV);
        levelsTV = findViewById(R.id.levelTV);
        hpTV      = findViewById(R.id.hpTV);
        attackTV  = findViewById(R.id.attackTV);
        defenseTV = findViewById(R.id.defenseTV);
        heightTV  = findViewById(R.id.heightTV);
        weightTV  = findViewById(R.id.weightTV);
        speciesTV = findViewById(R.id.speciesTV);
        dbB = findViewById(R.id.dbB);


        resetB.setOnClickListener(buttonListener);
        saveB.setOnClickListener(buttonListener);
        dbB.setOnClickListener(buttonListener);

        LinkedList<Integer> levelsList = new LinkedList<>();
        for (int i = 1; i <= 50; i++){
            levelsList.add(i);
        }
        ArrayAdapter<Integer> levelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,levelsList);
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelS.setAdapter(levelAdapter);
        levelS.setOnItemSelectedListener(spinnerListener);



    }


    private boolean checkValues() {
        boolean valid = true;


        //check if field is empty or not
        String number = numberET.getText().toString().trim();
        if (number.isEmpty()) {
            numberET.setError("Required");
            numberTV.setTextColor(getColor(R.color.red));
            valid = false;
        } else {
            //check if it fits parameters. (e.g numbers 0-1010).
            try {
                int value = Integer.parseInt(number);
                if (value < 0 || value > 1010){  //shouldn't have to worry about < 0 bc of widget but just in case
                    numberET.setError("0-1010 only");
                    numberTV.setTextColor(getColor(R.color.red));
                    valid = false;
                } else {
                    numberTV.setTextColor(getColor(R.color.black));
                }
            } catch (NumberFormatException e){
                numberET.setError("Whole number");
                numberTV.setTextColor(getColor(R.color.red));
                valid = false;
            }
        }


            String name = nameET.getText().toString().trim();
            if (name.isEmpty()) {
                nameET.setError("Required");
                nameTV.setTextColor(getColor(R.color.red));
                valid = false;
            } else if (!name.matches("[A-Za-z]{3,12}")) {
                nameET.setError("3–12 letters");
                nameTV.setTextColor(getColor(R.color.red));
                valid = false;
            } else {
                nameTV.setTextColor(getColor(R.color.black));
            }

            name = speciesET.getText().toString().trim();
            if (name.isEmpty()) {
                speciesET.setError("Required");
                speciesTV.setTextColor(getColor(R.color.red));
                valid = false;
            }  else {
                speciesTV.setTextColor(getColor(R.color.black));
            }

            if (!maleB.isChecked() && !femaleB.isChecked()) {
                genderTV.setTextColor(getColor(R.color.red));
                Toast.makeText(this, "Select Male or Female", Toast.LENGTH_SHORT).show();
                valid = false;
            } else {
                 genderTV.setTextColor(getColor(R.color.black));
            }

            number = hpET.getText().toString().trim();
            if (number.isEmpty()) {
                hpET.setError("Required");
                hpTV.setTextColor(getColor(R.color.red));
                valid = false;
            } else {
                try {
                    int value = Integer.parseInt(number);
                    if (value < 1 || value > 362) {
                        hpET.setError("1–362");
                        hpTV.setTextColor(getColor(R.color.red));
                        valid = false;
                    } else {
                        hpTV.setTextColor(getColor(R.color.black));
                    }
                } catch (NumberFormatException e) {
                    hpET.setError("Whole number");
                    hpTV.setTextColor(getColor(R.color.red));
                    valid = false;
                }
            }

            number = attackET.getText().toString().trim();
            if (number.isEmpty()) {
                attackET.setError("Required");
                attackTV.setTextColor(getColor(R.color.red));
                valid = false;
            } else {
                try {
                    int value = Integer.parseInt(number);
                    if (value < 0 || value > 526) {
                        attackET.setError("0–526");
                        attackTV.setTextColor(getColor(R.color.red));
                        valid = false;
                    } else {
                        attackTV.setTextColor(getColor(R.color.black));
                    }
                } catch (NumberFormatException e) {
                    attackET.setError("Whole number");
                    attackTV.setTextColor(getColor(R.color.red));
                    valid = false;
                }
            }

            number = defenseET.getText().toString().trim();
            if (number.isEmpty()) {
                defenseET.setError("Required");
                defenseTV.setTextColor(getColor(R.color.red));
                valid = false;
            } else {
                try {
                    int value = Integer.parseInt(number);
                    if (value < 10 || value > 614) {
                        defenseET.setError("10–614");
                        defenseTV.setTextColor(getColor(R.color.red));
                        valid = false;
                    } else {
                        defenseTV.setTextColor(getColor(R.color.black));
                    }
                } catch (NumberFormatException e) {
                    defenseET.setError("Whole number");
                    defenseTV.setTextColor(getColor(R.color.red));
                    valid = false;
                }
            }

            number = heightTIL.getText().toString().trim();
            if (number.isEmpty()) {
                heightTIL.setError("Required");
                heightTV.setTextColor(getColor(R.color.red));
                valid = false;
            } else {
                try {
                    double value = Double.parseDouble(number);
                    if (value < 0.2 || value > 169.99) {
                        heightTIL.setError("0.2–169.99");
                        heightTV.setTextColor(getColor(R.color.red));
                        valid = false;
                    } else {
                        heightTV.setTextColor(getColor(R.color.black));
                    }
                } catch (NumberFormatException e) {
                    heightTIL.setError("Number");
                    heightTV.setTextColor(getColor(R.color.red));
                    valid = false;
                }
            }

            number = weightTIL.getText().toString().trim();
            if (number.isEmpty()) {
                weightTIL.setError("Required");
                weightTV.setTextColor(getColor(R.color.red));
                valid = false;
            } else {
                try {
                    double value = Double.parseDouble(number);
                    if (value < 0.1 || value > 992.7) {
                        weightTIL.setError("0.1–992.7");
                        weightTV.setTextColor(getColor(R.color.red));
                        valid = false;
                    } else {
                        weightTV.setTextColor(getColor(R.color.black));
                    }
                } catch (NumberFormatException e) {
                    weightTIL.setError("Number");
                    weightTV.setTextColor(getColor(R.color.red));
                    valid = false;
                }
            }


        if (!valid){
            Toast.makeText(this,"Please verify the hightlighted fields", Toast.LENGTH_SHORT).show();
        }
        return valid;
    }
}