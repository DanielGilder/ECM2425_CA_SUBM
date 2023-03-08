package com.example.finaleattempt;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private ArrayList name_id, image_id, recipe_id, description_id;

    public MyAdapter(Context context,  ArrayList name_id, ArrayList image_id,ArrayList recipe_id, ArrayList description_id, RecyclerViewInterface recyclerViewInterface) {
        this.context=context;
        this.name_id=name_id;
        this.image_id=image_id;
        this.recipe_id = recipe_id;
        this.description_id = description_id;


        this.recyclerViewInterface = recyclerViewInterface;
        //context.deleteDatabase("Recipedata.db");

    }

    //Creates and and inflates the view holder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipeentry,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position))); //Sets the recipe name on the item view
        new DownloadImageTask(holder.image_id).execute(String.valueOf(image_id.get(position))); //Creates a thread to perform network operations of the UI thread

    }

    //This method returns the number of entries in the database
    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id;
        ImageView image_id;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            image_id = itemView.findViewById(R.id.entryImage);



            //This block is used to start an implicit intent to the RecipePage from the corresponding item entry on the RecipeList Activity
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface!=null){
                        int pos = getAdapterPosition();

                        if(pos  != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    //The following class is used to offload the image fetching from the URL of the UI thread
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        //Takes the URI and performs the network operation
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap icon = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                icon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return icon; //The Bitmap image to be returned to the UI
        }


        //Displays the final result image to the UI
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
