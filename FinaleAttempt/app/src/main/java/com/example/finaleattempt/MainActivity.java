package com.example.finaleattempt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import  android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, imageURL , recipeURL,  description;
    Button insert, view;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View objects for corresponding layout
        name = findViewById(R.id.name);
        imageURL = findViewById(R.id.imageURL);
        recipeURL = findViewById(R.id.recipeURL);
        description = findViewById(R.id.description);
        insert = findViewById(R.id.btnInsert);


        DB = new DBHelper(this);


        insert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //The following lines stores the values entered by the user when the insert button is clicked
                        String nameTXT = name.getText().toString();
                        String imageTXT = imageURL.getText().toString();
                        String recipeTXT = recipeURL.getText().toString();
                        String descTXT = description.getText().toString();

                        //Inserts the entered feilds into the database and returns a boolean value to check if successfully saved.
                        Boolean checkInsertData = DB.insertRecipeData(nameTXT, imageTXT, recipeTXT, descTXT);

                        if(checkInsertData ==true){ //Checks if data insertion successful
                            Toast.makeText(MainActivity.this, "New recipe added", Toast.LENGTH_SHORT).show(); //Message notification to user for successful recipe addition
                        }

                        else{
                            Toast.makeText(MainActivity.this, "New recipe not added", Toast.LENGTH_SHORT).show();  //Message notification to user for unsuccessful recipe addition
                        }
                    }
                }
        );
    }

    //This method is the inflate the menu with page options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }


    //This method adds onClickListeners to the menu options(items)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainPage:
                return true;

            case (R.id.recipeListPage):
                startActivity(new Intent(MainActivity.this, RecipeList.class));
                return true;

            default: return true;
        }
    }
}