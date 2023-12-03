package algonquin.cst2355.finalproject013group2.SunriseSunset;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import algonquin.cst2355.finalproject013group2.R;

public class sunriseMain extends AppCompatActivity {

    private EditText latitudeInput;
    private EditText longitudeInput;
    private TextView resultView;
    private RecyclerView favoritesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sunrisesunset_main);

        latitudeInput = findViewById(R.id.latitudeInput);
        longitudeInput = findViewById(R.id.longitudeInput);
        Button lookupButton = findViewById(R.id.lookupButton);
        Button saveButton = findViewById(R.id.saveButton);
        Button viewFavoritesButton = findViewById(R.id.viewFavoritesButton);
        resultView = findViewById(R.id.resultView);
        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView);

        // Setup RecyclerView
        favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Set the adapter for RecyclerView. You need to create a custom Adapter for this.
        // favoritesRecyclerView.setAdapter(new FavoritesAdapter(favoritesList));

        // Restore previous search term
        String prevSearch = getPreferences(MODE_PRIVATE).getString("prevSearch", "");
        latitudeInput.setText(prevSearch);

        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchSunriseSunsetInfo();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocation();
            }
        });

        viewFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFavorites();
            }
        });

        // Add more logic here for other functionalities
    }

    private void fetchSunriseSunsetInfo() {
        String latitude = latitudeInput.getText().toString();
        String longitude = longitudeInput.getText().toString();

        // Save search term
        getPreferences(MODE_PRIVATE).edit().putString("prevSearch", latitude).apply();

        // Network request to fetch sunrise and sunset data
        // This should be done in a background thread
        // Update resultView with the fetched data
    }

    private void saveLocation() {
        // Logic to save the current location to the database
    }

    private void viewFavorites() {
        // Logic to fetch favorite locations from the database and update the RecyclerView
        favoritesRecyclerView.setVisibility(View.VISIBLE);
    }

    // Add more methods for handling favorite locations, deleting locations, etc.

}