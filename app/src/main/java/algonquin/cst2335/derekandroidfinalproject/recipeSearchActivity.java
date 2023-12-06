package algonquin.cst2335.derekandroidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import algonquin.cst2335.derekandroidfinalproject.databinding.ActivityRecipeSearchBinding;

public class recipeSearchActivity extends AppCompatActivity {
ArrayList<String> recipies;
private RecyclerView.Adapter derekAdapter;
ActivityRecipeSearchBinding binding;
public boolean onCreateOptionsMenu(Menu menu){
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.menus,menu);
    return true;
}
@Override
public boolean onOptionsItemSelected(@NonNull MenuItem item){
    super.onOptionsItemSelected(item);
    switch(item.getItemId()){
        case R.id.sunrise:
            //startActivity(new Intent(this, recipeSearchActivity.class));
            break;
        case R.id.dictionary:
            //startActivity(new Intent(this, recipeSearchActivity.class));
            break;
        case R.id.song:
            //startActivity(new Intent(this, recipeSearchActivity.class));
            break;
        case R.id.help:
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Info about this page")
                    .setMessage("this shows the recipie name and picture"+
                     "it also allows you to save certain recipies")
                    //.setPositiveButton("Close",(dialog,which)-{})
                    .create()
                    .show();
            break;
    }
    return true;
}
RequestQueue queue =null;
    ViewModel recipeModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        queue = Volley.newRequestQueue(this);
        setSupportActionBar(binding.toolbar);
        recipeModel = new ViewModelProvider(this).get(ViewModel.class);
        recipies = new ArrayList<>();
//        if(recipeModel.recipies.getValue()!=null){
//            recipies.addAll(recipeModel.recipies.getValue());
//        }
        SharedPreferences preferences = getSharedPreferences("RecipeData", Context.MODE_PRIVATE);
        String search = preferences.getString("RecipeSearch","");
        binding.searchBar.setText(search);
        SharedPreferences.Editor editor = preferences.edit();
//        recipeModel.selectedRecipie.observe(this,(recipe)->{
//
//        });

        binding.searchButton.setOnClickListener(click ->{
            String text = binding.searchBar.getText().toString();

            Toast.makeText(this,"Toast:"+text,Toast.LENGTH_SHORT).show();
            editor.putString("RecipeSearch",text);
            editor.apply();

            try {
                String recipeName = URLEncoder.encode(search.toString(), "UTF-8");
                //String recipeName = URLEncoder.encode(search.getText().toString(), "UTF-8");
                String stringURL ="https://api.spoonacular.com/recipes/complexSearch?query="+ recipeName +"&apiKey=d904a6e732664ed6ac143ccbe9e429db";


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,stringURL,null,
                        (response) -> {
                    try {
                        recipies.clear();
                        JSONArray recipeArray=response.getJSONArray("results");
                        for (int i =0; i< recipeArray.length();i++) {
                            JSONObject recipe_obj = recipeArray.getJSONObject(0);
                            String recipe_title = recipe_obj.getString("title");
                            recipies.add(recipe_title);
                            //iconName = recipe_obj.getString("image");

                        }
                        //File imageFile = new File(iconName);
                        //recipeModel.recipies.postValue(recipies);

                    }catch (JSONException e){
                        throw new RuntimeException(e);
                    }
                        },
                        (error)->{
                    Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show();
                        });
                queue.add(request);
            }catch (UnsupportedEncodingException e){
                throw new RuntimeException(e);
            }

                });
        binding.recipeList.setLayoutManager(new LinearLayoutManager(this));
        binding.recipeList.setAdapter(derekAdapter = new RecyclerView.Adapter<MyRowHolder>()

        {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ActivityRecipeSearchBinding binding = ActivityRecipeSearchBinding.inflate(getLayoutInflater());

                return new MyRowHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.recipename.setText("");
                String obj = recipies.get(position);
                holder.recipename.setText(obj);

            }


            @Override
            public int getItemCount(){
                return recipies.size();
            }
        });

    }

    class MyRowHolder extends RecyclerView.ViewHolder{
        TextView recipename;
        int position;

        public MyRowHolder(@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(clk -> {
                position = getAdapterPosition();
                String selected = recipies.get(position);
                //recipeModel.selected.recipies.postValue(Selected);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(recipeSearchActivity.this);
//                    builder.setMessage(""+recipename.getText())
//                    .setTitle("question:")
//                            .setPositiveButton("No",(dialog,cl)->{})
//                            .setNegativeButton("Yes",((dialog,cl)->{
//                                String r = recipies.get(position);
//                                recipies.remove(position);
//                                derekAdapter.notifyItemRemoved(position);
//                                Snackbar.make(recipename,"you")
//
//
//                            }))


            });
            //recipename=itemView.findViewById(R.id.recipename);



        }


    }
}