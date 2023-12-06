package algonquin.cst2355.finalproject013group2.SunriseSunset;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2355.finalproject013group2.R;

/**
 * Activity class for displaying the details of sunrise and sunset for a specific location.
 * This class handles the presentation of sunrise and sunset times, first light, last light,
 * dawn, dusk, solar noon, golden hour, day length, and timezone information for a given location.
 * It also provides functionality to add the current lookup to favorites.
 */
public class sunriseDetail extends AppCompatActivity {

   /**
     * Called when the activity is starting. This is where most initialization should go:
     * calling setContentView(int) to inflate the activity's UI, using findViewById(int) to
     * programatically interact with widgets in the UI, setting up listeners, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down then this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sunrise_detail);

        String latitude = getIntent().getStringExtra("Latitude");
        String longitude = getIntent().getStringExtra("Longitude");

        TextView resultsForTextView = findViewById(R.id.resultsForTextView);
        if (latitude != null && longitude != null) {
            resultsForTextView.setText("Now displaying results for: " + latitude + " & " + longitude);
        } else {
            resultsForTextView.setText("Latitude and/or Longitude not provided.");
        }

        TextView sunriseDateTextView = findViewById(R.id.sunriseDateTextView);
        TextView sunriseTimeTextView = findViewById(R.id.sunriseTimeTextView);
        TextView sunsetTimeTextView = findViewById(R.id.sunsetTimeTextView);
        TextView sunriseFLTextView = findViewById(R.id.sunriseFLTextView);
        TextView sunriseLLTextView = findViewById(R.id.sunriseLLTextView);
        TextView sunriseDawnTextView = findViewById(R.id.sunriseDawnTextView);
        TextView sunriseDuskTextView = findViewById(R.id.sunriseDuskTextView);
        TextView sunriseSNTextView = findViewById(R.id.sunriseSNTextView);
        TextView sunriseGHTextView = findViewById(R.id.sunriseGHTextView);
        TextView sunriseDLTextView = findViewById(R.id.sunriseDLTextView);
        TextView sunriseTZTextView = findViewById(R.id.sunriseTZTextView);

        sunriseDateTextView.setText("Date: " + getIntent().getStringExtra("Date"));
        sunriseTimeTextView.setText("Sunrise Time: " + getIntent().getStringExtra("SunriseTime"));
        sunsetTimeTextView.setText("Sunset Time: " + getIntent().getStringExtra("SunsetTime"));
        sunriseFLTextView.setText("First Light: " + getIntent().getStringExtra("FirstLight"));
        sunriseLLTextView.setText("Last Light: " + getIntent().getStringExtra("LastLight"));
        sunriseDawnTextView.setText("Dawn: " + getIntent().getStringExtra("Dawn"));
        sunriseDuskTextView.setText("Dusk: " + getIntent().getStringExtra("Dusk"));
        sunriseSNTextView.setText("Solar Noon: "+ getIntent().getStringExtra("SolarNoon"));
        sunriseGHTextView.setText("Golden Hour: " + getIntent().getStringExtra("GoldenHour"));
        sunriseDLTextView.setText("Day Length: " + getIntent().getStringExtra("DayLength"));
        sunriseTZTextView.setText("Timezone: " + getIntent().getStringExtra("Timezone"));

        Button addToFavoritesButton = findViewById(R.id.addToFavoritesButton);
        Button backToMainButton = findViewById(R.id.backToMainButton);

        addToFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCurrentLookupToFavorites();
            }
        });

        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Adds the current lookup details to the favorites database.
     * This method retrieves the latitude, longitude, and timezone from the Intent's extras,
     * creates a new FavouriteLocation object, and uses the sunriseDBHelper to add the location
     * to the database. It also displays a Snackbar message with an Undo option.
     */
    private void addCurrentLookupToFavorites() {
        String latitude = getIntent().getStringExtra("Latitude");
        String longitude = getIntent().getStringExtra("Longitude");
        String timezone = getIntent().getStringExtra("Timezone");

        FavouriteLocation favourite = new FavouriteLocation();
        favourite.setLatitude(latitude);
        favourite.setLongitude(longitude);
        favourite.setTimezone(timezone);

        sunriseDBHelper dbHelper = new sunriseDBHelper(this);
        long rowId = dbHelper.addFavouriteLocation(favourite); // Save the returned row ID for potential undo

        // Use the CoordinatorLayout or any other layout that's the parent of your RecyclerView
        View view = findViewById(R.id.coordinatorLayout);
        Snackbar snackbar = Snackbar.make(view, "Location added to favorites.", Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the Undo button, delete the favorite location
                dbHelper.deleteFavoriteLocation((int) rowId);
                // Optionally, refresh the RecyclerView or inform the user
            }
        });

        snackbar.show();
    }
}