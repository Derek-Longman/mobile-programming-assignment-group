package algonquin.cst2335.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import algonquin.cst2335.myapplication.databinding.ActivityMainBinding;
import algonquin.cst2335.myapplication.databinding.DeezerSongBinding;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> songs;
    private RecyclerView.Adapter myAdapter;
ActivityMainBinding binding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
        switch( item.getItemId() )
        {
            case R.id.recipe:
             startActivity(new Intent());

                break;
            case R.id.Help:
                Toast.makeText(this, "Help",Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        setTitle("Deezer Song");
        songs = new ArrayList<>();
        songs.add("Test");
        SharedPreferences prefs = getSharedPreferences("SongData", Context.MODE_PRIVATE);
        String search = prefs.getString("SongSearch", "");
        binding.editTextText.setText(search);
        SharedPreferences.Editor editor = prefs.edit();


binding.button.setOnClickListener(click ->{
    Toast.makeText(this,"Toast: "+ binding.editTextText.getText().toString(),Toast.LENGTH_SHORT).show();
    editor.putString("SongSearch",binding.editTextText.getText().toString());
    editor.apply();
});
        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.RecyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                DeezerSongBinding binding = DeezerSongBinding.inflate(getLayoutInflater());
                return new MyRowHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.songname.setText("");
                String obj = songs.get(position);
                holder.songname.setText(obj);
            }

            @Override
            public int getItemCount() {
                return songs.size();
            }
        });
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView songname;
        int position;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(clk -> {
                position = getAdapterPosition();
                String selected = songs.get(position);
               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete the message " + songname.getText())
                .setTitle("Question:")
                .setPositiveButton("No", (dialog, cl) -> {
                })
                .setNegativeButton("Yes", ((dialog, cl) -> {
                String m= songs.get(position);
                songs.remove(position);
                myAdapter.notifyItemRemoved(position);
                    Snackbar.make(songname,"You deleted message #"+ position, Snackbar.LENGTH_LONG)
                            .setAction("Undo",click ->{
                                songs.add(position,m);
                                myAdapter.notifyItemInserted(position);
                            })
                            .show();
                }))
                .create().show();

            });
            songname = itemView.findViewById(R.id.songname);

        }
    }
}