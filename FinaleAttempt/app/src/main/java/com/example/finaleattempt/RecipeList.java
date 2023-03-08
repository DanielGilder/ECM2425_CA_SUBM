package com.example.finaleattempt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;



public class RecipeList extends AppCompatActivity implements RecyclerViewInterface {
    RecyclerView recyclerView;
    ArrayList<String> name, image, recipe, description;
    DBHelper db;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        db = new DBHelper(this);

        //These ArrayLists are used to store the Table records from the SQLite DB which are then used later to inflate the view holder by the adapter class.
        name = new ArrayList<>();
        image = new ArrayList<>();
        recipe = new ArrayList<>();
        description = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);

        adapter = new MyAdapter(this, name, image,recipe,description ,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }



    //This method access the cursor object with the retrieved recipes from the database and places all the individual record fields
    //in their corresponding arraylists.
    private void displayData() {

        Cursor cursor = db.getData();

        if(cursor.getCount()==0){ //Checks to see if their are zero recipe entries
            Toast.makeText(RecipeList.this,"No recipes exist", Toast.LENGTH_SHORT).show(); //Notifies the user that no recipes are saved in the RecipeList
            return;
        }
        else {
            //This while loop iterates through the cursor object to place the Recipes entry feilds in the corresponding arraylists to allow the item view to be inflated.
            while (cursor.moveToNext()){
                name.add(cursor.getString(0));
                image.add(cursor.getString(1));
                recipe.add(cursor.getString(2));
                description.add(cursor.getString(3));

            }
        }
    }


    //This method is used to start and pass data to the RecipePage Activity by creating an explicit intent and adding extra data.
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(RecipeList.this, RecipePage.class);
        intent.putExtra("name", name.get(position));
        intent.putExtra("image", image.get(position));
        intent.putExtra("description", description.get(position));
        intent.putExtra("recipe" , recipe.get(position));
        startActivity(intent);



    }

    //This method inflates the menu layout with the page options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    //This method add onClickListeners to the menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainPage:
                finish();
                return true;

            default: return true;
        }
    }

}