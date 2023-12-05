package algonquin.cst2335.derekandroidfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
RequestQueue queue =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        queue = Volley.newRequestQueue(this);
        setSupportActionBar(binding.toolbar);
        recipies = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("RecipeData", Context.MODE_PRIVATE);
        String search = preferences.getString("RecipeSearch","");
        binding.searchBar.setText(search);
        SharedPreferences.Editor editor = preferences.edit();

        binding.searchButton.setOnClickListener(click ->{
            String text = binding.searchBar.getText().toString();

            Toast.makeText(this,"Toast:"+text,Toast.LENGTH_SHORT).show();
            editor.putString("RecipeSearch",text);
            editor.apply();

            try {
                String recipeName = URLEncoder.encode(search.getText().toString(), "UTF-8");
                String stringURL ="“https://api.spoonacular.com/recipes/complexSearch?query="+ recipeName +"&apiKey=YYYYY”";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,stringURL,null,
                        (response) -> {
                    try {
                        JSONArray recipeArray=response.getJSONArray("");
                        JSONObject recipe_obj = recipeArray.getJSONObject(0);
                        String

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
        binding.recipeList.setAdapter(derekAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new MyRowHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

            }


            @Override
            public int getItemCount() {
                return recipies.size();
            }
        });

    }

    class MyRowHolder extends RecyclerView.ViewHolder{


    };
}